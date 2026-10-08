# Tag7 – JPA vs JDBC: Provider & Konfiguration (Payara 7)
Namespace: `de.ibb` · Projekt: `Tag7` · Server: **Payara 7** (Jakarta EE 10)

## Inhalte (Mini‑Challenge)
- **Entity** `Product` (ID, name, price, stock, createdAt + `@PrePersist`)
- **Service** `ProductService`: `createProduct`, `findProductById`, `findAllProducts`, `updateStock`, `deleteProduct`
- **Hibernate** als Provider in `persistence.xml` (MySQL Dialect, `hbm2ddl.auto=update`, SQL‑Logging)
- **DataSource** via `WEB-INF/payara-resources.xml` → JNDI `jdbc/jwebak_tag07_jpa`
- **Vergleich**: `JdbcDemoServlet` zeigt Zugriff über `DataSource` ohne JPA
- **glassfish-web.xml**: `<class-loader delegate="false"/>` damit **Hibernate** statt EclipseLink verwendet wird

## Start
1. Projekt über `pom.xml` öffnen, Server **Payara 7** wählen.
2. DB optional vorbereiten: `sql/schema-mysql.sql` (oder auto DDL nutzen).
3. `WEB-INF/payara-resources.xml` anpassen (User/Pass/DB).
4. Deploy & testen:
   - `http://localhost:8080/Tag7/` (Start)
   - `…/jpa` (JPA CRUD mit Hibernate)
   - `…/jdbc` (JDBC Vergleich)

## Provider umschalten (Optional)
- **EclipseLink** (Payara Default) nutzen: entferne den `<provider>`‑Eintrag & Hibernate‑Dependencies. Stelle stattdessen JPA‑Properties auf EclipseLink um.
