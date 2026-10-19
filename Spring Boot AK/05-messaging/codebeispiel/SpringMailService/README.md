Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# SpringMailService

## Was dieses Beispiel übt

Mailversand mit `JavaMailSender`: eine Textmail, eine HTML-Mail aus einem Thymeleaf-Template und eine asynchrone Mail auf einem eigenen Thread-Pool. SMTP zeigt auf MailDev.

## Wichtige Klassen in Lesereihenfolge

1. `SpringMailServiceApplication` – Start, Async ist aktiv
2. `AsyncConfig` – Thread-Pool für asynchrone Methoden
3. `EmailService` – Versand, eine Methode läuft asynchron
4. `EmailController` – HTTP unter `/api/email`

## Start

In NetBeans `SpringMailServiceApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8083** (`server.port` in `application.properties`). SMTP ist `localhost:1025`.

MailDev vorher starten:

```bash
docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

Die Weboberfläche liegt auf http://localhost:1080.

## Ausprobieren

1. http://localhost:8083/api/email/send?to=test@test.de aufrufen und die Textmail in MailDev prüfen.
2. http://localhost:8083/api/email/html?to=test@test.de&name=Max aufrufen und die HTML-Mail prüfen.
3. http://localhost:8083/api/email/async?to=test@test.de&name=Max aufrufen. Die HTTP-Antwort kommt sofort, die Mail trifft kurz danach in MailDev ein.
