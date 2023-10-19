package com.h1.centralvendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.h1.centralvendas.models.MobileLoginResponse;
import com.h1.centralvendas.services.MobileLoginService;

import jakarta.servlet.http.HttpSession;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Api {
	@Autowired
	MobileLoginService service;
	
	@GetMapping
	public ModelAndView Get() {
		return new ModelAndView("teste");
	}
	
	// @GetMapping("/api")
	// public MobileLoginResponse.Root  Api(HttpSession http, @Value("${sankhya.url.base}") String url) {
	// 	MobileLoginResponse.Root response = service.Teste(url);
	// 	MobileLoginResponse.ResponseBody body = response.responseBody; 
	// 	System.out.println(body.getJsessionid().$);
	// 	http.setAttribute("jsessionid", body.getJsessionid().$);
	// 	return service.Teste(url);
	// }
	
}
