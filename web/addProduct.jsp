<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <%--<nav>--%>
        <%--<ul>--%>
        <%--<li><a href="Controller">Home</a></li>--%>
        <%--<li><a href="Controller?action=overview">Overview</a></li>--%>
        <%--<li id="actual"><a href="Controller?action=signUp">Sign up</a></li>--%>
        <%--</ul>--%>
        <%--</nav>--%>
        <jsp:include page="nav.jsp"></jsp:include>
        <h2>
            Add Product
        </h2>

    </header>
    <main>
        <c:if test="${not authorized}">
            <p>Unauthorized to be here</p>
        </c:if>

        <c:if test="${authorized}">
            <c:if test="${not empty errors}">
                <div class="alert-danger">
                    <ul>
                        <c:forEach items="${errors}" varStatus="status" var="error">
                            <li>< c:out value ='${error}'/></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="POST" action="Controller" novalidate>
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="name">name</label><input type="text" id="name" name="name"
                                                             required value="< c:out value ='${name}'/>"></p>
                <p><label for="description">Description</label><input type="text" id="description" name="description"
                                                                   required value="< c:out value ='${description}'/>"></p>
                <p><label for="price">Price</label><input type="number" id="price" name="price"
                                                                 required value="< c:out value ='${price}'/>"></p>

                <p><input type="submit" id="addProduct" value="add Product"></p>
                <p><input type="hidden" id="action" name="action" value="addProduct"></p>
                    <%--<p><input type="hidden" id="hidden" value="SignUp" action="signUp"></p>--%>
            </form>
        </c:if>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
