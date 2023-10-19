package com.h1.centralvendas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h1.centralvendas.dto.consultas.produtos.ProdutosConsultaResponse;
import com.h1.centralvendas.services.consultas.ProdutosConsultaService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/produtos")
public class ConsultaProdutosAPI {
    @Autowired
    ProdutosConsultaService service;

    @GetMapping
    public String getProdutosByName(@CookieValue(name = "mgesession", required = true) String session, @Value("${sankhya.url.base}")String url){
        //String jsession = (String) http.getAttribute("JSESSIONID");
        System.out.println(session);
        return service.getProdutosByName(url, "101010003", session);
    }
}
