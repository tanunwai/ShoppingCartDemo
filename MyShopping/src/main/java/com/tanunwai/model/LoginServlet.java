package com.tanunwai.model;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.tanunwai.beans.MembershipBeans;
import com.tanunwai.domain.IDao;
import com.tanunwai.domain.MembershipDao;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName=request.getParameter("userName");
		String loginPw=request.getParameter("loginPass");
		String msg=null;
		
		DataSource dataSource=(DataSource)this.getServletContext().getAttribute("dataSource");
		IDao<MembershipBeans,String> dao=new MembershipDao();
		try {
			dao.setDataSource(dataSource);
			MembershipBeans members=dao.selectObject(loginName+","+loginPw);
			if(members==null) {
				msg=String.format("%s;登入密碼或帳號錯誤", loginName);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
			}else {
				Cookie cred=new Cookie("Author",members.getUserName());
				cred.setHttpOnly(true);
				response.addCookie(cred);
				HttpSession session=request.getSession();
				String memberName=dao.selectForObject(loginName).stream().map(user->user.getRealName()).collect(Collectors.joining());
				int memberid=dao.selectForObject(loginName).stream().map(user->user.getMemberId()).findFirst().get();
				session.setAttribute("memberid", memberid);
				session.setAttribute("cred", members);						
				session.setAttribute("memberName", memberName);				
				msg=String.format("%s;登入成功", memberName);
				System.out.println("You have the memberName in loginServlet:"+members.getRealName());
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			msg=String.format("System problems;Beason:%s", e.getMessage());
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
		}
	}
}
