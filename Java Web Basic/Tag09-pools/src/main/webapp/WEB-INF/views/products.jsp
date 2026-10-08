<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
<p>Lookup Mode: <strong>${lookupMode != null ? lookupMode : 'Unknown'}</strong></p>

<c:choose>
    <c:when test="${not empty productNames}">
        <ul>
            <c:forEach var="name" items="${productNames}">
                <li>${name}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <ul>
            <li>No products found.</li>
        </ul>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/">Back to entry page</a></p>
</body>
</html>

