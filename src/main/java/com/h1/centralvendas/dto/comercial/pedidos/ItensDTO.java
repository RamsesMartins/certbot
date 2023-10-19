package com.h1.centralvendas.dto.comercial.pedidos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItensDTO {
    @JsonProperty("INFORMARPRECO") 
    public String iNFORMARPRECO;
    public ArrayList<Item> item;
    
    public class Item{
        @JsonProperty("NUNOTA") 
        public NUNOTA nUNOTA;
        @JsonProperty("CODPROD") 
        public CODPROD cODPROD;
        @JsonProperty("QTDNEG") 
        public QTDNEG qTDNEG;
        @JsonProperty("CODLOCALORIG") 
        public CODLOCALORIG cODLOCALORIG;
        @JsonProperty("CODVOL") 
        public CODVOL cODVOL;
        @JsonProperty("PERCDESC") 
        public PERCDESC pERCDESC;
        @JsonProperty("VLRUNIT") 
        public VLRUNIT vLRUNIT;
    }
    public class CODPROD{
        public String $;
    }
    public class PERCDESC{
        public String $;
    }
    public class VLRUNIT{
        public String $;
    }
    public class QTDNEG{
        public String $;
    }
    public class CODLOCALORIG{
        public String $;
    }
    public class CODVOL{
        public String $;
    }
    public class NUNOTA{
    }
}
