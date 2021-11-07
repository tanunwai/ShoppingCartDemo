package com.tanunwai.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tanunwai.beans.Cart;

@WebServlet("/add_to_cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter out=response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            //System.out.println("You have Product id:"+id);
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);
            //System.out.println("You have CartList:"+cartList.toString());
            HttpSession session = request.getSession();
            /*try to use Lambda*/
            //cartList.stream().flatMap(cart->Stream.of(session.getAttribute("cart-list")).filter(null));
            /*put the session history to CartList*/
            @SuppressWarnings("unchecked")
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cart_list == null) {
                cartList.add(cm);
                //System.out.println("You have the SessionList:"+cartList.toString());
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("IndexAction");
            } else {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='CartAction'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }
		}
	}
}
