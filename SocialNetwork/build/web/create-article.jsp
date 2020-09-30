<%-- 
    Document   : create-article
    Created on : Sep 22, 2020, 10:50:48 AM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create the Article - Social Network</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user"/>
        <c:if test="${not empty user}">
            <c:choose>
                <c:when test="${user.roleId.id == 1}">
                    <c:redirect url="./"/>
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
                <div class="col-md-12">
                    <div class="card my-4">
                        <h5 class="card-header">Write an article</h5>
                        <div class="card-body">
                            <c:set value="${requestScope.MSG_SUCCESS}" var="msg"/>
                            <c:set value="${requestScope.MSG_FAIL}" var="msgFail"/>
                            <c:if test="${not empty msg}">
                                <div class="alert alert-success" role="alert">${msg}</div>
                            </c:if>
                            <c:if test="${not empty msgFail}">
                                <div class="alert alert-danger" role="alert">${msgFail}</div>
                            </c:if>
                            <form action="MainController" method="post">
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Title:</p>
                                    <input type="text" class="form-control" name="txtTitle" rows="2" required autofocus />
                                </div>
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Image</p>
                                    <button type="button" class="btn btn-success btn-sm" onclick="initImagePicker()" >Choose image</button>
                                    <input class="form-control" id="txtImageUrl" type="hidden" name="txtImageUrl"  />
                                </div>
                                <div class="form-group">
                                    <img id="img-preview" src="" height="150"/>
                                </div>
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Description:</p>
                                    <textarea class="form-control" name="txtDescription" rows="3" required></textarea>
                                </div>
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Content:</p>
                                    <textarea class="form-control" name="txtContent" rows="10" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary" name="action" value="create-article">Submit</button>
                                <button type="reset" class="btn btn-outline-secondary">Reset</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="//static.filestackapi.com/filestack-js/3.x.x/filestack.min.js"></script>
        <script>
                                        function initImagePicker() {
                                            const client = filestack.init('AgFmzdgAS0uIX18FOav8Iz');
                                            const options = {
                                                fromSources: ['local_file_system'],
                                                accept: ["image/*"],
                                                transformations: {
                                                    crop: true,
                                                    rotate: true
                                                },
                                                uploadConfig: {
                                                    retry: 5,
                                                    timeout: 60000
                                                },
                                                onFileSelected: file => {
                                                    if (file.size > 2048 * 1000) {
                                                        throw new Error('File too big, select something smaller than 2MB');
                                                    }
                                                },
                                                onUploadDone: result => {
                                                    if (result.filesUploaded.length === 1) {
                                                        document.getElementById("img-preview").setAttribute("src", result.filesUploaded[0].url);
                                                        document.getElementById("txtImageUrl").value = result.filesUploaded[0].url;
                                                    }
                                                }
                                            };
                                            client.picker(options).open();
                                        }
        </script>
    </body>
</html>
