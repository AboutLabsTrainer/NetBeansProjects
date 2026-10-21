-- MySQL schema for Tag8 (@ManyToOne Demo)
USE `ibb-db`;

CREATE TABLE IF NOT EXISTS jwebak06_authors (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS jwebak06_books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(160) NOT NULL,
  author_id BIGINT NOT NULL,
  CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES jwebak06_authors(id)
);
