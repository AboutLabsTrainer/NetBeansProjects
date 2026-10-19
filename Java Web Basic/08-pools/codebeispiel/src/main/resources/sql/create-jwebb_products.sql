-- Tabelle jwebb_products für Tag9-ConnectionPools (JNDI jdbc/jwebb_tag09_pools)
-- Datenbank: jwebb_tag09_pools (MariaDB/MySQL), Nutzer jwebb_tag09_pools
-- Ausführen in phpMyAdmin oder MySQL-Client

CREATE TABLE IF NOT EXISTS jwebb_products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000),
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Beispielprodukte
INSERT INTO jwebb_products (name, description, price, stock) VALUES
  ('Notebook', 'Leistungsstarker Laptop für Büro und Studium', 599.99, 12),
  ('Wireless Maus', 'Ergonomische Funkmaus mit USB-Empfänger', 29.99, 45),
  ('USB-Stick 32GB', 'Schneller USB-3.0 Speicherstick', 14.99, 80),
  ('Tastatur', 'Mechanische Tastatur mit RGB-Beleuchtung', 89.99, 8),
  ('Monitor 24 Zoll', 'Full-HD Monitor mit schmalem Rahmen', 199.99, 15);
