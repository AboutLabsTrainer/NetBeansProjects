# Tag5+6 – Custom Tags (Simple & Classic)
Namespace: `de.ibb` · Projektname: `Tag56` · Server: Payara 7 (Jakarta EE 10)

**Inhalt**
- SimpleTagSupport (Tag 5): Greeting, Badge, Truncate, Repeat
- Classic (Tag 6): GreetingClassic (TagSupport), BadgeClassic (TagSupport), TruncateClassic (BodyTagSupport), RepeatClassic (BodyTagSupport)
- Zwei TLDs:
  - `WEB-INF/tlds/simple-tags.tld` → URI `https://de.ibb/simple` (Prefix z. B. `fleet`)
  - `WEB-INF/tlds/classic-tags.tld` → URI `https://de.ibb/classic` (Prefix z. B. `fleetx`)

**Start in NetBeans (Payara 7)**
1. Projekt über `pom.xml` öffnen, Server **Payara 7** wählen
2. Run/Deploy
3. Testen:
   - `http://localhost:8080/Tag56/` (Start)
   - `http://localhost:8080/Tag56/demo-simple.jsp` (SimpleTagSupport)
   - `http://localhost:8080/Tag56/demo-classic.jsp` (BodyTagSupport/TagSupport)

**Didaktischer Hinweis**
- SimpleTagSupport: kompakter Lifecycle (`doTag()`).
- BodyTagSupport: gepufferter Body (`EVAL_BODY_BUFFERED`, `doAfterBody()`) für Iteration/Transformation.
- Beide Modelle können im selben WAR koexistieren; getrennte TLDs halten die Übungen übersichtlich.
