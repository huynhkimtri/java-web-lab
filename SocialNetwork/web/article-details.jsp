<%-- 
    Document   : article-details
    Created on : Sep 22, 2020, 10:50:11 AM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Article Details - Social Network</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/home.css" rel="stylesheet">
  </head>
  <body>
  <c:set value="${sessionScope.USER}" var="user"/>
  <c:if test="${not empty user}">
    <c:choose>
      <c:when test="${user.roleId.id == 1}">
        <c:redirect url="MainController?action=admin"/>
      </c:when>
      <c:when test="${user.roleId.id == 2}">
        <c:set value="${user.name}" var="fullName"/>
      </c:when>
    </c:choose>
  </c:if>
  <c:if test="${empty user}">
    <c:redirect url="MainController?action=login"/>
  </c:if>

  <!--Navigation-->
  <%@include file="component/navigation.jspf" %>
  <h1>This is article detail page!</h1>
</body>
</html>
