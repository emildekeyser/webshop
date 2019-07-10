<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <%--<nav>--%>
        <%--<ul>--%>
        <%--<li><a href="Controller">Home</a></li>--%>
        <%--<li id="actual"><a href="Controller?action=overview">Overview</a></li>--%>
        <%--<li><a href="Controller?action=signUp">Sign up</a></li>--%>
        <%--</ul>--%>
        <%--</nav>--%>
        <jsp:include page="nav.jsp"></jsp:include>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <c:if test="${not empty cartAmount}">
            <p>Cart: <c:out value="${cartAmount}"/></p>
        </c:if>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <%--<tr>
                <td>jan.janssens@hotmail.com</td>
                <td>Jan</td>
                <td>Janssens</td>
            </tr>--%>
            <c:forEach items="${products}" varStatus="status" var="item">
                <tr>
                    <td><c:out value='${item.name}'/></td>
                    <td><c:out value='${item.description}'/></td>
                    <td><c:out value='${item.price}'/></td>
                    <td>
                        <form method="POST" action="Controller">
                            <input type="number" id="amount" name="amount" value="1">
                            <input type="hidden" id="id" name="productId" value="<c:out value='${item.productId}'/>">
                            <input type="hidden" id="action" name="action" value="addToCart">
                            <input type="submit" id="add" value="add to cart">
                        </form>
                    </td>
                        <%--<td><a href="Controller?action=addToCart&productId=<c:out value='${item.productId}'/>">add to cart</a></td>--%>
                </tr>
            </c:forEach>

            <caption>Users Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>