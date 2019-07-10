<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <%--<nav>--%>
        <%--<ul>--%>
        <%--<li id="actual"><a href="Controller">Home</a></li>--%>
        <%--<li><a href="Controller?action=overview">Overview</a></li>--%>
        <%--<li><a href="Controller?action=signUp">Sign up</a></li>--%>
        <%--</ul>--%>
        <%--</nav>--%>
        <jsp:include page="nav.jsp"></jsp:include>
        <h2>Home</h2>

    </header>
    <main>
        <p>
            Sed ut perspiciatis unde omnis iste natus error sit
            voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
            ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
            dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
            aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
            qui ratione voluptatem sequi nesciunt.
        </p>
        <p>
            <c:out value="${quote}"></c:out>
        </p>
        <form method="GET" action="Controller" novalidate="novalidate">
            <label for="showQuote">Show Quote</label>
            <input type="radio" value="yes" id="showQuote"
                   name="showQuoteChoice" ${empty quote ? '' : 'checked'} >yes</input>
            <input type="radio" value="no" id="showQuote2"
                   name="showQuoteChoice" ${empty quote ? 'checked' : ''}>no</input>
            <input type="submit" id="quoteSubmit" value="send">
            <input type="hidden" id="hidden" value="setShowQuote" name="action">
        </form>

        <c:choose>
            <c:when test="${not empty sessionScope.get('user')}">
                <p>Hello ${sessionScope.get('user').firstName} ${sessionScope.get('user').lastName}</p>
                <form action="Controller" method="POST">
                    <input type="submit" id="logoutButton" value="logout">
                    <input type="hidden" id="logoutAction" value="logout" name="action">
                </form>
            </c:when>
            <c:otherwise>
                <c:if test="${not empty loginError}">
                   <p>${loginError}</p>
                </c:if>
                <form action="Controller" method="POST">
                    <input type="text" id="userid" name="userid">
                    <input type="text" id="password" name="password">
                    <input type="submit" id="loginButton" value="login">
                    <input type="hidden" id="loginAction" value="login" name="action">
                </form>
            </c:otherwise>
        </c:choose>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>