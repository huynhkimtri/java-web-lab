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
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/home.css" rel="stylesheet">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user" />
        <c:if test="${not empty user}">
            <c:set value="${user.firstName}" var="firstName"/>
            <c:set value="${user.lastName}" var="lastName"/>
        </c:if>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container">
                <a class="navbar-brand h1" href="#">MSN</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="MainController?action=home"><i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <c:if test="${user.role eq 'member'}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=write-blog"><i class="fas fa-edit"></i> Write
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${user.role eq 'admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=admin"><i class="fas fa-tasks"></i> Management
                                </a>
                            </li>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty firstName and not empty lastName}">
                                <li class="nav-item">
                                    <div class="dropdown">
                                        <a class="nav-link dropdown-toggle" 
                                           href="#" role="button" id="dropdownMenuLink" 
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ${firstName}&nbsp;${lastName}
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
                <div class="col-md-8">
                    <!-- List Articles --> 
                    <c:set value="${requestScope.LIST_ARTICLES}" var="listArticles"/>
                    <c:if test="${not empty listArticles}">
                        <c:forEach var="article" items="${listArticles}">
                            <div class="card mb-4 mt-4">
                                <div class="card-body">
                                    <h2 class="card-title">${article.title}</h2>
                                    <p class="card-text">${article.description}</p>

                                    <a href="MainController?action=view&id=${article.id}" class="btn btn-outline-primary">
                                        <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>Read More &rarr;</a>
                                </div>
                                <div class="card-footer text-muted">
                                    Published at ${article.publishedDate} by
                                    <a href="#"><strong>${article.authorFirstName}&nbsp;${article.authorLastName}</strong></a>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center mb-4">
                                <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numberOfPages" />
                                <!--For displaying Older link-->
                                <c:choose>
                                    <c:when test="${pageNummber gt 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber - 1}&cbxStatus=&role=member&txtSearchValue=" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!--For displaying Newer link--> 
                                <c:choose>
                                    <c:when test="${pageNummber lt numberOfPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber + 1}&cbxStatus=&role=member&txtSearchValue=">Newer &rarr;</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">Newer &rarr;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </c:if>
                    <!-- /.List Articles -->
                    <!-- List Search Results -->
                    <c:set value="${requestScope.LIST_SEARCH_RESULTS}" var="listSearchResults"/>
                    <c:if test="${not empty listSearchResults}">
                        <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                        <c:if test="${not empty searchValue}">
                            <h5 class="my-4">Results for keyword search: <i style="color: #0077f2">${searchValue}</i></h5>
                            </c:if>
                            <c:forEach var="result" items="${listSearchResults}">
                            <div class="card mb-4 mt-4">
                                <div class="card-body">
                                    <h2 class="card-title">${result.title}</h2>
                                    <p class="card-text">${result.description}</p>
                                    <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>
                                    <a href="MainController?action=view&id=${result.id}" class="btn btn-outline-primary">Read More &rarr;</a>
                                </div>
                                <div class="card-footer text-muted">
                                    Published ${result.publishedDate} by
                                    <a href="#"><strong>${result.authorFirstName}&nbsp;${result.authorLastName}</strong></a>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center mb-4">
                                <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numberOfPages" />
                                <!--For displaying Older link-->
                                <c:choose>
                                    <c:when test="${pageNummber gt 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber - 1}&cbxStatus=${param.cbxStatus}&role=member&txtSearchValue=${param.txtSearchValue}" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!--For displaying Newer link--> 
                                <c:choose>
                                    <c:when test="${pageNummber lt numberOfPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber + 1}&cbxStatus=${param.cbxStatus}&role=member&txtSearchValue=${param.txtSearchValue}">Newer &rarr;</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">Newer &rarr;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${empty listSearchResults}">
                        <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                        <c:if test="${not empty searchValue}">
                            <h5 class="my-4">Sorry! We could not find any results for keyword search <i style="color: #0077f2">${searchValue}</i></h5>
                            </c:if>
                        </c:if>
                    <!-- /.List Search Results -->
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
                    <!--                    <div class="card my-4 search-card">
                                            <h5 class="card-header">Search the article</h5>
                                            <div class="card-body">
                                                <form action="MainController" method="get">
                                                    <div class="input-group">
                    <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                    <input class="form-control" id="searchValue" type="text" 
                    <c:if test="${not empty searchValue}">value="${searchValue}"</c:if>
                        placeholder="What are you looking for?" aria-label="Search"
                        name="txtSearchValue" required>
                    <input type="hidden" name="role" value="member">
                    <div class="input-group-append">
                        <button class="btn btn-primary" name="action" 
                                value="search" onclick="return checkSearchValue()" type="submit">
                            <i class="fas fa-search"></i></button>
                    </div>
             </div>
         </form>
     </div>
 </div>-->
                        <!-- /.Search Widget -->
                    </aside>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container -->

            <!-- Footer -->
        <%@include file="component/footer.jspf" %>
    </body>
</html>
