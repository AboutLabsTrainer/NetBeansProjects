<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title>Warenkorb</title>
  <style>
    body{font-family:system-ui,Arial,sans-serif;margin:40px}
    table{border-collapse:collapse;width:100%;max-width:1000px}
    th,td{border:1px solid #eee;padding:10px;text-align:left}
    tr:first-child td{border-top:1px solid #ddd}
    tr.highlight-first{background:#f7fbff}
    tr.highlight-last{background:#fffaf2}
    .totals{max-width:400px;margin-left:auto}
    .muted{color:#666}
    .btn{display:inline-block;padding:10px 14px;border-radius:10px;background:#0a7;color:#fff;text-decoration:none}
    .btn:disabled, .btn.disabled{background:#93c5b5;cursor:not-allowed}
    .note{padding:8px 10px;border:1px dashed #ccc;border-radius:8px;background:#fafafa;margin:8px 0}
  </style>
</head>
<body>
  <h1>Warenkorb</h1>

  <fmt:setLocale value="de_DE" />

  <c:choose>
    <c:when test="${fn:length(cartItems) == 0}">
      <p class="note">🛒 Dein Warenkorb ist leer.</p>
    </c:when>
    <c:otherwise>
      <table aria-label="Warenkorb">
        <thead>
          <tr>
            <th>#</th>
            <th>Produkt</th>
            <th>Preis</th>
            <th>Menge</th>
            <th>Zwischensumme</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${cartItems}" varStatus="st">
            <c:set var="rowClass" value="${st.first ? 'highlight-first' : (st.last ? 'highlight-last' : '')}" />
            <tr class="${rowClass}">
              <td>${st.index + 1}</td>
              <td>${item.productName}</td>
              <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
              <td>${item.quantity}</td>
              <td><fmt:formatNumber value="${item.subtotal}" type="currency"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <div class="totals">
        <p>Zwischensumme: <strong><fmt:formatNumber value="${subtotal}" type="currency"/></strong></p>
        <p>Steuer (19%): <strong><fmt:formatNumber value="${tax}" type="currency"/></strong></p>
        <p>Gesamt: <strong><fmt:formatNumber value="${total}" type="currency"/></strong></p>

        <c:if test="${total > 50}">
          <p class="note">🚚 Free Shipping aktiviert (Total &gt; 50)!</p>
        </c:if>

        <p class="muted">Letzte Aktualisierung:
          <fmt:formatDate value="${lastUpdated}" type="both" pattern="dd.MM.yyyy HH:mm:ss" />
        </p>

        <c:url var="checkoutUrl" value="/checkout" />
        <c:choose>
          <c:when test="${fn:length(cartItems) > 0}">
            <a class="btn" href="${checkoutUrl}">Zur Kasse</a>
          </c:when>
          <c:otherwise>
            <a class="btn disabled" href="#" onclick="return false;">Zur Kasse</a>
          </c:otherwise>
        </c:choose>
      </div>
    </c:otherwise>
  </c:choose>

  <p><a href="${pageContext.request.contextPath}/">Zurück</a></p>
</body>
</html>
