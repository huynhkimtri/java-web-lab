<%-- 
    Document   : article-details
    Created on : Sep 22, 2020, 10:50:11 AM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
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
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <!-- Blog Entries Column -->
                <div class="post blog-post col-lg-8">
                    <div class="container">
                        <div class="row">
                            <c:set var="article" value="${requestScope.ARTICLE}"/>
                            <c:if test="${not empty article}">
                                <div class="post">
                                    <div class="post-details">
                                        <div class="post-meta d-flex justify-content-between">
                                            <div class="date meta-last">
                                                <f:formatDate type="both" timeStyle="short" dateStyle="medium" 
                                                              value="${article.createdDate}"/>
                                                <a href="#" class="author d-flex align-items-center flex-wrap">
                                                    <div class="title"><span>${article.authorEmail.name}</span></div>
                                                </a>
                                            </div>

                                        </div>
                                        <a href="MainController?action=view&id=${article.id}">
                                            <h3 class="h4">${article.title}</h3></a>
                                            <c:if test="${not empty article.imageUrl}">
                                            <div>
                                                <img src="${article.imageUrl}" width="100%"/>
                                            </div>
                                        </c:if>
                                        <p class="text-muted">${article.description}</p>
                                        <p class="text-muted">${article.contents}</p>
                                        <footer class="post-footer d-flex align-items-center">
                                            <div class="comments meta-last mr-2">
                                                <c:set value="${article.numOfLike}" var="like"/>
                                                <button type="button" class="btn btn-outline-success btn-sm mr-1"
                                                        onclick="like(${article.id}, '${article.authorEmail.email}')">
                                                    <c:if test="${like == 0}">Like</c:if>
                                                    <c:if test="${like > 0}">${like} Like</c:if>
                                                    </button>
                                                <c:set value="${article.numOfDislike}" var="dislike"/>
                                                <button type="button" class="btn btn-outline-secondary btn-sm"
                                                        onclick="dislike(${article.id}, '${article.authorEmail.email}')">
                                                    <c:if test="${dislike == 0}">Dislike</c:if>
                                                    <c:if test="${dislike > 0}">${dislike} Dislike</c:if>
                                                    </button>
                                                <c:if test="${article.authorEmail.email eq user.email and user.roleId.id eq 2}">
                                                    <button type="button" class="btn btn-danger btn-sm"
                                                            onclick="deleteArticle(${article.id})">Delete</button>
                                                </c:if>
                                            </div>
                                        </footer>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${empty article}">
                                <h3>This article does not exist</h3>
                            </c:if>
                        </div>
                    </div>
                    <div class="post-comments">
                        <c:set var="listComments" value="${requestScope.COMMENTS}"/>
                        <c:if test="${not empty listComments}">
                            <header>
                                <h3 class="h6">Post Comments<span class="no-of-comments">(${listComments.size()})</span></h3>
                            </header>
                            <c:forEach items="${listComments}" var="comment"> 
                                <div class="comment">
                                    <div class="comment-header d-flex justify-content-between">
                                        <div class="user d-flex align-items-center">
                                            <div class="image"><img src="https://d19m59y37dris4.cloudfront.net/blog/1-2-1/img/user.svg" alt="..." class="img-fluid rounded-circle"></div>
                                            <div class="title">
                                                <strong>${comment.ownerEmail.name}</strong>
                                                <span class="date">
                                                    <f:formatDate type="both" timeStyle="short" dateStyle="medium" 
                                                                  value="${comment.time}"/>
                                                </span>
                                                <c:if test="${comment.ownerEmail.email eq user.email}">
                                                    <button type="button" class="btn btn-danger btn-sm"
                                                            onclick="deleteComment(${comment.id})">Delete</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="comment-body">
                                        <p>${comment.contents}</p>
                                    </div>
                                </div>
                            </c:forEach>           
                        </c:if>
                        <c:if test="${empty listComments}">
                            <header>
                                <h3 class="h6">Post Comments</h3>
                            </header>
                        </c:if>
                    </div>
                    <c:if test="${not empty user and user.roleId.id == 2 and not empty article}">
                        <div class="add-comment">
                            <header>
                                <h3 class="h6">Leave a reply</h3>
                            </header>
                            <form action="MainController" method="POST" class="commenting-form">
                                <div class="row">
                                    <div class="form-group col-md-12"><grammarly-extension style="position: absolute; top: 0px; left: 0px; pointer-events: none;" class="_1KJtL"></grammarly-extension>
                                        <textarea name="comment" id="usercomment" required maxlength="500" placeholder="Type your comment" class="form-control" spellcheck="false"></textarea>
                                        <input type="hidden" name="articleId" value="${article.id}"/>
                                        <input type="hidden" name="notifierEmail" value="${article.authorEmail.email}"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="submit" name="action" value="comment" class="btn btn-secondary">Submit Comment</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
                <!-- /.Blog Entries Column -->

                <!-- Sidebar Widgets Column -->
                <aside class="col-md-4">
                    <!-- Search Widget -->
                    <div class="widget search">
                        <header>
                            <h3 class="h6">Search the Article</h3>
                        </header>
                        <form action="MainController" method="GET" class="search-form">
                            <div class="form-group">
                                <input type="search" name="keyword" placeholder="What are you looking for?">
                                <button type="submit" value="search" name="action" class="submit"><i class="icon-search"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="widget latest-posts">
                        <header>
                            <h3 class="h6">Latest Articles</h3>
                        </header>
                        <div class="blog-posts"><a href="#">
                                <div class="item d-flex align-items-center">
                                    <div class="image"><img src="https://d19m59y37dris4.cloudfront.net/blog/1-2-1/img/small-thumbnail-1.jpg" alt="..." class="img-fluid"></div>
                                    <div class="title"><strong>Alberto Savoia Can Teach You About</strong>
                                        <div class="d-flex align-items-center">
                                            <div class="views"><i class="icon-eye"></i> 500</div>
                                            <div class="comments"><i class="icon-comment"></i>12</div>
                                        </div>
                                    </div>
                                </div></a><a href="#">
                                <div class="item d-flex align-items-center">
                                    <div class="image"><img src="https://d19m59y37dris4.cloudfront.net/blog/1-2-1/img/small-thumbnail-2.jpg" alt="..." class="img-fluid"></div>
                                    <div class="title"><strong>Alberto Savoia Can Teach You About</strong>
                                        <div class="d-flex align-items-center">
                                            <div class="views"><i class="icon-eye"></i> 500</div>
                                            <div class="comments"><i class="icon-comment"></i>12</div>
                                        </div>
                                    </div>
                                </div></a><a href="#">
                                <div class="item d-flex align-items-center">
                                    <div class="image"><img src="https://d19m59y37dris4.cloudfront.net/blog/1-2-1/img/small-thumbnail-3.jpg" alt="..." class="img-fluid"></div>
                                    <div class="title"><strong>Alberto Savoia Can Teach You About</strong>
                                        <div class="d-flex align-items-center">
                                            <div class="views"><i class="icon-eye"></i> 500</div>
                                            <div class="comments"><i class="icon-comment"></i>12</div>
                                        </div>
                                    </div>
                                </div></a></div>
                    </div>
                    <!-- /.Search Widget -->
                </aside>
            </div>
            <!-- /.row -->
        </div>
        <script>
            const like = function (id, email) {
                const btn = document.getElementsByClassName("btn btn-outline-success");
                btn.className += " active";
                const url = new URL(this.document.URL);
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.location.reload();
                    }
                };
                const uri = url.origin + url.pathname
                        + "?action=like&id=" + id
                        + "&notifierEmail=" + email;
                xhttp.open("POST", uri, true);
                xhttp.send();
            };
            const dislike = function (id, email) {
                const btn = document.getElementsByClassName("btn btn-outline-secondary");
                btn.className += " active";
                const url = new URL(this.document.URL);
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.location.reload();
                    }
                };
                const uri = url.origin + url.pathname
                        + "?action=dislike&id=" + id
                        + "&notifierEmail=" + email;
                xhttp.open("POST", uri, true);
                xhttp.send();
            };
            const deleteComment = function (id) {
                const url = new URL(this.document.URL);
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.location.reload();
                    }
                };
                const uri = url.origin + url.pathname
                        + "?action=delCmt&id=" + id;
                const c = confirm("Are you sure to delete this comment?");
                if (c) {
                    xhttp.open("POST", uri, true);
                    xhttp.send();
                }
            };
            const deleteArticle = function (id) {
                const url = new URL(this.document.URL);
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.location.href = 'http://localhost:8080/SocialNetwork/'
                    }
                };
                const uri = url.origin + url.pathname
                        + "?action=delArt&id=" + id;
                const c = confirm("Are you sure to delete this article?");
                if (c) {
                    xhttp.open("POST", uri, true);
                    xhttp.send();
                }
            };
        </script>
    </body>
</html>
