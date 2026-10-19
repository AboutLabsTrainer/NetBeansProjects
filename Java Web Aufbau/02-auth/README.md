# 02 Auth

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Das Passwort liegt als SHA-256-Hex in der Datenbank. Der Vergleich läuft über ein PreparedStatement, die Session merkt sich den Kunden.

## Codebeispiel

- Context-Pfad: `/03-auth-db`
- URL: http://localhost:8080/03-auth-db/signin.jsp
- Deploy: Ordner `codebeispiel` auf Payara 6. Datenbank `jwebak_tag03_auth`. Demo-Login `test@example.com` / `test123`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`Passwords.sha256Hex` bildet SHA-256 über die UTF-8-Bytes und schreibt jedes Byte als zwei kleine Hex-Zeichen.

## Stufe 2

`LoginCheck.accept` verlangt eine E-Mail mit genau einem `@`, das nicht am Rand steht. Das Passwort muss zum gespeicherten Hash passen.

## Abnahme

- Stufe 1: `test123` ergibt `ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae`.
- Stufe 2: `test@example.com` mit diesem Hash ist gültig, eine Adresse ohne `@` und ein falsches Passwort sind es nicht.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
