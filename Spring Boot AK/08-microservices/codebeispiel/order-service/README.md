Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# Order Service

Microservice zur Verwaltung von Bestellungen mit **Feign Client** zur Kommunikation mit User Service.

---

## 📋 Übersicht

Der Order Service demonstriert:
- **Feign Client** für Service-zu-Service Kommunikation
- Automatische Service Discovery über Eureka
- Load Balancing ohne manuelle Konfiguration
- REST-API für Orders

**Port:** 8082

---

## 🚀 Schnellstart

### **Standalone starten:**
```bash
cd order-service
mvn spring-boot:run
```

### **Vom Root:**
```bash
cd ..
./build-all.sh
./start-all.sh
```

⚠️ **Wichtig:** User Service muss laufen, sonst kann Feign Client keine User holen!

---

## 📡 API Endpoints

### **Alle Orders abrufen**
```bash
GET http://localhost:8082/api/orders

# Response
[
  {
    "id": 1,
    "userId": 5,
    "product": "Laptop",
    "quantity": 2,
    "price": 1999.99,
    "status": "SHIPPED"
  },
  ...
]
```

### **Einzelne Order abrufen**
```bash
GET http://localhost:8082/api/orders/{id}

curl http://localhost:8082/api/orders/1
```

### **🎯 Order MIT User-Info (Feign Client!)**
```bash
GET http://localhost:8082/api/orders/{id}/with-user

curl http://localhost:8082/api/orders/1/with-user

# Response
{
  "order": {
    "id": 1,
    "userId": 5,
    "product": "Laptop",
    "quantity": 2,
    "price": 1999.99,
    "status": "SHIPPED"
  },
  "user": {
    "id": 5,
    "username": "max.mustermann",
    "email": "max@example.de",
    "firstName": "Max",
    "lastName": "Mustermann",
    "city": "München"
  }
}
```

**Das ist die Magie von Feign!** Order Service ruft User Service automatisch auf!

---

## 🏗️ Projekt-Struktur

```
order-service/
├── src/main/java/de/ibb/orderservice/
│   ├── OrderServiceApplication.java    # Main mit @EnableFeignClients
│   ├── model/
│   │   ├── Order.java                  # Order Entity
│   │   ├── User.java                   # User DTO
│   │   └── OrderWithUser.java          # Combined DTO
│   ├── client/
│   │   └── UserClient.java             # 🎯 FEIGN CLIENT!
│   ├── service/
│   │   └── OrderService.java           # Business Logic
│   └── controller/
│       └── OrderController.java        # REST Controller
├── src/main/resources/
│   └── application.yml
├── pom.xml
└── Dockerfile
```

---

## 🔧 Technologie-Stack

- **Spring Boot 3.2.0**
- **Spring Web** - REST API
- **Spring Cloud Netflix Eureka Client** - Service Discovery
- **Spring Cloud OpenFeign** - Deklarativer HTTP-Client
- **DataFaker 2.0.2** - Testdaten für Orders
- **Lombok** - Clean Code
- **Java 21**

---

## 📦 Dependencies

```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Eureka Client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
    <!-- Feign Client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    
    <!-- DataFaker -->
    <dependency>
        <groupId>net.datafaker</groupId>
        <artifactId>datafaker</artifactId>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

---

## ⚙️ Konfiguration

**application.yml:**
```yaml
server:
  port: 8082

spring:
  application:
    name: order-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
```

---

## 🎯 Feign Client - Die Magie!

### **UserClient Interface:**

```java
@FeignClient(name = "user-service")  // ← Eureka Service-Name!
public interface UserClient {
    
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}
```

**Das ist ALLES!** Kein HTTP-Code, keine URL-Konfiguration!

### **Wie es funktioniert:**

```
1. Order Service ruft: userClient.getUserById(5)

2. Feign fragt Eureka: "Wo ist user-service?"

3. Eureka antwortet: "localhost:8081" (oder 8083 wenn 2 Instanzen)

