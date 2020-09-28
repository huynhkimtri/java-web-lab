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
        <div class="col-lg-6 posts-listing">
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
                                        value="${article.createdDate}"/>
                          <a href="#" class="author d-flex align-items-center flex-wrap">
                            <div class="title"><span>${article.authorEmail.name}</span></div>
                          </a>
                        </div>
                      </div><a href="MainController?action=view&id=${article.id}">
                        <h3 class="h4">${article.title}</h3></a>
                      <p class="text-muted">${article.description}</p>
                      <footer class="post-footer d-flex align-items-center">
                        <div class="comments meta-last mr-2">
                          <c:set value="${article.numOfLike}" var="like"/>
                          <button type="button" class="btn btn-outline-success btn-sm mr-1" onclick="like(${article.id})">
                            <c:if test="${like == 0}">Like</c:if>
                            <c:if test="${like > 0}">${like} Like</c:if>
                            </button>
                          <c:set value="${article.numOfDislike}" var="dislike"/>
                          <button type="button" class="btn btn-outline-secondary btn-sm" onclick="dislike(${article.id})">
                            <c:if test="${dislike == 0}">Dislike</c:if>
                            <c:if test="${dislike > 0}">${dislike} Dislike</c:if>
                            </button>
                          </div>

                        </footer>
                      </div>
                    </div>
                </c:forEach>
              </c:if>
            </div>

          </div>
        </div>
        <!-- /.Blog Entries Column -->


      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->

  </body>
</html>
