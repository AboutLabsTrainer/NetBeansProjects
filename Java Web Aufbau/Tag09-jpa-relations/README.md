# Java Web Aufbau - JPA Relationen Demo

**JPA Relationen: @OneToOne & @ManyToOne**

---

## 📋 Überblick

Dieses Maven-Projekt demonstriert praktische JPA-Relationen mit Jakarta EE 10:
- **@OneToOne:** User ↔ UserProfile (1:1 Beziehung)
- **@ManyToOne:** Orders → User (N:1 Beziehung)
- **Bidirektionale Relationen:** Navigation in beide Richtungen
- **CascadeType & orphanRemoval:** Automatische Propagierung

---

## 🎯 Was du lernst

### Entity-Relationen
✅ **@OneToOne Relation**
- User hat genau ein UserProfile
- CascadeType.ALL & orphanRemoval = true
- Automatisches Löschen von Profile beim User-Delete

✅ **@ManyToOne Relation**
- Viele Orders gehören zu einem User
- FetchType.LAZY für Performance
- JOIN FETCH für effizientes Laden

✅ **Bidirektionale Relationen**
- User kennt Orders
- Orders kennen User
- Convenience-Methods für Synchronisation

---

## 🛠️ Technologie-Stack

- **Java:** JDK 21 LTS
- **Jakarta EE:** 10.0.0 (`jakarta.jakartaee-web-api`, provided)
- **JPA:** 3.1 (EclipseLink 4.0.7, vom Payara-6-Server gestellt)
- **Server:** Payara 6 (Community 6.2025.10)
- **Bytecode:** JDK 21 (`maven.compiler.release`)
- **Datenbank:** `jwebak_tag09_relations` (User und Passwort `jwebak_tag09_relations`, Tabellen-Präfix: `jwebak07_`)
- **Build:** Maven 3.9+

---

## 📦 Projekt-Struktur

```
JavaWebAufbauTag9/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── ibb/
    │   │           ├── model/
    │   │           │   ├── User.java
    │   │           │   ├── UserProfile.java
    │   │           │   ├── Order.java
    │   │           │   └── OrderStatus.java
    │   │           ├── service/
    │   │           │   └── OrderManagementService.java
    │   │           └── web/
    │   │               └── OrderServlet.java
    │   ├── resources/
    │   │   └── META-INF/
    │   │       └── persistence.xml
    │   └── webapp/
    │       ├── WEB-INF/
    │       ├── css/
    │       │   └── style.css
    │       └── index.html
```

---

## ⚙️ Setup & Installation

### 1. Voraussetzungen

- **JDK 21 LTS** installiert
- **Apache NetBeans 22** (oder IntelliJ IDEA)
- **Payara 6** konfiguriert
- **XAMPP mit MySQL (MariaDB)** läuft lokal
- **Maven 3.9+** installiert

### 2. Datenbank einrichten (lokale XAMPP-Installation)

**phpMyAdmin öffnen:** http://localhost/phpmyadmin

**SQL ausführen (Tab „SQL“):**
```sql
CREATE DATABASE jwebak_tag09_relations CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'jwebak_tag09_relations'@'localhost' IDENTIFIED BY 'jwebak_tag09_relations';
GRANT ALL ON jwebak_tag09_relations.* TO 'jwebak_tag09_relations'@'localhost';
FLUSH PRIVILEGES;
```

**Hinweis:** Falls `CREATE USER` fehlschlägt, existiert der User bereits. Dann nur Passwort/Grants aktualisieren:
```sql
ALTER USER 'jwebak_tag09_relations'@'localhost' IDENTIFIED BY 'jwebak_tag09_relations';
GRANT ALL ON jwebak_tag09_relations.* TO 'jwebak_tag09_relations'@'localhost';
FLUSH PRIVILEGES;
```

### 3. DataSource im Payara Server konfigurieren

**Admin Console öffnen:** http://localhost:4848

**JDBC Connection Pool erstellen:**
```
Resources → JDBC → JDBC Connection Pools → New...

Pool Name: MySQLPool
Resource Type: javax.sql.DataSource
Database Driver Vendor: MySQL

Additional Properties:
  ServerName: localhost
  Port: 3306
  DatabaseName: jwebak_tag09_relations
  User: jwebak_tag09_relations
  Password: jwebak_tag09_relations
```

**JDBC Resource erstellen:**
```
Resources → JDBC → JDBC Resources → New...

JNDI Name: jdbc/jwebak_tag09_relations
Pool Name: MySQLPool
```



**Dieses Beispiel:** JDBC Resource `jdbc/jwebak_tag09_relations` auf der Datenbank `jwebak_tag09_relations` (User und Passwort gleich dem Datenbanknamen). Die `persistence.xml` nutzt `<jta-data-source>jdbc/jwebak_tag09_relations</jta-data-source>`. Die Resource `jdbc/JWebShared` gehört nur zu Fleet 07.

### 4. Projekt builden & deployen

**Option A: NetBeans**
```
1. File → Open Project... → JavaWebAufbauTag9
2. Rechtsklick auf Projekt → Clean and Build
3. Rechtsklick auf Projekt → Run
```

**Option B: Maven CLI**
```bash
cd JavaWebAufbauTag9
mvn clean package
# WAR-Datei liegt in: target/javawebaufbau-tag9.war
```