4. Feign macht HTTP-Call: GET http://localhost:8081/api/users/5

5. Feign parsed JSON zu User-Objekt

6. Order Service bekommt User zurück

→ Alles automatisch! ✨
```

---

## 🔥 Verwendung im Service

**OrderService.java:**
```java
@Service
public class OrderService {
    
    private final UserClient userClient;  // ← Feign Client injiziert!
    
    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }
    
    public OrderWithUser getOrderWithUser(Long orderId) {
        // 1. Order holen (lokal)
        Order order = getOrderById(orderId);
        
        // 2. User holen (Feign Client → HTTP-Call zu User Service!)
        User user = userClient.getUserById(order.getUserId());
        
        // 3. Kombinieren
        return new OrderWithUser(order, user);
    }
}
```

**Sieht aus wie lokaler Methoden-Aufruf, ist aber HTTP über Netzwerk!** 🎉

---

## 🧪 Testen

### **1. Services starten:**
```bash
# Terminal 1 - Eureka
cd eureka-server && mvn spring-boot:run

# Terminal 2 - User Service (MUSS laufen!)
cd user-service && mvn spring-boot:run

# Terminal 3 - Order Service
cd order-service && mvn spring-boot:run
```

### **2. Feign Client testen:**
```bash
# Dieser Call macht intern einen HTTP-Request zu User Service!
curl http://localhost:8082/api/orders/1/with-user
```

### **3. Load Balancing testen:**
```bash
# Starte 2. User Service Instanz
cd user-service
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8083

# Jetzt verteilt Feign Requests zwischen 8081 und 8083!
curl http://localhost:8082/api/orders/1/with-user  # → 8081
curl http://localhost:8082/api/orders/2/with-user  # → 8083
curl http://localhost:8082/api/orders/3/with-user  # → 8081
```

**Schaue in die Logs beider User Services!** Du siehst Requests abwechselnd!

---

## 📊 Models

### **Order.java:**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;         // ← Foreign Key zu User
    private String product;
    private Integer quantity;
    private BigDecimal price;
    private String status;
}
```

### **User.java (DTO):**
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
}
```

### **OrderWithUser.java:**
```java
@Data
@AllArgsConstructor
public class OrderWithUser {
    private Order order;
    private User user;
}
```

---

## 🎓 Lernziele

Nach dem Durcharbeiten verstehst du:
- ✅ **Feign Client** für Service-zu-Service Kommunikation
- ✅ Deklarative REST-Clients (Interface statt Code)
- ✅ Automatisches Load Balancing über Eureka
- ✅ Service Discovery Integration
- ✅ DTOs für kombinierte Daten
- ✅ Microservices-Kommunikations-Patterns

---

## 🔧 Feign Features

### **1. Fallback (wenn User Service down ist):**

```java
// Fallback-Klasse
@Component
public class UserClientFallback implements UserClient {
    
    @Override
    public User getUserById(Long id) {
        // Default-User wenn Service down
        return new User(id, "Unknown", "unknown@example.com");
    }
}

// Im FeignClient registrieren
@FeignClient(
    name = "user-service",
    fallback = UserClientFallback.class  // ← Fallback aktiviert
)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}
```

**Resultat:** App läuft auch wenn User Service down ist! ✅

---

### **2. Timeout konfigurieren:**

```yaml
feign:
  client:
    config:
      user-service:
        connectTimeout: 5000
        readTimeout: 10000
```

---

### **3. Logging aktivieren:**

```yaml
logging:
  level:
    de.ibb.orderservice.client: DEBUG

feign:
  client:
    config:
      default:
        loggerLevel: FULL  # NONE, BASIC, HEADERS, FULL
