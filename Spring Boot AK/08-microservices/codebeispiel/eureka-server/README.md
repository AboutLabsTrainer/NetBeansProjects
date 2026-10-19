Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# Eureka Server

Netflix Eureka Service Discovery Server für Microservices.

## 🚀 Schnellstart

```bash
mvn spring-boot:run
```

Öffne: `http://localhost:8761`

## 📋 Was ist Eureka?

Eureka ist eine **Service Registry** - ein zentraler Ort wo sich alle Microservices registrieren und andere Services finden können.

### **Problem ohne Eureka:**
```
Order Service möchte User Service aufrufen:
→ Wo ist User Service?
→ Hardcoded: http://localhost:8081 ❌
→ Was wenn User Service auf anderem Server läuft?
→ Was wenn es 5 Instanzen von User Service gibt?
```

### **Lösung mit Eureka:**
```
1. User Service startet → registriert sich bei Eureka
2. Order Service startet → registriert sich bei Eureka  
3. Order Service fragt Eureka: "Wo ist USER-SERVICE?"
4. Eureka antwortet: "Auf localhost:8081 und localhost:8082"
5. Order Service ruft eine der Instanzen auf ✅
```

## 🎯 Features

- ✅ **Service Registry**: Alle Services registrieren sich hier
- ✅ **Service Discovery**: Services finden sich gegenseitig
- ✅ **Health Checks**: Eureka prüft ob Services noch leben
- ✅ **Load Balancing**: Verteilt Requests auf mehrere Instanzen
- ✅ **Dashboard**: Web-UI unter http://localhost:8761

## 📊 Eureka Dashboard

Nach dem Start kannst du unter `http://localhost:8761` sehen:

- **Instances currently registered**: Welche Services sind registriert
- **Status**: Gesundheitsstatus der Services
- **Availability Zones**: Geografische Verteilung

## 🔧 Konfiguration erklärt

### **application.yml:**

```yaml
server:
  port: 8761  # Standard Eureka Port

eureka:
  client:
    register-with-eureka: false  # Server registriert sich nicht selbst
    fetch-registry: false         # Server braucht kein Registry von sich selbst
```

## 🌐 Verwendung in anderen Services

### **1. Dependency hinzufügen:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### **2. Application annotieren:**
```java
@SpringBootApplication
@EnableDiscoveryClient  // Oder @EnableEurekaClient
public class UserServiceApplication {
    // ...
}
```

### **3. application.yml konfigurieren:**
```yaml
spring:
  application:
    name: user-service  # Name unter dem Service registriert wird

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## 📦 Teil der Microservices-Demo

Dieses Projekt ist Teil der **Microservices-Demo Collection**:

1. **Eureka Server** (DU BIST HIER) - Service Discovery
2. **User Service** - Beispiel-Service mit DataFaker
3. **Order Service** - Service mit Feign Client zu User Service
4. **API Gateway** - Zentraler Einstiegspunkt

## 🎓 Lernziele

Nach dem Durcharbeiten verstehst du:
- ✅ Service Discovery Pattern
- ✅ Netflix Eureka Server Setup
- ✅ Wie sich Services bei Eureka registrieren
- ✅ Wie Services sich gegenseitig finden
- ✅ Health Checks & Monitoring

## 🏢 Production-Hinweise

**Für Production würdest du zusätzlich brauchen:**

1. **Hochverfügbarkeit**: Mehrere Eureka Server (Peer Replication)
2. **Security**: Spring Security für Eureka Dashboard
3. **Monitoring**: Integration mit Prometheus/Grafana
4. **Backup**: Persistente Registry (z.B. Redis)

**Beispiel Multi-Server Setup:**
```yaml
# Eureka Server 1
eureka:
  client:
    service-url:
      defaultZone: http://eureka2:8761/eureka/,http://eureka3:8761/eureka/

# Eureka Server 2
eureka:
  client:
    service-url:
      defaultZone: http://eureka1:8761/eureka/,http://eureka3:8761/eureka/
```

## ❓ FAQ

**Q: Warum Port 8761?**  
A: Das ist der Standard-Port für Netflix Eureka Server.

**Q: Was passiert wenn Eureka Server ausfällt?**  
A: Services cachen das Registry. Sie funktionieren weiter, aber neue Services können nicht gefunden werden.

**Q: Wie viele Services kann Eureka handhaben?**  
A: Tausende! Netflix nutzt es für hunderte Services.

**Q: Alternativen zu Eureka?**  
A: Consul, etcd, Zookeeper. Aber Eureka ist am einfachsten in Spring Boot.

## 📚 Weiterführende Links

- **Spring Cloud Netflix Eureka**: https://spring.io/projects/spring-cloud-netflix
- **Netflix OSS Blog**: https://netflixtechblog.com/
- **Microservices Pattern**: https://microservices.io/patterns/service-registry.html


---

**Nächster Schritt:** Starte den **User Service** und beobachte wie er sich bei Eureka registriert!
