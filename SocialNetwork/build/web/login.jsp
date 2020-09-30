<%-- 
    Document   : login
    Created on : Sep 15, 2020, 10:56:56 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Sign in - Social Network</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
  </head>
  <body>
    <c:set value="${sessionScope.USER}" var="user"/>
    <c:if test="${not empty user.email}">
      <c:choose>
        <c:when test="${user.role eq 'admin'}">
          <c:redirect url="admin.jsp"/>
        </c:when>
        <c:otherwise>
          <c:redirect url="MainController?action=home"/>
        </c:otherwise>
      </c:choose>
    </c:if>
    <div class="signin-page">
      <div class="auth-form mt-4">
        <div class="auth-form-header">
          <h1>Sign in to Social Network</h1>
        </div>
        <c:set value="${requestScope.MSG_ERROR}" var="msg"/>
        <c:set value="${requestScope.LASTED_EMAIL}" var="lastedEmail"/>
        <c:if test="${not empty msg}">
          <div class="alert alert-danger" role="alert">${msg}</div>
        </c:if>
        <div class="auth-form-body mt-3">
          <form action="MainController" method="post">
            <label for="email">Email address</label>
            <c:if test="${not empty lastedEmail}">
              <input type="email" id="email" name="txtEmail" 
                     value="${lastedEmail}"
                     class="form-control input-block" required autofocus>
            </c:if>
            <c:if test="${empty lastedEmail}">
              <input type="email" id="email" name="txtEmail" 
                     class="form-control input-block" required autofocus>
            </c:if>
            <label for="password">Password<a class="label-link" href="/password_reset">Forgot password?</a></label>
            <input type="password" id="password" name="txtPassword" 
                   class="form-control input-block" minlength="8" required>
            <button type="submit" class="btn btn-primary btn-signin btn-block" name="action" value="login">Sign in</button>
          </form>
        </div>
        <p class="create-account-callout mt-3">New to SimpleBlog? <a href="register.jsp">Create an account.</a></p>
      </div>
    </div>
  </body>
</html>
