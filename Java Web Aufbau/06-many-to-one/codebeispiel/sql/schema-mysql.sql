-- MySQL schema for Tag8 (@ManyToOne Demo)
CREATE DATABASE IF NOT EXISTS jwebak_tag08_manytoone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE jwebak_tag08_manytoone;

CREATE TABLE IF NOT EXISTS authors (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(160) NOT NULL,
  author_id BIGINT NOT NULL,
  CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id)
);
