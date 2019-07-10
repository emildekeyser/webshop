<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Person Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="nav.jsp"></jsp:include>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <form method="GET" action="Controller" novalidate>
            <select name="sortChoice">
                <option value="email" ${cookie.sortBy.value eq "email" ? "selection" : ""}>E-mail</option>
                <option value="firstName" ${cookie.sortBy.value eq "firstName" ? "selection" : ""}>First Name</option>
                <option value="name" ${cookie.sortBy.value eq "name" ? "selected" : ""}>Name</option>
            </select>
            <input type="submit" value="send">
            <input type="hidden" value="sortPerson" name="action">
        </form>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach items="${persons}" varStatus="status" var="item">
                <tr>
                    <td><c:out value='${item.email}'/></td>
                    <td><c:out value='${item.firstName}'/></td>
                    <td><c:out value='${item.lastName}'/></td>
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