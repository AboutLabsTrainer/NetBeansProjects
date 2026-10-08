-- Schema helper (optional if /initdb is used)
-- DB: jwebb_tag10_jdbc, user: jwebb_tag10_jdbc, tables prefix: jwebb_
CREATE TABLE IF NOT EXISTS jwebb_products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);
