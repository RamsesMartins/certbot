package com.h1.centralvendas.dto.comercial;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.h1.centralvendas.dto.comercial.pedidos.CabecalhoDTO;
import com.h1.centralvendas.dto.comercial.pedidos.ItensDTO;

public class PedidoDTO {
    @JsonProperty("serviceName")
    public String serviceName = "CACSP.incluirNota";
    public RequestBody requestBody;

    private static class RequestBody{
        @JsonProperty("nota")
        public Nota nota;
    }
    private static class Nota{
        public CabecalhoDTO cabecalho;
        public ItensDTO itens;
    }
}
