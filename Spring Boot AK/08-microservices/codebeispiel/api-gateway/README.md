Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# API Gateway

Spring Cloud Gateway als zentraler Einstiegspunkt für alle Microservices.

---

## 📋 Übersicht

Das API Gateway:
- **Einziger Einstiegspunkt** für alle Clients
- Routet Requests an die richtigen Services
- **Load Balancing** über Eureka automatisch
- Zentrale Stelle für Security, Rate Limiting, etc.

**Port:** 8080

---

## 🚀 Schnellstart

### **Standalone starten:**
```bash
cd api-gateway
mvn spring-boot:run
```

### **Vom Root:**
```bash
cd ..
./build-all.sh
./start-all.sh
```

⚠️ **Wichtig:** Eureka Server und Services müssen laufen!

---

## 📡 Gateway Routes

Das Gateway routet automatisch zu den Services:

| Client Request | Gateway Route | Target Service |
|----------------|---------------|----------------|
| `GET /api/users` | `lb://user-service` | User Service (8081) |
| `GET /api/users/1` | `lb://user-service` | User Service (8081) |
| `GET /api/orders` | `lb://order-service` | Order Service (8082) |
| `GET /api/orders/1/with-user` | `lb://order-service` | Order Service (8082) |

**`lb://`** = Load Balanced via Eureka! 🎯

---

## 🧪 Testen

### **Via Gateway (empfohlen!):**
```bash
# User Service über Gateway
curl http://localhost:8080/api/users
curl http://localhost:8080/api/users/1

# Order Service über Gateway
curl http://localhost:8080/api/orders
curl http://localhost:8080/api/orders/1/with-user
```

### **Direkt zu Services (nicht empfohlen):**
```bash
# Direkter Zugriff (bypassed Gateway)
curl http://localhost:8081/api/users
curl http://localhost:8082/api/orders
```

**In Production nutzen Clients NUR das Gateway!**

---

## 🏗️ Projekt-Struktur

```
api-gateway/
├── src/main/java/de/ibb/gateway/
│   └── ApiGatewayApplication.java      # Main mit @EnableDiscoveryClient
├── src/main/resources/
│   └── application.yml                 # Gateway-Routing-Config
├── pom.xml
└── Dockerfile
```

**So einfach!** Gateway braucht keinen eigenen Business-Code!

---

## 🔧 Technologie-Stack

- **Spring Boot 3.2.0**
- **Spring Cloud Gateway** - Reactive Gateway
- **Spring Cloud Netflix Eureka Client** - Service Discovery
- **Java 21**

---

## 📦 Dependencies

