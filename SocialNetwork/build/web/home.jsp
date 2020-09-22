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
        <title>Home · Social Network</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <!--<script src="js/bootstrap.min.js" ></script>-->
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user" />
        <c:if test="${not empty user}">
            <c:set value="${user.name}" var="fullName"/>
        </c:if>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container">
                <a class="navbar-brand h1" href="#">Mini Social Network</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="MainController?action=home">
                                <i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <c:if test="${user.roleId.id == 2}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=write-article">
                                    <i class="fas fa-edit"></i> Write
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${user.roleId.id == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=admin">
                                    <i class="fas fa-tasks"></i> Management
                                </a>
                            </li>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty fullName}">
                                <li class="nav-item">
                                    <div class="dropdown">
                                        <a class="nav-link dropdown-toggle" 
                                           href="#" role="button" id="dropdownMenuLink" 
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            ${fullName}
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <c:set value="MainController?action=logout" var="url"/>
                                            <a class="dropdown-item" href="${url}" >Sign out</a>
                                        </div>
                                    </div> 
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="login.jsp">Sign in</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="register.jsp">Sign up</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <!-- Blog Entries Column -->
                <div class="col-lg-8 posts-listing">
                    <div class="container">
                        <div class="row">
                            <!-- post -->
                            <div class="post">
                                <div class="post-details">
                                    <div class="post-meta d-flex justify-content-between">
                                        <div class="date meta-last">20 May | 2016</div>
                                    </div><a href="post.html">
                                        <h3 class="h4">Alberto Savoia Can Teach You About Interior</h3></a>
                                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
                                    <footer class="post-footer d-flex align-items-center"><a href="#" class="author d-flex align-items-center flex-wrap">
                                            <div class="title"><span>John Doe</span></div></a>
                                        <div class="date"><i class="icon-clock"></i> 2 months ago</div>
                                        <div class="comments meta-last"><i class="icon-comment"></i>12</div>
                                    </footer>
                                </div>
                            </div>
                            <!-- post             -->
                            <div class="post ">
                                <div class="post-details">
                                    <div class="post-meta d-flex justify-content-between">
                                        <div class="date meta-last">20 May | 2016</div>
                                    </div><a href="post.html">
                                        <h3 class="h4">Alberto Savoia Can Teach You About Interior</h3></a>
                                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
                                    <div class="post-footer d-flex align-items-center"><a href="#" class="author d-flex align-items-center flex-wrap">
                                            <div class="title"><span>John Doe</span></div></a>
                                        <div class="date"><i class="icon-clock"></i> 2 months ago</div>
                                        <div class="comments meta-last"><i class="icon-comment"></i>12</div>
                                    </div>
                                </div>
                            </div>
                            <!-- post             -->
                            <div class="post ">
                                <div class="post-details">
                                    <div class="post-meta d-flex justify-content-between">
                                        <div class="date meta-last">20 May | 2016</div>
                                    </div><a href="post.html">
                                        <h3 class="h4">Alberto Savoia Can Teach You About Interior</h3></a>
                                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
                                    <div class="post-footer d-flex align-items-center"><a href="#" class="author d-flex align-items-center flex-wrap">
                                            <div class="title"><span>John Doe</span></div></a>
                                        <div class="date"><i class="icon-clock"></i> 2 months ago</div>
                                        <div class="comments meta-last"><i class="icon-comment"></i>12</div>
                                    </div>
                                </div>
                            </div>
                            <!-- post -->
                            <div class="post ">
                                <div class="post-details">
                                    <div class="post-meta d-flex justify-content-between">
                                        <div class="date meta-last">20 May | 2016</div>
                                    </div><a href="post.html">
                                        <h3 class="h4">Alberto Savoia Can Teach You About Interior</h3></a>
                                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
                                    <div class="post-footer d-flex align-items-center"><a href="#" class="author d-flex align-items-center flex-wrap">
                                            <div class="title"><span>John Doe</span></div></a>
                                        <div class="date"><i class="icon-clock"></i> 2 months ago</div>
                                        <div class="comments meta-last"><i class="icon-comment"></i>12</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-template d-flex justify-content-center">
                                <li class="page-item"><a href="#" class="page-link"> <i class="fa fa-angle-left"></i></a></li>
                                <li class="page-item"><a href="#" class="page-link active">1</a></li>
                                <li class="page-item"><a href="#" class="page-link">2</a></li>
                                <li class="page-item"><a href="#" class="page-link">3</a></li>
                                <li class="page-item"><a href="#" class="page-link"> <i class="fa fa-angle-right"></i></a></li>
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
