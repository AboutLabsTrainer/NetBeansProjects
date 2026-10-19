Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Tag 9: Spring Boot Actuator – Production Monitoring

Lauffähiges Maven-Projekt für NetBeans (Spring Boot 3.5.16 / Java 21). Hibernate kommt aus dem Boot-BOM.

## Start in NetBeans

1. Projektordner als Maven-Projekt öffnen (`pom.xml`)
2. `ActuatorMonitoringApplication` ausführen **oder** Terminal:

```bash
mvn spring-boot:run
```

App: http://localhost:8080

## Actuator testen

```bash
# Health (public)
curl http://localhost:8080/actuator/health

# Info (public)
curl http://localhost:8080/actuator/info

# Prometheus (public)
curl http://localhost:8080/actuator/prometheus

# Metrics (Basic Auth: actuator / actuator-secret-password)
curl -u actuator:actuator-secret-password http://localhost:8080/actuator/metrics

# Custom Endpoint
curl -u actuator:actuator-secret-password http://localhost:8080/actuator/database-queries
```

## User registrieren (erzeugt Micrometer-Metriken + DB-Queries)

```bash
curl -X POST http://localhost:8080/api/users ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"alice\",\"email\":\"alice@example.com\",\"password\":\"secret1\"}"

curl http://localhost:8080/api/users
curl -u actuator:actuator-secret-password http://localhost:8080/actuator/metrics/users.registered
```

## Monitoring-Stack (optional)

App zuerst in NetBeans starten, dann:

```bash
docker compose up -d
```

| Service       | URL                      | Login        |
|---------------|--------------------------|--------------|
| Prometheus    | http://localhost:9090    | –            |
| Grafana       | http://localhost:3000    | admin/admin  |
| Alertmanager  | http://localhost:9093    | –            |

Prometheus scrapt `host.docker.internal:8080/actuator/prometheus` (App außerhalb von Docker).

## Credentials

| Zweck              | User     | Passwort                   |
|--------------------|----------|----------------------------|
| Actuator (geschützt)| actuator | actuator-secret-password   |
| Grafana            | admin    | admin                      |

## SMTP-Health (optional)

Default: `health.email.check-connection=false` → Email-Indicator UP ohne SMTP.

Für echten Check (wie im Blog): Mail-Server starten und in `application.properties`:

```properties
health.email.check-connection=true
spring.mail.host=localhost
spring.mail.port=1025
```
