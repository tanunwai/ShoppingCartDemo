<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tanunwai.beans.MembershipBeans,java.util.ArrayList,com.tanunwai.beans.Cart" %>
<%
	MembershipBeans cred=(MembershipBeans)request.getSession().getAttribute("cred");
	if(cred != null){
		response.sendRedirect("index.jsp");
	}
	@SuppressWarnings("unchecked")
	ArrayList<Cart> oldListCart=(ArrayList<Cart>)session.getAttribute("cart-list");
	if(oldListCart != null){
		request.setAttribute("oldListCart", oldListCart);
	}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users Login Page</title>
<%@ include file="../includes/head.jsp" %>
<link rel="stylesheet" href="./css/_loginusers.css">
<script type="text/javascript" src="./js/loginValid.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
 <div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="user_card">
				<!-- Use a LOGO -->
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
						<img src="https://cdn.freebiesupply.com/logos/large/2x/pinterest-circle-logo-png-transparent.png" class="brand_logo" alt="Logo">
					</div>
				</div>
				<div class="d-flex justify-content-center form_container">
					<form method="post" action="loginservlet" id="lgoinForm">
						<!-- Users Account -->
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" name="userName" id="userName" placeholder="username">
							<label class="errorClass" id="userNameError"></label>
						</div>
						<!-- Users Password -->
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" name="loginPass" id="loginPass" placeholder="password">
							<label class="errorClass" id="loginPassError"></label>
					        <i class="far fa-eye" id="togglePassword"></i>
						</div>
						<!-- GCD Code -->
						<div class="input-group mb-1">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fa fa-barcode" aria-hidden="true"></i></span>
							</div>
							<input type="text" class="form-control valid_code" id="gcdCode" placeholder="Valid Code">
							<label class="errorClass" id="gcdCodeError"></label>
						</div>
						<!-- change another GCD -->
						<div>
							<img id="imgGcdCode" src="GCDCodeServlet">
						</div>
						<div>
							<p class="text-center">Change another GCD <a href="javascript:_gcdCodeChange()">刷新另一張</a></p>
						</div>
						<!-- Remember Password -->
						<div class="form-group">
							<!--  
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="customControlInline">
								<label class="custom-control-label" for="customControlInline">Remember me</label>
							</div>-->
						</div>
						<!-- Button Set -->
						<div class="d-flex justify-content-center mt-3 login_container">
				 			<button type="submit" name="button" class="btn login_btn">Login</button>
				   		</div>
					</form>
				</div>
				  
				<div class="mt-4">
					<div class="d-flex justify-content-center links">
						Don't have an account? <a href="_userRegisterAction" class="ml-2">Sign Up</a>
					</div>
					<!--
					<div class="d-flex justify-content-center links">
						<a href="#">Forgot your password?</a>
					</div>-->
				</div>
			</div>
		</div>
	</div>
<%@ include file="../includes/footer.jsp" %>
</body>
</html>