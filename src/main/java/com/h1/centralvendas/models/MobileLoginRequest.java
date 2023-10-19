package com.h1.centralvendas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileLoginRequest {
	// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
	// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
	/* ObjectMapper om = new ObjectMapper();
	Root root = om.readValue(myJsonString, Root.class); */
	
	public class INTERNO{
	    public String $;
	    public String getINTERNO() {
	    	return this.$;
	    }
	    
	    public INTERNO(String $) {
	    	this.$ = $;
	    }
	}

	public class KEEPCONNECTED{
	    public String $;
	    public String getKEEPCONNECTED() {
	    	return this.$;
	    }
	    
	    public KEEPCONNECTED(String $) {
	    	this.$ = $;
	    }
	    
	}

	public class NOMUSU{
	    public String $;
	    
	    public String getNOMUSU() {
	    	return this.$;
	    }
	    
	    public NOMUSU(String $) {
	    	this.$ = $;
	    }
	}

	public class RequestBody{
	    @JsonProperty("NOMUSU") 
	    public NOMUSU nOMUSU;
	    @JsonProperty("INTERNO") 
	    public INTERNO iNTERNO;
	    @JsonProperty("KEEPCONNECTED") 
	    public KEEPCONNECTED kEEPCONNECTED;
	}
	
	public Root getRequestBody(RequestBody value){
		return new Root(value);
	}
	public RequestBody getRequest(){
		return new RequestBody();
	}
	public NOMUSU getNOMUSU(String value){ 
		return new NOMUSU(value);
	}
	public INTERNO getINTERNO(String value){
		return new INTERNO(value);
	}
	public KEEPCONNECTED getKEEPCONNECTED(String value){
		return new KEEPCONNECTED(value);
	}

	public class Root{
	    public RequestBody requestBody;
	    public Root(RequestBody request) {
	    	this.requestBody = request;
	    }
	}


	}
