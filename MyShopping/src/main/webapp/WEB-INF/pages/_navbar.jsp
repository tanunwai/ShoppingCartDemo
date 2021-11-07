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
<html >
<head>
<meta charset="UTF-8">
<%@ include file="../includes/head.jsp" %>
<link rel="stylesheet" href="./css/_navbar.css">
</head>
<body>
<div class="navbar navbar-expand-md navbar-dark bg-dark mb-4" role="navigation">
    <a class="navbar-brand" href="IndexAction">E-Commerce-Shopping</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>	
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav ml-auto">
			<!--group Home-->
			<li class="nav-item">			
		        <a class="nav-link" href="IndexAction">
		          <i class="fa fa-home" aria-hidden="true">
		            <span class="badge badge-info"></span>
		          </i>
		          HOME
		        </a>
		    </li>
		    <% if(cred != null){%>
		    <!-- logout group -->
	    	<li class="nav-item">			
	        <a class="nav-link" href="log-out">
	          <i class="fas fa-running" aria-hidden="true">
	            <span class="badge badge-info"><%=session.getAttribute("memberName")%></span>
	          </i>
	          Logout
	        </a>
		    </li>
		    <!--group Update-->	
	        <li class="nav-item">			
		        <a class="nav-link" href="_updateAction">
		          <i class="fa fa-id-badge" aria-hidden="true">
		            <span class="badge badge-info"></span>
		          </i>
		          Update
		        </a>
		    </li>
		    <!--group Message-->
	       <li class="nav-item">			
	        <a class="nav-link" href="_orderAction">
	          <i class="fa fa-bell">
	            <span class="badge badge-info">${orders.size()}</span>
	          </i>
	          OrderList
	        </a>
	      </li>
		    <% }else{%>
		    <!--group login-->
			<li class="nav-item">			
		        <a class="nav-link" href="_loginAction">
		          <i class="fa fa-users" aria-hidden="true">
		            <span class="badge badge-info"></span>
		          </i>
		          Login
		        </a>
		    </li>
		    <% } %>    		    		    		    
		   <!--group DropdownMemu
		   <li class="nav-item dropdown">
		   
		   <a class="nav-link dropdown-toggle" href="_loginAction" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fa fa-users">
	            <span class="badge badge-primary"></span>
	          </i>
	          Login
	       </a>
		   
		   		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <a class="dropdown-item" href="_updateAction">Update
		            <i class="fa fa-id-badge" aria-hidden="true">
			             <span class="badge badge-info"></span>
			        </i></a>
		            <a class="dropdown-item" href="log-out">Logout
		            <i class="fa fa-user" aria-hidden="true">
			            <span class="badge badge-info"></span>
			        </i></a>
		            <div class="dropdown-divider"></div>
		            <a class="dropdown-item" href="#">Something else here</a>
	            </div>
	      </li>-->                      
	       
		  <!--group Shopping-Cart-->
	      <li class="nav-item">
	        <a class="nav-link" href="CartAction">
	            <i class="fa fa-shopping-cart" aria-hidden="true">        
	            <span class="badge badge-danger px-1">${oldListCart.size()}</span>
	          </i>
	          Shopping-Cart
	        </a>
	      </li>
	 </ul><!-- 
	        <form class="form-inline mt-2 mt-md-0">
	            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
	            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	        </form> -->
	 </div>
</div>
<%@ include file="../includes/footer.jsp" %>
</body>
</html>