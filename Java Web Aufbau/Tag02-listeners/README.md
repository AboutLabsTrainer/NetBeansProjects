# Tag2 – Listener meistern (Event‑Driven)
Namespace: `de.ibb` · Projektname: `Tag2` · Server: Payara 6 (Jakarta EE 10)

**Mini‑Challenge umgesetzt:**  
1) **Application‑Startup** (ServletContextListener): Start‑Zeit loggen, Config laden (`environment`, `appVersion`)  
2) **Session‑Tracking** (HttpSessionListener): aktive Sessions, Peak‑Sessions, Session‑Duration  
3) **Request‑Performance** (ServletRequestListener): Dauer messen, Kategorien **Fast/OK/Slow**, Histogram  
4) **Admin‑Dashboard**: `/admin/monitor` HTML + **Bonus** `/admin/api/metrics` (JSON)

## Start (NetBeans)
1. Projekt über `pom.xml` öffnen, Server **Payara 6** setzen  
2. **Run/Deploy**  
3. Browser: `http://localhost:8080/Tag2/` → Links zum Dashboard & JSON

## Technische Hinweise
- Listener in `WEB-INF/web.xml` registriert; zusätzlich `@WebListener`.  
- Thread‑Safety via `AtomicInteger/AtomicLong`, gemeinsame Ablage im `ServletContext`.  
- JSON ohne externe Library (einfacher Writer).
