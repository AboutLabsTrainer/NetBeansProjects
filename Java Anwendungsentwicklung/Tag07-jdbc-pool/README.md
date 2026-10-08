# 🗄️ Java Anwendungsentwicklung - Tag 7

**JDBC Best Practices (Pooling, Transactions, DAO)**

*Java Fleet Systems Consulting - java-developer.online*

---

## 📋 Inhalt

| Klasse | Beschreibung | Starten |
|--------|--------------|---------|
| `ConnectionPoolDemo` | HikariCP vs. DriverManager | `mvn exec:java` |
| `TransactionDemo` | commit/rollback in Aktion | `mvn exec:java -Ptransaction` |
| `DaoDemo` | DAO Pattern komplett | `mvn exec:java -Pdao` |

---

## 🚀 Quick Start

```bash
# Connection Pool Demo (HikariCP)
mvn exec:java

# Transaction Demo (Banküberweisung)
mvn exec:java -Ptransaction

# DAO Pattern Demo
mvn exec:java -Pdao
```

---

## 🎓 Lernziele

- ✅ Connection Pooling mit HikariCP
- ✅ Transactions (commit/rollback)
- ✅ ACID-Prinzipien
- ✅ DAO Pattern für saubere Architektur
- ✅ Dependency Injection

---

## 📊 Die drei Säulen

```
┌─────────────────┬─────────────────┬─────────────────┐
│  Connection     │  Transactions   │  DAO Pattern    │
│  Pooling        │                 │                 │
├─────────────────┼─────────────────┼─────────────────┤
│  Performance    │  Integrität     │  Wartbarkeit    │
│  100x schneller │  Alles/Nichts   │  SQL gekapselt  │
└─────────────────┴─────────────────┴─────────────────┘
```

---

*© 2025 Java Fleet Systems Consulting*
