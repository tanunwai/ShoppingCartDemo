<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tanunwai.beans.MembershipBeans,java.text.DecimalFormat,java.util.List,javax.sql.DataSource,java.util.ArrayList,com.tanunwai.beans.Cart,com.tanunwai.domain.ProductDao" %>
<%
	MembershipBeans cred=(MembershipBeans)request.getSession().getAttribute("cred");
	if(cred != null){
		request.setAttribute("person", cred);
	}
	DataSource dataSource=(DataSource)request.getServletContext().getAttribute("dataSource");
	List<Cart> cartProducts=null;
	@SuppressWarnings("unchecked")
	ArrayList<Cart> oldListCart=(ArrayList<Cart>)session.getAttribute("cart-list");
	if(oldListCart != null){
		ProductDao pDao=new ProductDao();
		pDao.setDataSource(dataSource);
		cartProducts=pDao.getProducts(oldListCart);
		double total=pDao.getTotalPrice(oldListCart);
		request.setAttribute("total", total);
		request.setAttribute("oldListCart", oldListCart);
	}
	DecimalFormat dft=new DecimalFormat("#.##");
	request.setAttribute("dft", dft);
%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>this is Product cart</title>
<%@ include file="../../includes/head.jsp" %>
<link rel="stylesheet" href="./css/_cartstyle.css" />
<script src="./js/_cartProduct.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price:$ ${(total>0)?dft.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="cart_Check_Out">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<% if(oldListCart != null){
				for(Cart c:cartProducts){%>
					<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>$ <%=dft.format(c.getPrice())%></td>
					<td>
						<form method="post" action="oder_Now" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
							<a class="btn btn-sm btn-incre" href="quantity_Inc_Dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							<input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity()%>" readonly>
							<a class="btn btn-sm btn-decre" href="quantity_Inc_Dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td>
						<a class="btn btn-sm btn-danger" href="remove_From_Cart?id=<%=c.getId()%>">Remove</a>
					</td>
				</tr>
				<% }
				} %>
				
			</tbody>
		</table>		
	</div>
<%@ include file="../../includes/footer.jsp" %>
</body>
</html>