package com.tanunwai.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.tanunwai.domain.OrderDao;

@WebServlet("/cancel_Order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CancelOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			DataSource dataSoruce=(DataSource)request.getServletContext().getAttribute("dataSource");
			String id=request.getParameter("id");
			if(id != null) {
				OrderDao oDao=new OrderDao();
				oDao.setDataSource(dataSoruce);
				oDao.cancelOrder(Integer.parseInt(id));
			}
			response.sendRedirect("CartAction");
		}catch(Exception e) {
			System.out.println("Error Problem in CalSerlet;Beacuase:"+e.getMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
