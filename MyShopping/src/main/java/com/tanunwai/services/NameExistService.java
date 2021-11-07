package com.tanunwai.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.tanunwai.beans.MembershipBeans;
import com.tanunwai.beans.Message;

@Path("/checkacc")
public class NameExistService {
	private @Context ServletContext app;
	private Response response=null;
	private Message msg=null;
	private MembershipBeans members=null;
	
	@Path("/chkacc/{ckna}/rawdata")
	@GET
	@Produces("application/json")
	public Response checkAcc(@PathParam("ckna")String userName) {
		DataSource dataSource=(DataSource)app.getAttribute("dataSource");
		try(Connection con=dataSource.getConnection()){
			String sql="select * from sakila.memberships where username=?";
			PreparedStatement prst=con.prepareStatement(sql);
			prst.setString(1, userName);
			ResultSet rs=prst.executeQuery();
			if(rs.next()) {
				members=new MembershipBeans();
				members.setMemberId(rs.getInt("memberid"));
				members.setUserName(rs.getString("username"));
				members.setLoginpass(rs.getString("password"));
				members.setRealName(rs.getString("realname"));
				members.setEmail(rs.getString("email"));
				members.setPhone(rs.getString("phone"));
				members.setSex(rs.getString("sex"));
				members.setBirthday(rs.getString("birthday"));
				members.setAddress(rs.getString("address"));
				response=Response.ok(members).build();
			}else {
				msg=new Message();
				msg.setCode(400);
				msg.setMsg(String.format("This account is legal and has not been used:%s", userName));
				response=Response.status(400).entity(msg).build();
			}
		}catch(SQLException e) {
			msg=new Message();
			msg.setCode(400);
			msg.setMsg("System problems;reason:"+e.getMessage());
			response=Response.status(200).entity(msg).build();
		}
		return response;
	}
}