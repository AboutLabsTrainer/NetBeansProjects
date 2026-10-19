# 03 Security

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Der Container prüft Rollen an URL-Mustern. `admin` darf die Flächen der anderen Rollen mitbetreten.

## Codebeispiel

- Context-Pfad: `/Tag4`
- URL: http://localhost:8080/Tag4/user/dashboard , http://localhost:8080/Tag4/manager/dashboard , http://localhost:8080/Tag4/admin/dashboard
- Deploy: Ordner `codebeispiel` auf Payara 6, JDBC-Realm `jwebak_tag04_securityRealm`. Konten `user`/`user123`, `manager`/`manager123`, `admin`/`admin123`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`RoleGate.allows` ist wahr, wenn Fläche und Rolle beide `admin`, `manager` oder `user` sind und denselben Namen haben.

## Stufe 2

`admin` darf zusätzlich `manager` und `user`. `manager` bleibt auf `manager`, `user` auf `user`.

## Abnahme

- Stufe 1: `user`/`user` ja, `admin`/`user` nein, `guest` nein.
- Stufe 2: `admin` öffnet `manager`, `manager` öffnet `admin` nicht.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
