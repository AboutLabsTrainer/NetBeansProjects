# Tag8 – JPA @ManyToOne (Author/Book) – Payara 7
Namespace: `de.ibb` · Projektname: `Tag8` · Server: **Payara 7** (Jakarta EE 10)

## Features
- **@ManyToOne** (Book → Author) mit `fetch=LAZY`, `optional=false`, `@JoinColumn(name="author_id")`
- **@OneToMany(mappedBy="author", cascade=ALL, orphanRemoval=true)** in `Author`
- **Servlet-Demo** `/jpa`: Autoren/Books anlegen & auflisten (Delete Book)
- **JPA 3.1**: `persistence.xml` mit JTA-DS `jdbc/jwebak_tag08_manytoone` (Provider = EclipseLink, Payara-Default)
- **Datasource**: `WEB-INF/payara-resources.xml` (Pool `jwebak_tag08_manytoone`, JNDI `jdbc/jwebak_tag08_manytoone`)
- **web.xml**: für Payara‑Erkennung in NetBeans
- **SQL**: `sql/schema-mysql.sql`

## Start
1. Projekt via `pom.xml` in NetBeans öffnen, **Payara 7** wählen.
2. DB anlegen: `sql/schema-mysql.sql` (DB `tag8db`).
3. In `WEB-INF/payara-resources.xml` Host/Port/User/Pass prüfen.
4. Deploy & testen: `http://localhost:8080/Tag8/jpa`

## Hinweise
- `@ManyToOne` ist standardmäßig **EAGER** – hier explizit `LAZY` gesetzt. Der Join erfolgt über `author_id`. Für bidirektionale Beziehungen muss die **Nicht‑Owning-Seite** (`Author.books`) `mappedBy` nutzen.
