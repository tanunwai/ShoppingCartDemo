package com.tanunwai.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.tanunwai.beans.Message;

@Path("/gcdcode")
public class GcdCodeService {
	private @Context HttpServletRequest request;
	private Message msg=null;
	private Response response=null;
	
	@Path("/chkgcd/{pwd}/rawdata")
	@GET
	@Produces("application/json")
	public Response chkGcdCode(@PathParam("pwd")String gcdcode) {
		HttpSession session=request.getSession();
		String pwdGetBySession=(String)session.getAttribute("passwd");
		boolean isGcdCodeA=gcdcode.equals(pwdGetBySession);
		if(isGcdCodeA) {
			msg=new Message();
			msg.setCode(200);
			msg.setMsg("The correct code");
			response=Response.ok(msg).build();
		}else {
			msg=new Message();
			msg.setCode(400);
			msg.setMsg("Fill in the worong code");
			response=Response.status(400).entity(msg).build();
		}		
		return response;
	}
}