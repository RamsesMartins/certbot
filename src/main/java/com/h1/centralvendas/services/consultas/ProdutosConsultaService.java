package com.h1.centralvendas.services.consultas;

import org.springframework.stereotype.Service;

import com.h1.centralvendas.dto.consultas.CRUDServiceProvider;
import com.h1.centralvendas.dto.consultas.produtos.ProdutosConsultaResponse;
import com.h1.centralvendas.services.RequestAPI;

@Service
public class ProdutosConsultaService{
    public String getProdutosByName(String url, String NomeProd, String jsession){
       
       CRUDServiceProvider provider = new CRUDServiceProvider();
       String expression = String.format("this.CODPROD = %s", NomeProd);
       CRUDServiceProvider.DataSet dataSet = new CRUDServiceProvider.DataSet("Produtos", expression, "CODPROD,DESCRPROD,LOCAL,MARCA,CODVOL");
       
        CRUDServiceProvider.RequestBody requestBody = new CRUDServiceProvider.RequestBody(dataSet);

        provider.setRequestBody(requestBody);

       String apiURL = url + "serviceName="+provider.getServiceName()+"&mgesession="+jsession;
       RequestAPI requestAPI = new RequestAPI();
       System.out.println(requestBody.getDataSet().getRootEntity());
       System.out.println(apiURL);
       return requestAPI.getForEntity(apiURL, provider, String.class);
    }
}