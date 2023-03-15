"""Tests for certbot.plugins.dns_common."""

import collections
import logging
import sys
import unittest
from unittest import mock

import pytest

from certbot import errors
from certbot import util
from certbot.compat import os
from certbot.display import util as display_util
from certbot.plugins import dns_common
from certbot.plugins import dns_test_common
from certbot.tests import util as test_util
from certbot.tests.util import FreezableMock
from typing import Dict, Union


class _FakeDNSAuthenticator(dns_common.DNSAuthenticator):
    _setup_credentials = mock.MagicMock()
    _perform = mock.MagicMock()
    _cleanup = mock.MagicMock()

    def more_info(self) -> str:  # pylint: disable=missing-docstring,no-self-use
        return 'A fake authenticator for testing.'


class _FakeConfig:
    fake_propagation_seconds = 0
    fake_config_key = 1
    fake_other_key = None
    fake_file_path = None


class DNSAuthenticatorTest(test_util.TempDirTestCase, dns_test_common.BaseAuthenticatorTest):
    # pylint: disable=protected-access

    def setUp(self) -> None:
        super().setUp()

        self.config = _FakeConfig()

        self.auth = _FakeDNSAuthenticator(self.config, "fake")

    @test_util.patch_display_util()
    def test_perform(self, unused_mock_get_utility: FreezableMock) -> None:
        self.auth.perform([self.achall])

        self.auth._perform.assert_called_once_with(dns_test_common.DOMAIN, mock.ANY, mock.ANY)

    def test_cleanup(self) -> None:
        self.auth._attempt_cleanup = True

        self.auth.cleanup([self.achall])

        self.auth._cleanup.assert_called_once_with(dns_test_common.DOMAIN, mock.ANY, mock.ANY)

    @test_util.patch_display_util()
    def test_prompt(self, mock_get_utility: FreezableMock) -> None:
        mock_display = mock_get_utility()
        mock_display.input.side_effect = ((display_util.OK, "",),
                                          (display_util.OK, "value",))

        self.auth._configure("other_key", "")
        assert self.auth.config.fake_other_key == "value"

    @test_util.patch_display_util()
    def test_prompt_canceled(self, mock_get_utility: FreezableMock) -> None:
        mock_display = mock_get_utility()
        mock_display.input.side_effect = ((display_util.CANCEL, "c",),)

        with pytest.raises(errors.PluginError):
            self.auth._configure("other_key", "")

    @test_util.patch_display_util()
    def test_prompt_file(self, mock_get_utility: FreezableMock) -> None:
        path = os.path.join(self.tempdir, 'file.ini')
        open(path, "wb").close()

        mock_display = mock_get_utility()
        mock_display.directory_select.side_effect = ((display_util.OK, "",),
                                                     (display_util.OK, "not-a-file.ini",),
                                                     (display_util.OK, self.tempdir),
                                                     (display_util.OK, path,))

        self.auth._configure_file("file_path", "")
        assert self.auth.config.fake_file_path == path

    @test_util.patch_display_util()
    def test_prompt_file_canceled(self, mock_get_utility: FreezableMock) -> None:
        mock_display = mock_get_utility()
        mock_display.directory_select.side_effect = ((display_util.CANCEL, "c",),)

        with pytest.raises(errors.PluginError):
            self.auth._configure_file("file_path", "")

    def test_configure_credentials(self) -> None:
        path = os.path.join(self.tempdir, 'file.ini')
        dns_test_common.write({"fake_test": "value"}, path)
        setattr(self.config, "fake_credentials", path)

        credentials = self.auth._configure_credentials("credentials", "", {"test": ""})

        assert credentials.conf("test") == "value"

    @test_util.patch_display_util()
    def test_prompt_credentials(self, mock_get_utility: FreezableMock) -> None:
        bad_path = os.path.join(self.tempdir, 'bad-file.ini')
        dns_test_common.write({"fake_other": "other_value"}, bad_path)

        path = os.path.join(self.tempdir, 'file.ini')
        dns_test_common.write({"fake_test": "value"}, path)
        setattr(self.config, "fake_credentials", "")

        mock_display = mock_get_utility()
        mock_display.directory_select.side_effect = ((display_util.OK, "",),
                                                     (display_util.OK, "not-a-file.ini",),
                                                     (display_util.OK, self.tempdir),
                                                     (display_util.OK, bad_path),
                                                     (display_util.OK, path,))

        credentials = self.auth._configure_credentials("credentials", "", {"test": ""})
        assert credentials.conf("test") == "value"

    def test_auth_hint(self) -> None:
        assert 'try increasing --fake-propagation-seconds (currently 0 seconds).' in \
            self.auth.auth_hint([mock.MagicMock()])


