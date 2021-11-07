<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tanunwai.beans.MembershipBeans,com.tanunwai.beans.Cart,java.util.*,com.tanunwai.domain.ProductDao,javax.sql.DataSource,com.tanunwai.beans.Product" %>
<%
MembershipBeans cred=(MembershipBeans)request.getSession().getAttribute("cred");
if(cred != null){
	request.setAttribute("person", cred);	
}
DataSource dataSource=(DataSource)request.getServletContext().getAttribute("dataSource");
ProductDao pDao=new ProductDao();
pDao.setDataSource(dataSource);
List<Product> products=pDao.getAllObject();
@SuppressWarnings("unchecked")
ArrayList<Cart> oldListCart=(ArrayList<Cart>)session.getAttribute("cart-list");
if(oldListCart != null){
	request.setAttribute("oldListCart", oldListCart);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/includes/head.jsp" %>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
	<div class="container">
		<div class="card-header my-3 ">All Products</div>
		<div class="row">
		<% if(!products.isEmpty()){
			for(Product p:products){%>
				<div class="col-md-3">
				<div class="card w-100" style="width:18rem">
				<img src="product_images/<%=p.getImages()%>" class="card-img-top">
				<div class="card-body">
					<h5 id="name" class="card-title"><%=p.getName()%></h5>
				    <h6 id="price" class="price">Prices:<%=p.getPrice()%></h6>
				    <h6 id="category" class="category">Category:<%=p.getCategory()%></h6>
				    <div class="mt-3 d-flex justify-content-between">
				    	<a href="add_to_cart?id=<%=p.getId()%>" class="btn btn-primary">Add to Cart</a>
				    	<a href="oder_Now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy Now</a>
				    </div>
				</div>				
				</div>
			</div>
			<%}
			  }%>			
		</div>	
	</div>
<%@ include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>