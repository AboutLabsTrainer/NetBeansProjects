# Tag 8 – JSTL Mini‑Challenge (Shopping Cart)
**Namespace:** `de.ibb` · **Projektname:** `Tag8` · **Build:** Maven WAR (Java 21).

## Was ist enthalten?
- `CartServlet` setzt laut Challenge: `cartItems`, `subtotal`, `tax`, `total`, `lastUpdated` und forwarded auf `/WEB-INF/views/cart.jsp`.
- `cart.jsp` nutzt **JSTL** (`c`, `fmt`, `fn`) für Iteration, Bedingungen, URL‑Erzeugung & Formatierung (Währung/Datum) – **ohne Scriptlets**.
- Bonus umgesetzt: `varStatus` (Index), **erste/letzte Zeile** hervorheben, **Free Shipping** bei `total > 50`, Subtotal je Item (`price * quantity`).

## Start (NetBeans + Payara 6)
1. Datei → Öffnen → `pom.xml` im Ordner **Tag8** wählen.
2. Rechtsklick → **Run/Deploy** (Server: **Payara 6**).
3. **http://localhost:8080/Tag8/** → Button „Warenkorb anzeigen“. Direkt: **/cart**.

## Versionen / URIs
- JSTL URIs (Jakarta Tags 3.0): `jakarta.tags.core`, `jakarta.tags.fmt`, `jakarta.tags.functions`
- `web.xml` ist **Servlet 6.0** (Jakarta EE 10) – NetBeans erkennt damit Payara 6.

## Hinweise
- Steuerberechnung ist Demo (19% VAT). Passe sie bei Bedarf im `Cart` an.
- JSTL‑API ist als **provided** deklariert; die Implementierung liefert Payara 6.