**Deploy manuell:**
```
Payara Admin Console → Applications → Deploy...
→ target/javawebaufbau-tag9.war hochladen
```

### 5. Anwendung testen

**Landing Page:**
```
http://localhost:8080/javawebaufbau-tag9/
```

**Order Management Demo:**
```
http://localhost:8080/javawebaufbau-tag9/orders
```

---

## 🚀 Features & Funktionen

### 1. Test-Daten erstellen

Schaltfläche **Test-Daten erstellen** auf `http://localhost:8080/javawebaufbau-tag9/orders`. Das Formular sendet **POST** `orders` mit `action=create`. GET bleibt Lesen (Liste, Statistik, Menü).

- Erstellt User mit UserProfile
- Erstellt 2 Orders für den User
- Zeigt @OneToOne & @ManyToOne in Aktion

### 2. Orders auflisten
```
http://localhost:8080/javawebaufbau-tag9/orders?action=list
```
- Zeigt alle Orders mit User-Info
- Nutzt JOIN FETCH für Performance
- Demonstriert LAZY Loading

### 3. Statistiken anzeigen
```
http://localhost:8080/javawebaufbau-tag9/orders?action=stats
```
- Anzahl Orders pro User
- Gesamtsumme berechnen
- Durchschnittswert

---

## 📚 Wichtige Konzepte

### @OneToOne Relation

```java
@Entity
public class User {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;
}
```

**Was passiert:**
- `persist(user)` → `persist(profile)` automatisch
- `remove(user)` → `remove(profile)` automatisch
- `user.setProfile(null)` → Profile wird gelöscht (orphanRemoval)

### @ManyToOne Relation

```java
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
```

**Was passiert:**
- Viele Orders können zu einem User gehören
- User wird LAZY geladen (erst bei Zugriff)
- Foreign Key `user_id` in `orders` Tabelle

### Bidirektionale Relation

```java
// User-Seite
@OneToMany(mappedBy = "user")
private List<Order> orders = new ArrayList<>();

// Order-Seite
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

**Convenience Methods:**
```java
public void addOrder(Order order) {
    orders.add(order);
    order.setUser(this);  // Beide Seiten synchronisieren!
}
```

---

## ⚡ Performance-Tipps

### Problem: N+1 Query

**Schlecht:**
```java
List<Order> orders = em.createQuery("SELECT o FROM Order o", Order.class)
    .getResultList();
for (Order order : orders) {
    order.getUser().getUsername(); // Für jede Order: 1 Query!
}
```

**Gut:**
```java
List<Order> orders = em.createQuery(
    "SELECT o FROM Order o JOIN FETCH o.user", Order.class)
    .getResultList(); // Nur 1 Query für alles!
```

### FetchType.LAZY vs. EAGER

- **LAZY:** Standard für @ManyToOne, @OneToMany, @ManyToMany
- **EAGER:** Standard für @OneToOne, @ManyToOne (optional)
- **Best Practice:** Immer LAZY nutzen, JOIN FETCH wo nötig

---

## 🔧 Troubleshooting

### Problem: Lazy-Beziehung außerhalb der Transaktion

**Fehler (EclipseLink):**
```
org.eclipse.persistence.exceptions.ValidationException
An attempt was made to traverse a relationship using indirection that had a null Session.
```

**Ursache:** LAZY Entity außerhalb Transaction aufgerufen.

**Lösung 1: JOIN FETCH**
```java
em.createQuery("SELECT o FROM Order o JOIN FETCH o.user WHERE o.id = :id", Order.class)
```

**Lösung 2: @Transactional breiter setzen**
```java
@Transactional
public void processOrder(Long id) {
    Order order = em.find(Order.class, id);
    String username = order.getUser().getUsername(); // OK!
}
```

### Problem: Foreign Key Constraint verletzt

**Fehler:**
```
MySQLIntegrityConstraintViolationException: Cannot add or update a child row
```

**Ursache:** User existiert nicht.

**Lösung:**
```java
User user = em.find(User.class, userId);
if (user == null) {
    throw new IllegalArgumentException("User not found!");
}
Order order = new Order(orderNumber, amount, user);
```

### Problem: DataSource nicht gefunden

**Fehler:**
```
javax.naming.NameNotFoundException: jdbc/jwebak_tag09_relations not found
```

**Lösung:**
1. Payara Admin Console öffnen: http://localhost:4848
2. Resources → JDBC → JDBC Resources prüfen
3. `jdbc/jwebak_tag09_relations` muss vorhanden sein
4. Payara Server neu starten

---

## 📖 Weiterführende Ressourcen

### Offizielle Dokumentation
- **Jakarta Persistence:** https://jakarta.ee/specifications/persistence/
- **JPA Tutorial:** https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm

### Tutorials & Blogs
- **Baeldung JPA Relationships:** https://www.baeldung.com/jpa-relationships
- **Thorben Janssen Associations:** https://thorben-janssen.com/ultimate-guide-association-mappings-jpa-hibernate/
- **Vlad Mihalcea's Blog:** https://vladmihalcea.com/

### Bücher
- "Pro JPA 2" by Mike Keith (Chapter 4: Relationships)
- "High-Performance Java Persistence" by Vlad Mihalcea

---

## Hinweise

- Lokales Setup ohne Docker, mit XAMPP/MySQL.
- JNDI-Resource `jdbc/jwebak_tag09_relations` zeigt auf die Datenbank `jwebak_tag09_relations`.
