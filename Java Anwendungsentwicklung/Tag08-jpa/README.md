# 🗄️ Java Anwendungsentwicklung - Tag 8

**JPA Einführung (Entity, EntityManager, EclipseLink)**

*Java Fleet Systems Consulting - java-developer.online*

---

## 📋 Inhalt

| Klasse | Beschreibung | Starten |
|--------|--------------|---------|
| `HalloJPA` | Erste JPA-Anwendung | `mvn exec:java` |
| `EntityLifecycleDemo` | Die 4 Entity-Zustände | `mvn exec:java -Plifecycle` |
| `JpaCrudDemo` | CRUD mit JPA | `mvn exec:java -Pcrud` |

---

## 🚀 Quick Start

```bash
# Hallo JPA
mvn exec:java

# Entity Lifecycle (NEW, MANAGED, DETACHED, REMOVED)
mvn exec:java -Plifecycle

# CRUD Demo (vergleiche mit JDBC Tag 6!)
mvn exec:java -Pcrud
```

---

## 🎓 Lernziele

- ✅ JPA (API) und EclipseLink (Implementierung) unterscheiden
- ✅ Entity mit Annotations erstellen
- ✅ EntityManager für CRUD
- ✅ Entity Lifecycle verstehen
- ✅ Dirty Checking (automatische Updates)

---

## 🔑 Der Unterschied

```java
// JDBC (Tag 6): ~20 Zeilen
String sql = "SELECT...";
Connection conn = ...;
PreparedStatement ps = ...;
ResultSet rs = ...;
// ... Mapping ...

// JPA (Tag 8): 1 Zeile!
Person p = em.find(Person.class, id);
```

---

*© 2025 Java Fleet Systems Consulting*
