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
<title>Users Register Page</title>
<%@ include file="../includes/head.jsp" %>
<link rel="stylesheet" href="./css/_registerusers.css">
<script type="text/javascript" src="./js/regstValid.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
	<article class="card-body mx-auto" style="max-width: 400px;">
	<h4 class="card-title mt-3 text-center">Create Account</h4>
	<p class="text-center">Get started with your free account</p>	
	<form method="post" action="RegisterServlet" id="registForm">
		<table>
			<!-- form group userName -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-user"></i> </span>
						 </div>
						<input class="form-control" placeholder="User Account" name="userName" id="userName" type="text"/>
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="userNameError"></label>
				</td>
			</tr>
			<!-- form group password -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i> </span>
						</div>
						<input class="form-control" placeholder="Create password" name="loginPass" id="loginPass" type="password"/>						
					</div>					
				</td><!--  
					<td>
						<i class="far fa-eye" id="togglePassword"></i>
					</td>-->
				<td class="tdError"><label class="errorClass" id="loginPassError"></label>
				</td>
			</tr>
			<!-- form group re-password 
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i> </span>
						</div>
						<input class="form-control" placeholder="Repeat password" name="reloginPass" id="reloginPass" type="password"/>
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="reloginPassError"></label>
				</td>
			</tr>-->
			<!-- form group realName -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-users" aria-hidden="true"></i> </span>
						 </div>
						<input class="form-control" placeholder="Real Name" name="realName" id="realName" type="text"/>
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="realNameError"></label>
				</td>
			</tr>
			<!-- form group email -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
						 </div>
						<input class="form-control" placeholder="Email address" name="email" id="email" type="email"/>
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="emailError"></label>
				</td>
			</tr>
			<!-- form group phone -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-phone"></i> </span>
						</div>			
						<input class="form-control" placeholder="Phone number" name="phone" id="phone" type="text">
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="phoneError"></label>
				</td>
			</tr>
			<!-- form group sex -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-transgender" aria-hidden="true"></i> </span>
						</div> 
						<div class="wrapper">
						  <div class="toggle_radio">
							<input type="radio" class="toggle_option" id="female" name="sex" value="female">
							<input type="radio" checked="checked" class="toggle_option" id="man" name="sex" value="man">
							<label for="female">女性</label>
							<label for="man">男性</label>    
							<div class="toggle_option_slider">
						  </div>
						</div>	
					   </div>
					</div>
				</td>
				<!-- <td class="tdError"><label class="errorClass" id="sexError"></label>
				</td> -->
			</tr>
			<!-- form group GCD code -->
			<tr>
				<td>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-barcode" aria-hidden="true"></i> </span>
						</div>			
						<input class="form-control" placeholder="Valid Code" name="gcdCode" id="gcdCode" type="text">
					</div>
				</td>
				<td class="tdError"><label class="errorClass" id="gcdCodeError"></label>
				</td>
			</tr>
			<!-- change another GCD -->
			<tr>		
				<td><div>
				<img id="imgGcdCode" src="GCDCodeServlet"></div></td>
				<td>
					<div>													
						<p class="text-center">Change another GCD <a href="javascript:_gcdCodeChange()">刷新另一張</a></p>					
					</div>
				</td>										
			</tr>
			<!-- button for submit-->
			<tr>
				<td>
					<div class="form-group">
						<input type="submit" class="btn btn-primary btn-block" value="Create Account" />
					</div>
				</td>								
		    </tr>
		    <tr>		    
		    	<td><p>Have an account? <a href="_loginAction">Log In</a></p></td>
		    </tr>
		</table>                                                          
	</form>
	</article>	
<%@ include file="../includes/footer.jsp" %>	
</body>
</html>