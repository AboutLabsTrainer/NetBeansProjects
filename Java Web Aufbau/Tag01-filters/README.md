# Filter Demo - Mini-Challenge

Dieses Projekt demonstriert die Implementierung von Filtern in Jakarta EE gemäß der Mini-Challenge aus dem Artikel ["Filter meistern – Request/Response Interceptors in Jakarta EE"](https://www.java-developer.online/filter-meistern-response-interceptors/).

## Projekt-Übersicht

Das Projekt enthält vier Filter:
- **EncodingFilter**: Setzt UTF-8 Encoding für alle Requests
- **LoggingFilter**: Loggt alle Requests mit Timestamp, Method, URL, Duration und Status
- **AdminSecurityFilter**: Schützt `/admin/*` URLs und leitet nicht eingeloggte User zu `/login` um
- **CounterFilter** (Bonus): Zählt alle Requests thread-safe mit AtomicInteger

## 3. Test-Szenarien

### Test 1: EncodingFilter + LoggingFilter + CounterFilter

**Ziel:** Überprüfen, dass die Basis-Filter korrekt funktionieren.

1. **Browser öffnen:** `http://localhost:8080/FilterDemo/`
   - **Erwartung:** Homepage wird angezeigt mit "Request-Count: 1"

2. **Mehrfach Reload (F5)**
   - **Erwartung:** Request-Count steigt bei jedem Reload (2, 3, 4, ...)

3. **Server-Console prüfen**
   - **Erwartung:** Logging-Ausgabe für jeden Request:
   ```
   === REQUEST START ===
   Time: 14:32:15.342
   Method: GET
   URL: /FilterDemo/
   Status: 200
   Duration: 47ms
   === REQUEST END ===
   ```

**Was du prüfst:**
- ✅ **EncodingFilter:** UTF-8 wird gesetzt (Umlaute funktionieren korrekt)
- ✅ **LoggingFilter:** Jeder Request wird geloggt (Timestamp, Method, URL, Duration, Status)
- ✅ **CounterFilter:** Request-Count wird angezeigt und steigt korrekt

---

### Test 2: AdminSecurityFilter – ohne Login

**Ziel:** Überprüfen, dass der Security-Filter unautorisierte Zugriffe blockt.

1. **Direkt aufrufen:** `http://localhost:8080/FilterDemo/admin`
   - **Erwartung:** Automatischer Redirect zu `/FilterDemo/login`

2. **Server-Console prüfen**
   - **Erwartung:** LoggingFilter läuft trotzdem (Request wird geloggt)
   - **Erwartung:** AdminSecurityFilter blockt den Request (kein `chain.doFilter()`)

**Was du prüfst:**
- ✅ **Security-Filter greift:** Nur bei `/admin/*` URLs
- ✅ **Redirect funktioniert:** Nicht eingeloggte User werden zu Login umgeleitet
- ✅ **Request erreicht Servlet nicht:** AdminServlet wird nicht aufgerufen

---

### Test 3: AdminSecurityFilter – mit Login

**Ziel:** Überprüfen, dass eingeloggte User Zugriff auf den Admin-Bereich haben.

1. **Login-Seite aufrufen:** `http://localhost:8080/FilterDemo/login`

2. **Formular absenden** (beliebiger Username/Password)
   - **Erwartung:** Redirect zu `/FilterDemo/admin`

3. **Admin-Seite aufrufen:** `http://localhost:8080/FilterDemo/admin`
   - **Erwartung:** "Admin-Bereich" wird angezeigt (kein Redirect mehr)

**Was du prüfst:**
- ✅ **Session wird gesetzt:** `user`-Attribut wird in Session gespeichert
- ✅ **Security-Filter lässt durch:** Eingeloggte User können Admin-Bereich erreichen
- ✅ **AdminServlet wird erreicht:** Request kommt beim Servlet an

---

### Test 4: Performance-Warning (optional)

**Ziel:** Die "SLOW REQUEST"-Warnung im LoggingFilter testen.

Um die Performance-Warnung zu sehen, kannst du temporär im `LoggingFilter` eine Verzögerung einbauen:

```java
// Temporär in LoggingFilter.doFilter() einfügen (nur zum Testen):
Thread.sleep(600); // 600ms warten
```

Dann einen Request machen → Console zeigt:
```
⚠️ SLOW REQUEST: /FilterDemo/ took 612ms
```

**Was du prüfst:**
- ✅ **Performance-Warning:** Wird bei Requests > 500ms angezeigt
- ✅ **Logging funktioniert:** Auch bei langen Requests wird alles korrekt geloggt

---

## 4. Filter-Chain in der Console beobachten

Die Filter laufen in dieser **Reihenfolge**:

1. **EncodingFilter** (setzt UTF-8)
2. **LoggingFilter** (loggt Request-Start)
3. **AdminSecurityFilter** (nur bei `/admin/*`)
4. **CounterFilter** (zählt Requests)
5. **Servlet** (HomeServlet/AdminServlet/LoginServlet)
6. **Filter rückwärts** (LoggingFilter loggt Request-End)

**In der Console siehst du:**
- `EncodingFilter initialized with: UTF-8` (beim Server-Start)
- `LoggingFilter initialized` (beim Server-Start)
- `CounterFilter initialized` (beim Server-Start)
- Pro Request: Logging-Ausgabe mit BEFORE/AFTER

**Beispiel-Console-Ausgabe:**
```
EncodingFilter initialized with: UTF-8
LoggingFilter initialized
CounterFilter initialized

=== REQUEST START ===
Time: 14:32:15.342
Method: GET
URL: /FilterDemo/
Status: 200
Duration: 47ms
=== REQUEST END ===

=== REQUEST START ===
Time: 14:32:16.123
Method: GET
URL: /FilterDemo/admin
Status: 302
Duration: 12ms
=== REQUEST END ===
```

---

## 5. Browser DevTools nutzen

**F12 → Network-Tab öffnen:**

- **Headers prüfen:**
  - `Content-Type: text/html;charset=UTF-8` (EncodingFilter hat das gesetzt)
  - `Status: 200` (normal) oder `302` (Redirect bei Security)

- **Response-Zeit:**
  - Sollte mit der Duration im Logging übereinstimmen

- **Request-Details:**
  - Method (GET/POST)
  - URL
  - Status Code

**Was du prüfst:**
- ✅ **Encoding-Header:** Wird korrekt gesetzt
- ✅ **Status-Codes:** 200 (normal), 302 (Redirect bei Security)
- ✅ **Response-Zeit:** Stimmt mit Logging-Duration überein

---

## 6. Verschiedene HTTP-Methoden testen

**GET-Request:**
- `http://localhost:8080/FilterDemo/`
- Console: `Method: GET`

**POST-Request:**
- Login-Formular absenden
- Console: `Method: POST`

**Was du prüfst:**
- ✅ **LoggingFilter zeigt korrekte Methode:** GET und POST werden unterschieden
- ✅ **Filter funktionieren bei beiden Methoden:** Encoding, Logging, Counter

---

## Troubleshooting

### Problem: Filter werden nicht ausgeführt

**Lösung:**
- Server neu starten nach Deployment
- Prüfen: `web.xml` korrekt deployt?
- Filter-Initialisierung in Console sichtbar?

**Check:**
```bash
# In Server-Console sollte erscheinen:
EncodingFilter initialized with: UTF-8
LoggingFilter initialized
CounterFilter initialized
```

---

### Problem: "Response already committed" Exception

**Grund:** Du versuchst, Response-Header zu ändern, nachdem Output geschrieben wurde.

**Lösung:** Header müssen **VOR** `chain.doFilter()` gesetzt werden (EncodingFilter macht das richtig).

**Check:**
```java
// ✅ RICHTIG:
response.setContentType("text/html;charset=UTF-8");
chain.doFilter(request, response);

// ❌ FALSCH:
chain.doFilter(request, response);
response.setContentType("text/html;charset=UTF-8"); // Zu spät!
```

---

### Problem: Request-Count zeigt -1

**Grund:** CounterFilter läuft nicht oder Request-Attribut wird nicht gesetzt.

**Lösung:**
- CounterFilter muss VOR dem Servlet laufen (in `web.xml` Reihenfolge prüfen)
- Prüfen: `CounterFilter initialized` in Console?

**Check in web.xml:**
```xml
<!-- CounterFilter muss VOR Servlet-Mappings stehen -->
<filter-mapping>
    <filter-name>CounterFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

---

### Problem: Umlaute funktionieren nicht

**Grund:** EncodingFilter läuft nicht oder läuft zu spät in der Chain.

**Lösung:** EncodingFilter muss **ERSTER** Filter sein (ist in `web.xml` so konfiguriert).

**Check in web.xml:**
```xml
<!-- EncodingFilter muss ZUERST in der Chain sein! -->
<filter-mapping>
    <filter-name>EncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

---

### Problem: Security-Filter leitet nicht um

**Grund:** Session wird nicht korrekt geprüft oder Redirect funktioniert nicht.

**Lösung:**
- Prüfen: `AdminSecurityFilter` läuft bei `/admin/*`?
- Prüfen: Session wird mit `getSession(false)` geholt (keine neue Session)?
- Prüfen: Redirect-Pfad ist korrekt?

**Check:**
```java
// In AdminSecurityFilter:
HttpSession session = httpRequest.getSession(false); // false = keine neue Session!
if (session == null || session.getAttribute("user") == null) {
    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
    return; // WICHTIG: Kein chain.doFilter()!
}
```

---

## Erwartete Ergebnisse

Nach erfolgreichem Testen solltest du:

✅ **EncodingFilter** für alle Requests UTF-8 setzen  
✅ **LoggingFilter** alle Requests mit Details loggen  
✅ **AdminSecurityFilter** `/admin/*` schützen  
✅ **CounterFilter** (Bonus) Request-Count anzeigen  
✅ **Filter-Reihenfolge** in `web.xml` korrekt definiert  

**Viel Erfolg beim Testen!** 🚀
