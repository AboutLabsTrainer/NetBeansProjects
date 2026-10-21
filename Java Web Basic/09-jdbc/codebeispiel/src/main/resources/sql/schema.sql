-- Schema helper (optional if /initdb is used)
-- DB: ibb-db, user: ibb-user, table: jwebb09_products
CREATE TABLE IF NOT EXISTS jwebb09_products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);
