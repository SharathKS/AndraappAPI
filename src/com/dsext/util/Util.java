package com.dsext.util;

import javax.ws.rs.core.Response;

public class Util {

	public static Response authenticate(String username, String password){
		// Plug in authentication logic here
		if (username != null && password != null){
			return Response.ok().build();			
		}else{
			return Response.status(401).build();
		}		
	}
}