```xml
<dependencies>
    <!-- Spring Cloud Gateway -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    
    <!-- Eureka Client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

**WICHTIG:** Spring Cloud Gateway ist **reaktiv** (Webflux)!  
→ KEIN `spring-boot-starter-web`!

---

## ⚙️ Konfiguration

**application.yml:**
```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway
  
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true              # Automatisches Routing aktivieren
          lower-case-service-id: true  # Service-Namen in lowercase
      
      routes:
        # Route für User Service
        - id: user-service
          uri: lb://user-service     # lb = Load Balanced via Eureka!
          predicates:
            - Path=/api/users/**     # Matched alle /api/users/* Requests
        
        # Route für Order Service
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
```

---

## 🎯 Wie Routing funktioniert

### **Request-Flow:**

```
1. Client: GET http://localhost:8080/api/users/5

2. Gateway empfängt Request auf Port 8080

3. Gateway prüft Routes:
   - Path=/api/users/** matched? ✅ JA!
   - Route-ID: user-service
   - URI: lb://user-service

4. Gateway fragt Eureka: "Wo ist user-service?"

5. Eureka antwortet: 
   - localhost:8081
   - localhost:8083 (wenn 2 Instanzen)

6. Gateway wählt eine Instanz (Round-Robin):
   → localhost:8081

7. Gateway macht Request:
   GET http://localhost:8081/api/users/5

8. User Service antwortet

9. Gateway leitet Response an Client weiter

→ Client bekommt Antwort! ✅
```

---

## 🔥 Load Balancing Demo

### **1. Starte 2 User Service Instanzen:**
```bash
# Terminal 1
cd user-service
mvn spring-boot:run

# Terminal 2
cd user-service
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8083
```

### **2. Check Eureka:**
```
http://localhost:8761

USER-SERVICE:
- localhost:8081
- localhost:8083
```

### **3. Requests über Gateway:**
```bash
curl http://localhost:8080/api/users/1  # → 8081
curl http://localhost:8080/api/users/2  # → 8083
curl http://localhost:8080/api/users/3  # → 8081
curl http://localhost:8080/api/users/4  # → 8083
```

**Gateway verteilt automatisch!** 🎉

---

## 🎓 Lernziele

Nach dem Durcharbeiten verstehst du:
- ✅ **API Gateway Pattern**
- ✅ Spring Cloud Gateway Routing
- ✅ Load Balancing via `lb://`
- ✅ Service Discovery Integration
- ✅ Predicates (Path Matching)
- ✅ Reactive Gateway (Webflux)

---

## 🔧 Erweiterte Features

### **1. Header hinzufügen:**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - AddRequestHeader=X-Request-Gateway, ApiGateway
```

**Resultat:** Jeder Request bekommt Header `X-Request-Gateway: ApiGateway`

---

### **2. Response-Header:**

```yaml
filters:
  - AddResponseHeader=X-Response-From, Gateway
```

---

### **3. URL Rewrite:**

```yaml
filters:
  - RewritePath=/api/(?<segment>.*), /${segment}
```

**Beispiel:**
```
Client: GET /api/users/5
→ Gateway: GET /users/5 (ohne /api)
```

---

### **4. Rate Limiting:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
```

```yaml
filters:
  - name: RequestRateLimiter
    args:
      redis-rate-limiter.replenishRate: 10  # 10 Requests pro Sekunde
      redis-rate-limiter.burstCapacity: 20
```

---

### **5. Circuit Breaker:**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-reactor-resilience4j</artifactId>
</dependency>
```

```yaml
filters:
  - name: CircuitBreaker
    args:
      name: userServiceCircuit
      fallbackUri: forward:/fallback/users
```

---

### **6. Retry:**

```yaml
filters:
  - name: Retry
    args:
      retries: 3
      statuses: BAD_GATEWAY,GATEWAY_TIMEOUT
```

---

### **7. Timeout:**

```yaml
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 1000  # 1 Sekunde
        response-timeout: 5s   # 5 Sekunden
```

---

## 🎯 Warum API Gateway?

### **Ohne Gateway:**
```
Client 1 → User Service (8081)
Client 2 → Order Service (8082)
Client 3 → Product Service (8083)

Probleme:
❌ Clients müssen alle Service-URLs kennen
❌ CORS für jeden Service einzeln
❌ Authentication für jeden Service einzeln
❌ Keine zentrale Logging/Monitoring
❌ Kein Load Balancing
```

### **Mit Gateway:**
```
Client 1 →┐
Client 2 →├→ API Gateway (8080) →┬→ User Service
Client 3 →┘                       ├→ Order Service
                                  └→ Product Service

Vorteile:
✅ Clients kennen nur Gateway-URL
✅ CORS zentral
✅ Authentication zentral
✅ Logging zentral
✅ Automatisches Load Balancing
✅ Rate Limiting zentral
✅ Security zentral
```

---

## 🔒 Security (Beispiel)

### **JWT-Token Validation im Gateway:**

```java
@Component
public class AuthenticationFilter implements GatewayFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // JWT Token validieren
        String token = request.getHeaders().getFirst("Authorization");
        
        if (token == null || !isValid(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
}
```

**Vorteil:** Security nur einmal im Gateway, nicht in jedem Service!

---

## 🐳 Docker

### **Image bauen:**
```bash
mvn clean package -DskipTests
docker build -t ibb/api-gateway:latest .
```

### **Container starten:**
```bash
docker run -p 8080:8080 \
  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka:8761/eureka/ \
  ibb/api-gateway:latest
```

---

## 📊 Gateway vs. Service Mesh

| Feature | API Gateway | Service Mesh (Istio) |
|---------|-------------|----------------------|
| **Einstiegspunkt** | ✅ Ja | ❌ Nein |
| **External Traffic** | ✅ Ja | ⚠️ Teils |
| **Service-to-Service** | ⚠️ Teils | ✅ Ja |
| **Komplexität** | ✅ Niedrig | ❌ Hoch |
| **Kubernetes** | ⚠️ Optional | ✅ Erforderlich |

**Für unsere Demo:** API Gateway ist perfekt! ✅

---

## 🐛 Troubleshooting

**Problem:** `503 Service Unavailable`
- Target Service läuft nicht
- Service nicht bei Eureka registriert
- Warte 30 Sekunden (Eureka Update)

**Problem:** Gateway routet nicht
- Check `spring.cloud.gateway.routes` in application.yml
- Check Predicates (Path korrekt?)
- Check Service-Name in Eureka

**Problem:** `No instances available for user-service`
- User Service nicht bei Eureka
- `spring.application.name` falsch?
- Eureka Server läuft?

---

## 📚 Weiterführende Links

- **Spring Cloud Gateway Docs**: https://spring.io/projects/spring-cloud-gateway
- **Gateway Filters**: https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories
- **Predicates**: https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories

---

## 🎯 Production-Ready Checklist

Für Production brauchst du noch:

- [ ] **Security** (JWT, OAuth2)
- [ ] **Rate Limiting** (Redis)
- [ ] **Circuit Breaker** (Resilience4j)
- [ ] **Monitoring** (Prometheus/Grafana)
- [ ] **Distributed Tracing** (Zipkin/Jaeger)
- [ ] **Logging** (ELK Stack)
- [ ] **CORS Configuration**
- [ ] **HTTPS/SSL**
- [ ] **Health Checks**
- [ ] **Metrics** (Actuator)

---

**Teil der Microservices Demo Collection** - [Zurück zum Haupt-README](../README.md)
