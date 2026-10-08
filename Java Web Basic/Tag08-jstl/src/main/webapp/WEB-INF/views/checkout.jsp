<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title>Kasse</title>
  <style>
    body{font-family:system-ui,Arial,sans-serif;margin:40px}
    table{border-collapse:collapse;width:100%;max-width:1000px}
    th,td{border:1px solid #eee;padding:10px;text-align:left}
    tr:first-child td{border-top:1px solid #ddd}
    tr.highlight-first{background:#f7fbff}
    tr.highlight-last{background:#fffaf2}
    .totals{max-width:400px;margin-left:auto}
    .muted{color:#666}
    .success{padding:15px 20px;border-radius:10px;background:#d4edda;border:1px solid #c3e6cb;color:#155724;margin:20px 0}
    .btn{display:inline-block;padding:10px 14px;border-radius:10px;background:#0a7;color:#fff;text-decoration:none;margin-top:10px}
  </style>
</head>
<body>
  <h1>Kasse</h1>

  <fmt:setLocale value="de_DE" />

  <c:choose>
    <c:when test="${fn:length(cartItems) == 0}">
      <p class="note">🛒 Dein Warenkorb ist leer.</p>
    </c:when>
    <c:otherwise>
      <table aria-label="Bestellübersicht">
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

        <p class="muted">Bestelldatum:
          <fmt:formatDate value="${orderDate}" type="both" pattern="dd.MM.yyyy HH:mm:ss" />
        </p>
      </div>

      <div class="success">
        <strong>✓ Bestellung erfolgreich!</strong><br/>
        Vielen Dank für Ihren Einkauf. Ihre Bestellung wurde bearbeitet.
      </div>
    </c:otherwise>
  </c:choose>

  <p>
    <a class="btn" href="${pageContext.request.contextPath}/cart">Zurück zum Warenkorb</a>
    <a class="btn" href="${pageContext.request.contextPath}/">Zur Startseite</a>
  </p>
</body>
</html>
