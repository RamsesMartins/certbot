package com.h1.centralvendas.dto.comercial.pedidos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CabecalhoDTO {
    @JsonProperty("NUNOTA") 
    public NUNOTA nUNOTA;
    @JsonProperty("CODPARC") 
    public CODPARC cODPARC;
    @JsonProperty("DTNEG") 
    public DTNEG dTNEG;
    @JsonProperty("CODTIPOPER") 
    public CODTIPOPER cODTIPOPER;
    @JsonProperty("CODTIPVENDA") 
    public CODTIPVENDA cODTIPVENDA;
    @JsonProperty("CODVEND") 
    public CODVEND cODVEND;
    @JsonProperty("CODEMP") 
    public CODEMP cODEMP;
    @JsonProperty("TIPMOV") 
    public TIPMOV tIPMOV;
    
    
    public class CODEMP{
        public String $;
    }
    
    public class CODLOCALORIG{
        public String $;
    }
    
    public class CODPARC{
        public String $;
    }
    
    public class CODPROD{
        public String $;
    }
    
    public class CODTIPOPER{
        public String $;
    }
    
    public class CODTIPVENDA{
        public String $;
    }
    
    public class CODVEND{
        public String $;
    }
    
    public class CODVOL{
        public String $;
    }
    
    public class DTNEG{
        public String $;
    }

    public class NUNOTA{
    }

    public class TIPMOV{
        public String $;
    }
}
