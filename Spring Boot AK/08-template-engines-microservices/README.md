# Tag 10: Template Engines & Microservices

Maven-Projekte zu [Tag 10: Template Engines & Microservices](https://www.java-developer.online/tag-10-template-engines-microservices/).

Zwei getrennte Parent-Projekte (wie die Kurs-Demos) – Ports kollidieren sonst.

| Ordner | Inhalt |
|--------|--------|
| **[template-engines/](template-engines/)** | 7 Server-Side Template Engines |
| **[microservices/](microservices/)** | Eureka, User, Order, API Gateway |

## Voraussetzungen

- Java JDK 21
- Maven 3.8+
- Docker (optional, nur für `microservices/docker-compose.yml`)

## NetBeans

1. **File → Open Project**
2. Parent öffnen: `template-engines` **oder** `microservices`
3. Modul im Projects-Baum auswählen → **Run** (grüner Play-Button)

**Wichtig:** Template-Engines und Microservices nicht parallel mit denselben Ports (8080–8082) starten.

---

## Teil 1: Template Engines

Parent: `template-engines/`

| Modul | Port | URL |
|-------|------|-----|
| **thymeleaf-demo** | 8080 | http://localhost:8080 |
| **freemarker-demo** | 8081 | http://localhost:8081 |
| **jte-demo** | 8082 | http://localhost:8082 |
| **groovy-demo** | 8083 | http://localhost:8083 |
| **pebble-demo** | 8084 | http://localhost:8084 |
| **mustache-demo** | 8085 | http://localhost:8085 |
| **jsp-demo** | 8086 | http://localhost:8086 |

Jedes Demo zeigt dieselbe Personen-Liste (DataFaker) mit der jeweiligen Template Engine.

```bash
cd template-engines/thymeleaf-demo
mvn spring-boot:run
```

Alle Module bauen:

```bash
cd template-engines
mvn clean package -DskipTests
```

---

## Teil 2: Microservices

Parent: `microservices/`

| Service | Port | Funktion |
|---------|------|----------|
| **eureka-server** | 8761 | Service Discovery |
| **user-service** | 8081 | User-API |
| **order-service** | 8082 | Orders + Feign → User |
| **api-gateway** | 8080 | Zentraler Einstieg |

### Startreihenfolge

1. **eureka-server** – warten bis http://localhost:8761 erreichbar ist
2. **user-service**
3. **order-service**
4. **api-gateway**

In NetBeans: jedes Modul einzeln **Run**. Oder per Maven (je ein Terminal):

```bash
cd microservices/eureka-server && mvn spring-boot:run
cd microservices/user-service && mvn spring-boot:run
cd microservices/order-service && mvn spring-boot:run
cd microservices/api-gateway && mvn spring-boot:run
```

### Testen

```bash
# Eureka Dashboard
# http://localhost:8761

# Direkt
curl http://localhost:8081/api/users
curl http://localhost:8082/api/orders/1/with-user

# Über API Gateway (empfohlen)
curl http://localhost:8080/api/users
curl http://localhost:8080/api/orders/1/with-user
```

### Docker Compose (optional)

```bash
cd microservices
mvn clean package -DskipTests
docker-compose up -d
```

---

## Quellen

- Template Engines: https://github.com/ElyndraValen/tag10-springAufbau-template-engines-demos
- Microservices: https://github.com/ElyndraValen/tag10-springAufbau-microservices-demos
