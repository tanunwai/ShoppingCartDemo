<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tanunwai.domain.OrderDao,javax.sql.DataSource,com.tanunwai.beans.Order,java.util.List,java.text.DecimalFormat,com.tanunwai.beans.MembershipBeans,java.util.ArrayList,com.tanunwai.beans.Cart" %>
<%	
	MembershipBeans cred=(MembershipBeans)request.getSession().getAttribute("cred");
	List<Order> orders=null;
	DataSource dataSource=(DataSource)request.getServletContext().getAttribute("dataSource");
	if(cred != null){
		request.setAttribute("person", cred);
		int memberId=(int)request.getSession().getAttribute("memberid");
		OrderDao oDao=new OrderDao();
		oDao.setDataSource(dataSource);
		orders=oDao.selectForObject(String.valueOf(memberId));
		request.setAttribute("orders", orders);
		double ordersTotal=oDao.getTotalOrderPrice(orders);
		request.setAttribute("ordersTotal", ordersTotal);
	}else{
		response.sendRedirect("_loginAction");
	}
	@SuppressWarnings("unchecked")
	ArrayList<Cart> oldListCart=(ArrayList<Cart>)session.getAttribute("cart-list");
	if(oldListCart != null){
		request.setAttribute("oldListCart", oldListCart);
	}
	DecimalFormat dft=new DecimalFormat("#.##");
	request.setAttribute("dft", dft);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders page</title>
<%@ include file="../../includes/head.jsp" %>
</head>
<body>
<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
	<div class="container">
		<div class="card-header my-3">All Order Here</div>
		<div class="d-flex py-3">
			<h3>Total Price:$ ${(ordersTotal>0)?dft.format(ordersTotal):0}</h3>
			<a class="mx-3 btn btn-primary" href="cart_Check_Out">Payment</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%if(orders != null){
				for(Order o:orders){%>
					<tr>
					<td><%=o.getCreateDate()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getCategory()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=dft.format(o.getPrice())%></td>
					<td><a class="btn btn-sm btn-danger" href="cancel_Order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
					</tr>
				<%}
				  }%>				
			</tbody>
		</table>
	</div>
<%@ include file="../../includes/footer.jsp" %>
</body>
</html>