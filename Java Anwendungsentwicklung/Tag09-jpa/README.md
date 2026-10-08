# 🗄️ Java Anwendungsentwicklung - Tag 9

**JPA CRUD & Queries (JPQL, Relationships, Repository)**

*Java Fleet Systems Consulting - java-developer.online*

---

## 📋 Inhalt

| Klasse | Beschreibung | Starten |
|--------|--------------|---------|
| `JpqlDemo` | JPQL Abfragen | `mvn exec:java` |
| `RelationshipDemo` | @OneToOne, @OneToMany | `mvn exec:java -Prelations` |
| `RepositoryDemo` | Repository Pattern | `mvn exec:java -Prepository` |

---

## 🚀 Quick Start

```bash
# JPQL Demo
mvn exec:java

# Relationships (Person → Adresse, Bestellungen)
mvn exec:java -Prelations

# Repository Pattern
mvn exec:java -Prepository
```

---

## 🎓 Lernziele

- ✅ JPQL (Java Persistence Query Language)
- ✅ Entity Relationships
- ✅ N+1-Problem und JOIN FETCH
- ✅ Repository Pattern

---

## ⚠️ Das N+1-Problem

```java
// ❌ N+1 Queries!
for (Person p : personen) {
    p.getBestellungen().size();
}

// ✅ 1 Query mit JOIN FETCH
"SELECT p FROM Person p LEFT JOIN FETCH p.bestellungen"
```

---

*© 2025 Java Fleet Systems Consulting*
