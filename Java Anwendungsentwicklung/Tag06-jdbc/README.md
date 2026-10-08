# 🗄️ Java Anwendungsentwicklung - Tag 6

**JDBC Grundlagen**

*Java Fleet Systems Consulting - java-developer.online*

---

## 📋 Inhalt

| Klasse | Beschreibung | Starten |
|--------|--------------|---------|
| `HalloJDBC` | Erste Datenbankverbindung | `mvn exec:java` |
| `CrudBeispiel` | Create, Read, Update, Delete | `mvn exec:java -Pcrud` |
| `SqlInjectionDemo` | Warum PreparedStatement PFLICHT ist | `mvn exec:java -Pinjection` |

---

## 🚀 Quick Start

```bash
# Hallo JDBC (erste Verbindung)
mvn exec:java

# CRUD komplett
mvn exec:java -Pcrud

# SQL Injection Demo (Sicherheit!)
mvn exec:java -Pinjection
```

---

## 🎓 Lernziele

- ✅ JDBC-Architektur verstehen
- ✅ Connection, Statement, ResultSet
- ✅ try-with-resources für Ressourcen-Management
- ✅ PreparedStatement (SQL Injection verhindern!)
- ✅ CRUD-Operationen

---

## ⚠️ Die goldene Regel

```java
// ❌ NIEMALS
"SELECT * FROM users WHERE id=" + userId

// ✅ IMMER
"SELECT * FROM users WHERE id = ?"
ps.setInt(1, userId);
```

---

*© 2025 Java Fleet Systems Consulting*
