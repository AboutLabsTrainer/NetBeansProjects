-- Schema & seed for 03-auth-db (DB auth demo)
CREATE DATABASE IF NOT EXISTS jwebak_tag03_auth CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE jwebak_tag03_auth;

CREATE TABLE IF NOT EXISTS jwebak03_customer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Password 'test123' hashed with SHA-256
INSERT INTO jwebak03_customer (email, password)
VALUES ('test@example.com', 'ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae')
ON DUPLICATE KEY UPDATE email=VALUES(email);
