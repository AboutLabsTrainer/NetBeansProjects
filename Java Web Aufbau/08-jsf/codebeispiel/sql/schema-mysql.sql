-- MySQL schema for Tag10-JSF-PrimeFaces (JSF Application)
-- Database: ibb-db, user: ibb-user
USE `ibb-db`;

CREATE TABLE IF NOT EXISTS jwebak08_products (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  description VARCHAR(1000),
  price DECIMAL(13,2) NOT NULL,
  stock INT NOT NULL
);

-- Insert mock product data
INSERT INTO jwebak08_products (name, description, price, stock) VALUES
('Laptop Pro 15"', 'High-performance laptop with 16GB RAM, 512GB SSD, Intel i7 processor', 1299.99, 25),
('Wireless Mouse', 'Ergonomic wireless mouse with 2.4GHz connection, battery life up to 12 months', 29.99, 150),
('Mechanical Keyboard', 'RGB backlit mechanical keyboard with Cherry MX switches', 89.99, 45),
('USB-C Hub', '7-in-1 USB-C hub with HDMI, USB 3.0, SD card reader, and power delivery', 49.99, 80),
('Monitor 27" 4K', '27-inch 4K UHD monitor with HDR support and adjustable stand', 399.99, 30),
('Webcam HD 1080p', 'Full HD webcam with autofocus and built-in microphone', 79.99, 60),
('External SSD 1TB', 'Portable 1TB external SSD with USB 3.2 Gen 2, read speeds up to 1050MB/s', 129.99, 40),
('Noise Cancelling Headphones', 'Wireless over-ear headphones with active noise cancellation, 30h battery', 249.99, 35),
('USB-C Cable 2m', 'Braided USB-C to USB-C cable, supports fast charging and data transfer', 19.99, 200),
('Laptop Stand', 'Aluminum laptop stand with adjustable height and ventilation', 39.99, 55);
