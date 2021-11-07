package com.tanunwai.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.tanunwai.beans.MembershipBeans;
import com.tanunwai.domain.IDao;
import com.tanunwai.domain.MembershipDao;

@WebServlet("/RegisterServlet")
public class RegisterMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("loginPass");
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		
		//System.out.println("來自註冊的訊息:"+sex);
		
		MembershipBeans member=new MembershipBeans();
		member.setUserName(userName);
		member.setLoginpass(password);
		member.setRealName(realName);
		member.setEmail(email);
		member.setPhone(phone);
		member.setSex(sex);
		
		String msgE=null;		
		DataSource dataSource=(DataSource)this.getServletContext().getAttribute("dataSource");
		IDao<MembershipBeans,String> dao=new MembershipDao();		
		try {
			dao.setDataSource(dataSource);
			boolean isInsert=dao.insert(member);
			request.setAttribute("member", member);
			if(isInsert) {				
				msgE=String.format("%s,You have successful registered", member.getRealName());
				request.setAttribute("msg", msgE);
				request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);		
			}
		} catch (SQLException e) {
			msgE=String.format("System problems;reason:", e.getMessage());
			request.setAttribute("msg", msgE);
			request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
		}		
	}
}
