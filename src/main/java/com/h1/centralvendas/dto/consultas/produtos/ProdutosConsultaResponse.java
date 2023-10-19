package com.h1.centralvendas.dto.consultas.produtos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutosConsultaResponse {
    @JsonProperty("entity")
    public ArrayList<Entity> entity;
    
    public class Entity{
        public F0 CODPROD;
        public F1 DESCRPROD;
        public F2 LOCAL;
        public F3 MARCA;
        public F4 CODVOL;
    }

    public static class F0{
        public String $;
    }

    public static class F1{
        public String $;
    }

    public static class F2{
        public String $;
    }

    public static class F3{
        public String $;
    }

    public static class F4{
        public String $;
    }

}
