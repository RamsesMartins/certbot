package com.h1.centralvendas.services;


import org.springframework.stereotype.Service;
import com.h1.centralvendas.dto.LoginDTO;
import com.h1.centralvendas.models.MobileLoginRequest;
import com.h1.centralvendas.models.MobileLoginResponse;

@Service
public class MobileLoginService {
    public MobileLoginResponse.Root  Teste(String url, LoginDTO login) {
    	MobileLoginRequest mobileLogin = new MobileLoginRequest();
    	MobileLoginRequest.RequestBody request = mobileLogin.getRequest();
    	request.nOMUSU = new MobileLoginRequest().getNOMUSU(login.getUsername());
    	request.iNTERNO = new MobileLoginRequest().getINTERNO(login.getPassword());
    	request.kEEPCONNECTED = new MobileLoginRequest().getKEEPCONNECTED("S");
    	MobileLoginRequest.Root RequestBody = mobileLogin.getRequestBody(request);

        System.out.println(RequestBody.requestBody.kEEPCONNECTED.$);
        // Defina o URL da API
        String apiUrl = url+"serviceName=MobileLoginSP.login";
        
        RequestAPI requestAPI = new RequestAPI();
        return requestAPI.postForEntity(apiUrl, RequestBody, MobileLoginResponse.Root.class);
        
        //responseEntity.getBody();
    }
}

