package com.tanunwai.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanunwai.beans.Cart;

@WebServlet("/remove_From_Cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveFromCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			String bookId = request.getParameter("id");
			if (bookId != null) {
				@SuppressWarnings("unchecked")
				List<Cart> cart_List = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if (cart_List != null) {
					for (Cart c : cart_List) {
						if (c.getId() == Integer.parseInt(bookId)) {
							cart_List.remove(cart_List.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("CartAction");
			} else {
				response.sendRedirect("CartAction");
			}
		}
	}
}
