# Tag5 – JSP verstehen (Jakarta Pages + JSTL + EL)
Namespace: `de.ibb` · Projektname: `Tag5` · Maven WAR (Java 17, Servlet 6, JSTL 3).

## Import
1) NetBeans → Datei → Öffnen → `pom.xml` wählen
2) Run/Deploy auf Payara 7
3) Startseite: `http://localhost:8080/Tag5/`

## Demos
- `/demo/el` – EL & Implicit Objects (`param`, `header`, `cookie`, `pageScope/requestScope/sessionScope/applicationScope`)
- `/demo/jstl` – `c:forEach`, `c:if`, `c:choose`
- `/demo/include` – include-Direktive vs `<jsp:include>`
- `/demo/form` → POST auf `/demo/submit`

**JSTL 3.0 URI:** `jakarta.tags.core`
