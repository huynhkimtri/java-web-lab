<%-- 
    Document   : register
    Created on : Sep 15, 2020, 10:59:04 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign in - Social Network</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/register.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="register-page">
            <div class="auth-form mt-4">
                <div class="auth-form-header">
                    <h1>Sign up to Social Network</h1>
                </div>
                <c:set value="${requestScope.MSG_ERROR}" var="msg"/>
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger" role="alert">${msg}</div>
                </c:if>
                <div class="auth-form-body mt-3">
                    <form id="registerForm" action="MainController" method="post">
                        <c:set value="${requestScope.LASTED_EMAIL}" var="email"/>
                        <c:set value="${requestScope.LASTED_FULL_NAME}" var="fullName"/>

                        <label for="name">Full Name</label>
                        <input type="text" id="fullName" name="txtFullName" 
                               value="<c:if test="${not empty fullName}">${fullName}</c:if>"
                                   class="form-control input-block" required>

                               <label for="email">Email address</label>
                               <input type="email" id="email" name="txtEmail" 
                                      value="<c:if test="${not empty email}">${email}</c:if>"
                               class="form-control input-block" required>

                        <label for="password">Password (8 characters minimum)</label>
                        <input type="password" id="password" name="txtPassword" 
                               class="form-control input-block" minlength="8" required>

                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="txtConfirmPassword" 
                               class="form-control input-block" minlength="8" required>

                        <button type="submit" class="btn btn-primary btn-signup btn-block" 
                                name="action" value="register">Sign up</button>
                    </form>
                </div>
                <p class="signin-callout mt-3"><a href="login.jsp">I already have a membership.</a></p>
            </div>
        </div>
    </body>
</html>
