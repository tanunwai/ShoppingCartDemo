<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tanunwai.beans.MembershipBeans,com.tanunwai.beans.Cart,java.util.*,com.tanunwai.domain.ProductDao,javax.sql.DataSource,com.tanunwai.beans.Product" %>
<%
MembershipBeans cred=(MembershipBeans)request.getSession().getAttribute("cred");
if(cred != null){
	request.setAttribute("cred", cred);	
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
<meta charset="UTF-8">
<%@ include file="../includes/head.jsp" %>
<title>this is a Result page</title>
<link rel="stylesheet" href="./css/ResultRegister.css">
</head>
<body>
<jsp:include page="/WEB-INF/pages/_navbar.jsp"></jsp:include>
 <div class="error-content">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ">
                <div class="error-text">
                    <h1 class="error">${msg}</h1>
                    <div class="im-sheep">
                        <div class="top">
                            <div class="body"></div>
                            <div class="head">
                                <div class="im-eye one"></div>
                                <div class="im-eye two"></div>
                                <div class="im-ear one"></div>
                                <div class="im-ear two"></div>
                            </div>
                        </div>
                        <div class="im-legs">
                            <div class="im-leg"></div>
                            <div class="im-leg"></div>
                            <div class="im-leg"></div>
                            <div class="im-leg"></div>
                        </div>
                    </div>
                    <c:if test="${msg.length()==0}">
                    <h4>Oops! This page Could Not Be Found!</h4>
                    <p>Sorry bit the page you are looking for does not exist, have been removed or name changed.</p>
                    </c:if>
                    <a href="index.jsp" class="btn btn-primary btn-round">Go to HOME</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../includes/footer.jsp" %>
</body>
</html>