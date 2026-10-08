# 03-auth-db – Jakarta EE Authentication (DB)
Namespace: `de.ibb` · Projektname: `03-auth-db` · Server: Payara 7 (Jakarta EE 10)

**Mini‑Challenge umgesetzt (gemäß Kursseite):**
- Login gegen **MySQL** (Tabelle `jwebak03_customer`)
- **SHA‑256** Passwort‑Hashing
- **PreparedStatement** (SQL‑Injection‑Schutz)
- **Session‑basiert** (Customer in Session)

## Start (NetBeans + Payara 7)
1. Projekt über `pom.xml` öffnen, als Server **Payara 7** wählen
2. DB vorbereiten: `src/main/webapp/WEB-INF/sql/schema.sql` in MySQL ausführen (nutzt DB `jwebak_tag03_auth`)
3. In `WEB-INF/web.xml` DB‑Zugangsdaten (context‑param) anpassen (Standard: `jwebak_tag03_auth` / `jwebak_tag03_auth`)
4. Run/Deploy → Browser:
   - `http://localhost:8080/03-auth-db/signin.jsp` (Login)
   - nach Login: `index.jsp` (Dashboard)
   - Logout: `signout.jsp`

Demo‑User: `test@example.com / test123` (siehe SQL‑Seed)

## Hinweise
- MySQL Treiber: `com.mysql.cj.jdbc.Driver`
- Sicherheits‑Basics: SHA‑256‑Hashing & Prepared Statements (OWASP)
- Session‑Management: Servlet Session

Viel Erfolg beim Unterrichten!
