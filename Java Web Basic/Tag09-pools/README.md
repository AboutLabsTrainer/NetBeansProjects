# Tag9 – Connection Pools

## Was dieses Beispiel übt
Drei Wege zur DataSource aus dem Payara-Pool: Resource-Injection, manueller JNDI-Lookup und ein Stateless-DAO.

## Wichtige Klassen
1. `ProductServlet` – Injection von `jdbc/jwebb_tag09_pools`
2. `ProductServletManual` – Lookup über `InitialContext`
3. `ProductDAO` und `ProductDAOImpl` – JDBC über den Pool
4. `ProductDaoServlet` – ruft das DAO per `@EJB` auf
5. `Product` – Modell für die JSP

## Start in NetBeans
Projekt auf dem registrierten Payara-6-Server mit **Run** starten.
Context-Pfad: `/Tag9-ConnectionPools`
URL: http://localhost:8080/Tag9-ConnectionPools/

## Datenbank
MySQL (XAMPP), Datenbank `jwebb_tag09_pools`, JNDI-Name `jdbc/jwebb_tag09_pools` (resource-ref in `web.xml`). Der Pool liegt auf der Payara-Domain.

## Ausprobieren
1. Startseite öffnen und „View Products (Resource Injection)“ wählen.
2. Denselben Katalog über „Manual JNDI Lookup“ und „DAO + EJB“ öffnen.
3. In der DAO-Ansicht einen Namensteil suchen.
