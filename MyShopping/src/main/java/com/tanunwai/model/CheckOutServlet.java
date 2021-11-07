package com.tanunwai.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.tanunwai.beans.Cart;
import com.tanunwai.beans.MembershipBeans;
import com.tanunwai.beans.Order;
import com.tanunwai.domain.OrderDao;

@WebServlet("/cart_Check_Out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckOutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			SimpleDateFormat formatter=new SimpleDateFormat();
			Date createDate=new Date();
			@SuppressWarnings("unchecked")
			List<Cart> cart_List=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			MembershipBeans auth=(MembershipBeans)request.getSession().getAttribute("cred");
			if(cart_List != null && auth !=null) {
				int memberId=(int)request.getSession().getAttribute("memberid");
				for(Cart c:cart_List) {
					Order model=new Order();
					model.setId(c.getId());
					model.setUid(memberId);
					model.setQuantity(c.getQuantity());
					model.setCreateDate(formatter.format(createDate));
					
					OrderDao oDao=new OrderDao();
					DataSource dataSource=(DataSource)request.getServletContext().getAttribute("dataSource");
					oDao.setDataSource(dataSource);
					boolean result=oDao.insert(model);
					if(!result) break;
				}
				cart_List.clear();
				response.sendRedirect("_orderAction");
			}else {
				if(auth==null) {
					response.sendRedirect("_loginAction");
				}
				request.getRequestDispatcher("/WEB-INF/pages/product/cartProduct.jsp").include(request, response);
			}
		}catch(Exception e) {
			System.out.println("System Problem;Beacause:"+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