class _MockLoggingHandler(logging.Handler):
    messages = None

    def __init__(self, *args, **kwargs) -> None:
        self.reset()
        super().__init__(*args, **kwargs)

    def emit(self, record:     logging.LogRecord) -> None:
        self.messages[record.levelname.lower()].append(record.getMessage())

    def reset(self) -> None:
        """Allows the handler to be reset between tests."""
        self.messages = collections.defaultdict(list)


class CredentialsConfigurationTest(test_util.TempDirTestCase):
    def test_valid_file(self) -> None:
        path = os.path.join(self.tempdir, 'too-permissive-file.ini')

        dns_test_common.write({"test": "value", "other": 1}, path)

        credentials_configuration = dns_common.CredentialsConfiguration(path)
        assert "value" == credentials_configuration.conf("test")
        assert "1" == credentials_configuration.conf("other")

    def test_nonexistent_file(self) -> None:
        path = os.path.join(self.tempdir, 'not-a-file.ini')

        with pytest.raises(errors.PluginError):
            dns_common.CredentialsConfiguration(path)

    def test_valid_file_with_unsafe_permissions(self) -> None:
        log = _MockLoggingHandler()
        dns_common.logger.addHandler(log)

        path = os.path.join(self.tempdir, 'too-permissive-file.ini')
        util.safe_open(path, "wb", 0o744).close()

        dns_common.CredentialsConfiguration(path)

        assert 1 == len([_ for _ in log.messages['warning'] if _.startswith("Unsafe")])


class CredentialsConfigurationRequireTest(test_util.TempDirTestCase):

    def setUp(self) -> None:
        super().setUp()

        self.path = os.path.join(self.tempdir, 'file.ini')

    def _write(self, values: Dict[str, Union[str, int]]) -> None:
        dns_test_common.write(values, self.path)

    def test_valid(self) -> None:
        self._write({"test": "value", "other": 1})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        credentials_configuration.require({"test": "", "other": ""})

    def test_valid_but_extra(self) -> None:
        self._write({"test": "value", "other": 1})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        credentials_configuration.require({"test": ""})

    def test_valid_empty(self) -> None:
        self._write({})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        credentials_configuration.require({})

    def test_missing(self) -> None:
        self._write({})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        with pytest.raises(errors.PluginError):
            credentials_configuration.require({"test": ""})

    def test_blank(self) -> None:
        self._write({"test": ""})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        with pytest.raises(errors.PluginError):
            credentials_configuration.require({"test": ""})

    def test_typo(self) -> None:
        self._write({"tets": "typo!"})

        credentials_configuration = dns_common.CredentialsConfiguration(self.path)
        with pytest.raises(errors.PluginError):
            credentials_configuration.require({"test": ""})


class DomainNameGuessTest(unittest.TestCase):

    def test_simple_case(self) -> None:
        assert 'example.com' in \
            dns_common.base_domain_name_guesses("example.com")

    def test_sub_domain(self) -> None:
        assert 'example.com' in \
            dns_common.base_domain_name_guesses("foo.bar.baz.example.com")

    def test_second_level_domain(self) -> None:
        assert 'example.co.uk' in \
            dns_common.base_domain_name_guesses("foo.bar.baz.example.co.uk")


if __name__ == "__main__":
    sys.exit(pytest.main(sys.argv[1:] + [__file__]))  # pragma: no cover
