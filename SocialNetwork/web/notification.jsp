<%-- 
    Document   : home
    Created on : Sep 15, 2020, 11:02:18 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification - Social Network</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link href="css/fontawesome.min.css" rel="stylesheet">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user" />
        <c:if test="${not empty user}">
            <c:set value="${user.name}" var="fullName"/>
        </c:if>
        <!-- Navigation -->
        <%@include file="component/navigation.jspf" %>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <!-- Blog Entries Column -->
                <div class="col-lg-6 posts-listing align-middle">
                    <div class="container">
                        <div class="row">
                            <c:set value="${requestScope.LIST_NOTIFICATIONS}" var="listNotifications"/>
                            <c:if test="${not empty listNotifications}">
                                <c:forEach items="${listNotifications}" var="noti">
                                    <!-- post -->
                                    <div class="post">
                                        <div class="post-details">
                                            <div class="post-meta d-flex justify-content-between">
                                                <div class="date meta-last">
                                                    <f:formatDate type="both" timeStyle="short" dateStyle="short" 
                                                                  value="${noti.time}"/>
                                                    <a href="MainController?action=view&id=${noti.articleId.id}" 
                                                       class="author d-flex align-items-center flex-wrap">
                                                        <div class="title"><span>${noti.actor.name} ${noti.typeId.notiDescription}</span></div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty listNotifications}">
                                <h1>Notifications was found</h1>
                            </c:if>
                        </div>

                    </div>
                </div>

            </div>
        </div>

    </body>
</html>
