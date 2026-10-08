<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products - DAO Demo</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #007bff;
            padding-bottom: 10px;
        }
        .search-form {
            margin: 20px 0;
        }
        .search-form input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .search-form button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-form button:hover {
            background-color: #0056b3;
        }
        .search-form a {
            margin-left: 10px;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .product-card {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .product-card h3 {
            color: #007bff;
            margin-top: 0;
        }
        .product-card .description {
            color: #666;
            font-size: 0.9em;
            margin: 10px 0;
        }
        .product-card .price {
            font-size: 1.5em;
            color: #28a745;
            font-weight: bold;
        }
        .product-card .stock {
            color: #666;
            font-size: 0.9em;
            margin-top: 10px;
        }
        .product-card .stock.low {
            color: #dc3545;
            font-weight: bold;
        }
        .no-products {
            text-align: center;
            padding: 40px;
            background: white;
            border-radius: 8px;
            color: #666;
        }
        .back-link {
            margin-top: 30px;
            display: inline-block;
        }
    </style>
</head>
<body>
<h1>Our Products (DAO)</h1>

<form action="products-dao" method="get" class="search-form">
    <input type="text" name="search" placeholder="Search products..."
           value="${fn:escapeXml(param.search)}">
    <button type="submit">Search</button>
    <c:if test="${not empty param.search}">
        <a href="products-dao">Clear</a>
    </c:if>
</form>

<c:choose>
    <c:when test="${not empty products}">
        <div class="product-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <h3>${product.name}</h3>
                    <p class="description">${product.description}</p>
<p class="price">
    <fmt:formatNumber value="${product.price}"
                      type="currency"
                      currencySymbol="€"/>
</p>
<c:set var="lowStock" value="${product.stock lt 10}"/>
<p class="stock<c:if test="${lowStock}"> low</c:if>">
                        <c:choose>
                            <c:when test="${product.stock == 0}">
                                Out of Stock
                            </c:when>
                            <c:when test="${product.stock lt 10}">
                                Only ${product.stock} left!
                            </c:when>
                            <c:otherwise>
                                ${product.stock} in stock
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="no-products">
            No products found.
            <c:if test="${not empty param.search}">
                <p>Try a different search term or
                    <a href="products-dao">view all products</a>.
                </p>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>

<p class="back-link"><a href="${pageContext.request.contextPath}/">Back to entry page</a></p>
</body>
</html>

