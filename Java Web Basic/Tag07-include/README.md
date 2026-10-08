# Tag 7 – Include-Action vs Include-Direktive (Mini‑Challenge)
Namespace: de.ibb · Projektname: Tag7

## Import & Start
1) NetBeans → Datei → Öffnen → `pom.xml` wählen
2) Run/Deploy auf Payara 7
3) Aufrufen: `http://localhost:8080/Tag7/demo.jsp`

## Erfüllt (laut Mini‑Challenge)
- /WEB-INF/components/ (Runtime, <jsp:include>): Alert.jsp, Button.jsp, Card.jsp
- /WEB-INF/includes/ (Compile-Time, <%@ include %>): meta-tags.jsp, header.jsp, footer.jsp
- demo.jsp nutzt alle Komponenten & Includes

## Tests
- Hot‑Reload: Ändere /WEB-INF/components/Alert.jsp → Reload im Browser → Änderung sofort sichtbar
- Compile‑Time: Ändere /WEB-INF/includes/header.jsp → ggf. Server-Neustart/Neu‑Compile

Hinweis: Für Compile‑Time Include werden keine Runtime-Parameter übergeben; Titel wird per Request-Attribut gesetzt und in meta-tags.jsp gelesen.