```

**Logs zeigen dann:**
```
[UserClient#getUserById] ---> GET http://user-service/api/users/5
[UserClient#getUserById] ---> END HTTP (0-byte body)
[UserClient#getUserById] <--- HTTP/1.1 200 (145ms)
[UserClient#getUserById] content-type: application/json
[UserClient#getUserById] {"id":5,"username":"..."}
[UserClient#getUserById] <--- END HTTP (82-byte body)
```

---

### **4. Retry konfigurieren:**

```java
@Configuration
public class FeignConfig {
    
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
            100,    // Start-Intervall: 100ms
            1000,   // Max-Intervall: 1 Sekunde
            3       // Max 3 Versuche
        );
    }
}
```

---

## 🐳 Docker

### **Image bauen:**
```bash
mvn clean package -DskipTests
docker build -t ibb/order-service:latest .
```

### **Container starten:**
```bash
docker run -p 8082:8082 \
  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka:8761/eureka/ \
  ibb/order-service:latest
```

---

## 🔥 Erweiterte Feign Patterns

### **1. Mehrere Services aufrufen:**

```java
@Service
public class OrderService {
    
    private final UserClient userClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        // 1. User validieren (Feign)
        User user = userClient.getUserById(request.getUserId());
        
        // 2. Produkt reservieren (Feign)
        Product product = productClient.getProductById(request.getProductId());
        productClient.reserveProduct(reservation);
        
        // 3. Zahlung verarbeiten (Feign)
        PaymentResult payment = paymentClient.processPayment(paymentRequest);
        
        // 4. Order erstellen (lokal)
        return orderRepository.save(order);
    }
}
```

---

### **2. POST/PUT/DELETE mit Feign:**

```java
@FeignClient(name = "user-service")
public interface UserClient {
    
    // GET
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
    
    // POST
    @PostMapping("/api/users")
    User createUser(@RequestBody User user);
    
    // PUT
    @PutMapping("/api/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User user);
    
    // DELETE
    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable Long id);
}
```

---

### **3. Headers & Query-Parameter:**

```java
@FeignClient(name = "user-service")
public interface UserClient {
    
    // Mit Header
    @GetMapping("/api/users/{id}")
    User getUserById(
        @PathVariable Long id,
        @RequestHeader("Authorization") String token
    );
    
    // Mit Query-Parameter
    @GetMapping("/api/users/search")
    List<User> searchUsers(@RequestParam String name);
}
```

---

## 🐛 Troubleshooting

**Problem:** `FeignException$NotFound: [404] during [GET] to [http://user-service/api/users/5]`
- User Service läuft nicht
- User mit ID 5 existiert nicht
- URL im FeignClient falsch

**Problem:** `No instances available for user-service`
- User Service nicht bei Eureka registriert
- Eureka Server läuft nicht
- `spring.application.name` falsch in User Service

**Problem:** Feign macht keine Load Balancing
- Nur eine User Service Instanz läuft
- Starte 2. Instanz auf anderem Port
- Warte 30 Sekunden (Eureka Update)

---

## 📚 Weiterführende Links

- **Feign Docs**: https://spring.io/projects/spring-cloud-openfeign
- **Eureka Docs**: https://spring.io/projects/spring-cloud-netflix
- **Circuit Breaker**: https://resilience4j.readme.io/

---

## 🎯 Vergleich: Feign vs. RestTemplate

| Feature | RestTemplate | Feign |
|---------|--------------|-------|
| **Code** | 20+ Zeilen | 3 Zeilen |
| **Lesbarkeit** | ❌ Schwer | ✅ Einfach |
| **Load Balancing** | Manuell | ✅ Automatisch |
| **Eureka** | Manuell | ✅ Automatisch |
| **Fallback** | Manuell | ✅ Einfach |
| **Testing** | Schwer | ✅ Einfach (Mock Interface) |

**Franz-Martin's Fazit:**
> "Feign ist wie Lombok für HTTP-Calls.  
> Du schreibst Interface, Spring macht REST.  
> Einmal verwendet, nie mehr zurück zu RestTemplate!"

---

**Teil der Microservices Demo Collection** - [Zurück zum Haupt-README](../README.md)
