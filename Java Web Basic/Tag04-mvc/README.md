# Tag 4 – Deployment Descriptor & MVC (Model 2)
NetBeans/Maven WAR-Projekt (Payara 7, Jakarta Servlet 6, Java 17) zur Demonstration von **web.xml** und **MVC**.

## Import
1. NetBeans: `Datei → Öffnen` und Ordner mit `pom.xml` wählen.
2. Rechtsklick → **Run/Deploy** auf Payara 7.
3. Basis-URL: `http://localhost:8080/HelloPayara-Tag4/`

## Endpunkte
- `/blog` – Liste aller Blog-Posts (Controller → forward → `/WEB-INF/views/blog-list.jsp`)
- `/blog?id=X` – Einzelansicht (Controller → forward → `/WEB-INF/views/blog-post.jsp`)
- Fehlerseiten: `404.jsp`, `500.jsp` über `web.xml` gemappt

## Architektur
- **Model:** `BlogPost` (POJO), `BlogPostDAO` (In-Memory).
- **Controller:** `BlogServlet` (in `web.xml` gemappt, kein `@WebServlet`).
- **View:** JSPs unter `WEB-INF/views/` (nicht direkt aufrufbar! Zugriff nur via Forward).

## Was zeigt dieses Projekt?
- **Deployment Descriptor (`web.xml`)**: display-name, context-param, session-config, error-pages, welcome-file-list, servlet-mapping.
- **RequestDispatcher#forward(...)** zur MVC-Verknüpfung.
- **WEB-INF**-JSP-Schutz (nur Controller darf Views rendern).
