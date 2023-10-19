package com.h1.centralvendas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.h1.centralvendas.dto.LoginDTO;
import com.h1.centralvendas.models.MobileLoginResponse;
import com.h1.centralvendas.services.MobileLoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/api/login")
public class LoginApi {
	@Autowired
	MobileLoginService service;
	
	@GetMapping("view")
	public ModelAndView Get() {
		return new ModelAndView("teste");
	}
	
	@PostMapping
	public RedirectView Api(HttpServletResponse http, @Value("${sankhya.url.base}") String url, LoginDTO login) {
        RedirectView redirect = new RedirectView();
		try {
            MobileLoginResponse.Root response = service.Teste(url, login);
            MobileLoginResponse.ResponseBody body = response.responseBody;
            //http.setAttribute("mgesession", body.getJsessionid().$);
            Cookie cookie = new Cookie("mgesession", body.getJsessionid().$);
            cookie.setPath("/");
            http.addCookie(cookie);
            redirect.setUrl("/home");
            return redirect;
        } catch (Exception e) {
            redirect.setUrl("/login");
            return redirect;
        }
        
    }
	
}
