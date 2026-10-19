<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Personen - JSP</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div class="container">
        <h1>Personen-Verwaltung</h1>
        <p class="engine-badge">Template Engine: <strong>${engine}</strong></p>
        
        <div class="stats">
            <p>Gesamt: <strong><c:out value="${persons.size()}"/></strong> Personen</p>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Telefon</th>
                    <th>Stadt</th>
                    <th>Position</th>
                    <th>Firma</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${persons}" var="person">
                <tr>
                    <td><c:out value="${person.id}"/></td>
                    <td><c:out value="${person.fullName}"/></td>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.phone}"/></td>
                    <td><c:out value="${person.city}"/></td>
                    <td><c:out value="${person.jobTitle}"/></td>
                    <td><c:out value="${person.company}"/></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>