<%-- 
    Document   : home
    Created on : Sep 15, 2020, 11:02:18 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home - Social Network</title>
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
        <div class="col-lg-8 posts-listing">
          <div class="container">
            <div class="row">
              <c:set value="${requestScope.LIST_ARTILCES}" var="listArticles"/>
              <c:if test="${not empty listArticles}">
                <c:forEach items="${listArticles}" var="article">
                  <!-- post -->
                  <div class="post">
                    <div class="post-details">
                      <div class="post-meta d-flex justify-content-between">
                        <div class="date meta-last">${article.createdDate}</div>
                      </div><a href="MainController?action=view&id=${article.id}">
                        <h3 class="h4">${article.title}</h3></a>
                      <p class="text-muted">${article.description}</p>
                      <footer class="post-footer d-flex align-items-center">
                        <div class="comments meta-last mr-2">
                          <c:set value="${article.numOfLike}" var="like"/>
                          <button type="button" class="btn btn-outline-success btn-sm mr-1">
                            <c:if test="${like == 0}">Like</c:if>
                            <c:if test="${like > 0}">${like} like</c:if>
                            </button>
                          <c:set value="${article.numOfDislike}" var="dislike"/>
                          <button type="button" class="btn btn-outline-secondary btn-sm">
                            <c:if test="${dislike == 0}">Dislike</c:if>
                            <c:if test="${dislike > 0}">${dislike} dislike</c:if>
                            </button>
                          </div>
                          <a href="#" class="author d-flex align-items-center flex-wrap">
                            <div class="title"><span>${article.authorEmail.name}</span></div>
                        </a>
                      </footer>
                    </div>
                  </div>
                </c:forEach>
              </c:if>
            </div>
            <!-- Pagination -->
            <nav aria-label="Page navigation example">
              <ul class="pagination pagination-template d-flex justify-content-center">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /.Blog Entries Column -->

        <!-- Sidebar Widgets Column -->
        <aside class="col-md-4">
          <!-- Search Widget -->
          <div class="widget search">
            <header>
              <h3 class="h6">Search the Article</h3>
            </header>
            <form action="#" class="search-form">
              <div class="form-group">
                <input type="search" placeholder="What are you looking for?">
                <button type="submit" class="submit"><i class="icon-search"></i></button>
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
    <!-- /.container -->

    <!-- Footer -->
    <%--<%@include file="component/footer.jspf" %>--%>
    <!--<script src="js/jquery.min.js"></script>-->
  </body>
</html>
