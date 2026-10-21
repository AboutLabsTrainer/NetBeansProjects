package de.ibb.jdbc;

public final class PersonenSql {

    private PersonenSql() {
    }

    public static String createTableSql() {
        return """
            CREATE TABLE jae06_personen (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                `alter` INT,
                email VARCHAR(100)
            )
            """;
    }

    public static String insertSql() {
        return "INSERT INTO jae06_personen (name, `alter`, email) VALUES (?, ?, ?)";
    }

    public static String selectAlleSql() {
        return "SELECT id, name, `alter`, email FROM jae06_personen";
    }
}
