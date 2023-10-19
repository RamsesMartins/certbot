package com.h1.centralvendas.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileLoginResponse {
	private static class CallID{
	    public String $;
	}
	
	private static class Idusu{
	    public String $;
	}

	public static class Jsessionid{
	    public String $;
	}
	
	
	private static class KID{
	    public String $;
	}
	
	public static class ResponseBody{
		@JsonProperty("callID")
	    public CallID callID;
		@JsonProperty("jsessionid")
	    public Jsessionid jsessionid;
		@JsonProperty("kID")
	    public KID kID;
		@JsonProperty("idusu")
	    public Idusu idusu;
		public CallID getCallID() {
			return callID;
		}
		public void setCallID(CallID callID) {
			this.callID = callID;
		}
		public Jsessionid getJsessionid() {
			return jsessionid;
		}
		
		public KID getkID() {
			return kID;
		}
		public void setkID(KID kID) {
			this.kID = kID;
		}
		public Idusu getIdusu() {
			return idusu;
		}
		public void setIdusu(Idusu idusu) {
			this.idusu = idusu;
		}
	}
	
	
//	public ResponseBody getResponseBody() {
//		return new ResponseBody();
//	}
//	
//	public Root getRoot(ResponseBody value) {
//		return new Root(value);
//	}
	
	public static class Root{
		@JsonProperty("responseBody")
	    public ResponseBody responseBody;
//	    public Root(ResponseBody response) {
//	    	this.responseBody = response;
//	    }

	}


}


//{
//	  "responseBody": {
//	    "callID": {
//	      "$": "6CA1F45E1E09D97FDE7D8951E3183CBC"
//	    },
//	    "jsessionid": {
//	      "$": "fe8pwN3buR0s4rgR45eVtYtanLEuuQ9BE8ZxGyAG"
//	    },
//	    "kID": {
//	      "$": "REEyRTJDNzM1MjlDMENEMDREREIwQ0E5NUU0NDZFQkU=\n"
//	    },
//	    "idusu": {
//	      "$": "MA==\n"
//	    }
//	  }
//	}