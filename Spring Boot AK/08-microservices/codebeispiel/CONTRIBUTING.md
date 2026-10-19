# Mitwirken an der Microservices-Demo

Danke, dass du zu diesem Projekt beitragen möchtest! 🎉

## 🚀 Schnellstart für Contributors

### Prerequisites
- Java 21
- Maven 3.8+
- Docker (optional)
- Git

### Setup
```bash
git clone https://github.com/DEIN-USERNAME/microservices-demos.git
cd microservices-demos
mvn clean install
```

## 🔧 Entwicklungs-Workflow

### 1. Fork & Clone
```bash
# Forke das Repository auf GitHub
git clone https://github.com/DEIN-USERNAME/microservices-demos.git
cd microservices-demos
```

### 2. Branch erstellen
```bash
git checkout -b feature/dein-feature-name
```

### 3. Code-Änderungen
- Folge den bestehenden Code-Styles
- Schreibe sauberen, kommentierten Code
- Teste deine Änderungen lokal

### 4. Testen
```bash
# Alle Tests
mvn test

# Einzelner Service
cd user-service
mvn test
```

### 5. Commit & Push
```bash
git add .
git commit -m "feat: beschreibung deiner änderung"
git push origin feature/dein-feature-name
```

### 6. Pull Request erstellen
- Gehe zu GitHub
- Erstelle Pull Request von deinem Branch zu `main`
- Beschreibe deine Änderungen

## 📝 Commit-Konventionen

Wir nutzen [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: Neues Feature
fix: Bugfix
docs: Dokumentations-Änderung
style: Code-Formatierung
refactor: Code-Refactoring
test: Test-Änderungen
chore: Build-/Tool-Änderungen
```

Beispiele:
```
feat: add product service
fix: resolve eureka connection timeout
docs: update README with new endpoints
```

## 🎯 Code-Style

- **Java**: Folge Standard-Java-Konventionen
- **Einrückung**: 4 Spaces
- **Line Length**: Max 120 Zeichen
- **Naming**: 
  - Klassen: PascalCase (UserService)
  - Methoden: camelCase (getUserById)
  - Konstanten: UPPER_SNAKE_CASE (MAX_RETRIES)

## 🧪 Testing

- Jede neue Feature sollte Tests haben
- Nutze JUnit 5 & Mockito
- Test Coverage > 70%

```java
@Test
public void testGetUserById_Success() {
    // Arrange
    User mockUser = new User(1L, "test", "test@example.com");
    when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
    
    // Act
    User result = userService.getUserById(1L);
    
    // Assert
    assertNotNull(result);
    assertEquals("test", result.getUsername());
}
```

## 📦 Neue Services hinzufügen

1. Erstelle neuen Ordner im Root
2. Erstelle `pom.xml` mit Parent-Referenz
3. Füge Modul zum Parent-POM hinzu
4. Registriere Service bei Eureka
5. Dokumentiere im README

## 🐛 Bug Reports

Nutze GitHub Issues mit Template:

**Titel:** Kurze Beschreibung  
**Beschreibung:**
- Was ist passiert?
- Was sollte passieren?
- Schritte zum Reproduzieren
- Log-Ausgabe
- Environment (OS, Java Version)

## 💡 Feature Requests

Nutze GitHub Issues:

**Titel:** Feature: Deine Idee  
**Beschreibung:**
- Was soll das Feature machen?
- Warum ist es nützlich?
- Beispiel-Code (optional)

## ❓ Fragen?

- Öffne ein GitHub Issue
- Oder schreib eine E-Mail

## 🙏 Danke!

Jeder Beitrag hilft - egal ob klein oder groß! 

**Happy Coding!** 🚀
