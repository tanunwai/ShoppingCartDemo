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

@WebServlet("/_usersUpdate")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("loginPass");
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String address=request.getParameter("address");
		
		//System.out.println("來自更新的訊息:"+sex);	
		String msgF=null;		
		DataSource dataSource=(DataSource)this.getServletContext().getAttribute("dataSource");
		IDao<MembershipBeans,String> dao=new MembershipDao();
		
		int memberId=(int)request.getSession().getAttribute("memberid");
		
		MembershipBeans member=new MembershipBeans();
		member.setUserName(userName);
		member.setLoginpass(password);
		member.setRealName(realName);
		member.setEmail(email);
		member.setPhone(phone);
		member.setSex(sex);
		member.setBirthday(birthday);
		member.setAddress(address);
		member.setMemberId(memberId);
		
		try {
			dao.setDataSource(dataSource);
			boolean isUpdate=dao.update(member);
			if(isUpdate) {
				msgF=String.format("%s;You have successful Updata", member.getRealName());
				request.setAttribute("msg", msgF);
				request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			msgF=String.format("System problems;Beacuase:%s", e.getMessage());
			request.setAttribute("msg", msgF);
			request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);
		}
	}
}
