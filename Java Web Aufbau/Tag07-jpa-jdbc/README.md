# Tag7 – JPA vs JDBC: Provider & Konfiguration (Payara 7)
Namespace: `de.ibb` · Projekt: `Tag7` · Server: **Payara 7** (Jakarta EE 11)

## Inhalte (Mini‑Challenge)
- **Entity** `Product` (ID, name, price, stock, createdAt + `@PrePersist`)
- **Service** `ProductService`: `createProduct`, `findProductById`, `findAllProducts`, `updateStock`, `deleteProduct`
- **EclipseLink** als Provider in `persistence.xml` (der Server liefert die Implementierung; Schema über `jakarta.persistence.schema-generation.database.action=create`, SQL-Log über `eclipselink.logging.level.sql`)
- **DataSource** via `WEB-INF/payara-resources.xml` → JNDI `jdbc/jwebak_tag07_jpa`
- **Vergleich**: `JdbcDemoServlet` zeigt Zugriff über `DataSource` ohne JPA
- Klassen werden mit **JDK 21** gebaut und laufen auf einer Payara-7-JVM mit JDK 21 oder 25

## Start
1. Projekt über `pom.xml` öffnen, Server **Payara 7** (Community 7.2026.8 oder neuer) wählen.
2. DB optional vorbereiten: `sql/schema-mysql.sql` (oder Schema-Generierung beim Deploy nutzen).
3. `WEB-INF/payara-resources.xml` anpassen (User/Pass/DB).
4. Deploy & testen:
   - `http://localhost:8080/Tag7/` (Start)
   - `…/jpa` (JPA CRUD mit EclipseLink)
   - `…/jdbc` (JDBC Vergleich)

## Provider
Hibernate nicht ins WAR legen und `class-loader delegate="false"` nicht setzen. Beides verdrängt den EclipseLink des Servers und führt auf Payara 7 zu Classloader-Fehlern.
