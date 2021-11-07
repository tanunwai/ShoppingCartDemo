package com.tanunwai.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

@WebServlet("/oder_Now")
public class OderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OderNowServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try(PrintWriter out= response.getWriter()){
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date =new Date();
			MembershipBeans auth=(MembershipBeans)request.getSession().getAttribute("cred");			
			if(auth != null) {
				int memberId=(int)request.getSession().getAttribute("memberid");
				//System.out.println("Your have the UserId:"+auth.getMemberId());
				//System.out.println("Your have the MemberId:"+memberId);
				
				String productId=request.getParameter("id");
				int productQuantity=Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity <=0) {
					productQuantity=1;
				}
				Order orderModel=new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(memberId);
				orderModel.setQuantity(productQuantity);
				orderModel.setCreateDate(formatter.format(date));
				
				DataSource dataSource=(DataSource)request.getServletContext().getAttribute("dataSource");
				
				OrderDao orderDao=new OrderDao();
				orderDao.setDataSource(dataSource);
				boolean affect=orderDao.insert(orderModel);
				if(affect) {
					@SuppressWarnings("unchecked")
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
					response.sendRedirect("_orderAction");
				}else {
					out.println("Order Fail");
				}
			}else {
				response.sendRedirect("_loginAction");
			}
			
		}catch(Exception e) {
			System.out.println("System Problem;Beacause:"+e.getMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request,response);
	}

}
