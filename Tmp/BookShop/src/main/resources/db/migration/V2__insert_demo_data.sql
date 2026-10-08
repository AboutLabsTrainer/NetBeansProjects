INSERT INTO authors (name) VALUES ('Anna Beispiel');
INSERT INTO authors (name) VALUES ('Max Mustermann');

INSERT INTO books (title, isbn, author_id)
SELECT 'Spring Boot lernen', '978-3-86680-192-9', id
FROM authors
WHERE name = 'Anna Beispiel';

INSERT INTO books (title, isbn, author_id)
SELECT 'Modernes Java', '978-0-13-468599-1', id
FROM authors
WHERE name = 'Max Mustermann';
