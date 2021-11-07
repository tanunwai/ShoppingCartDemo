<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CarouselPage</title>
<link rel="stylesheet" href='<c:url value="/css/_testCarousel2.css" />' >
</head>
<body>
<div class="carousel-wrapper">
    <span id="item-1"></span>
    <span id="item-2"></span>
    <span id="item-3"></span>
    <div class="carousel-item item-1">
      <a href="#item-3" class="arrow-prev arrow"></a>
      <a href="#item-2" class="arrow-next arrow"></a>
    </div>

    <div class="carousel-item item-2">
      <a href="#item-1" class="arrow-prev arrow"></a>
      <a href="#item-3" class="arrow-next arrow"></a>
    </div>

    <div class="carousel-item item-3">
      <a href="#item-2" class="arrow-prev arrow"></a>
      <a href="#item-1" class="arrow-next arrow"></a>
    </div>

  </div>
</body>
</html>