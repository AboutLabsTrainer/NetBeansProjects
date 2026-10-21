USE `ibb-db`;

CREATE TABLE IF NOT EXISTS jwebak03_users (
  username VARCHAR(255) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS jwebak03_user_roles (
  username VARCHAR(255) NOT NULL,
  role_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (username, role_name),
  FOREIGN KEY (username) REFERENCES jwebak03_users(username) ON DELETE CASCADE
);

-- SHA-256 hashes:
-- admin123   => 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9
-- manager123 => 866485796cfa8d7c0cf7111640205b83076433547577511d81f8030ae99ecea5
-- user123    => e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446

INSERT INTO jwebak03_users (username, password) VALUES
('admin',   '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9'),
('manager', '866485796cfa8d7c0cf7111640205b83076433547577511d81f8030ae99ecea5'),
('user',    'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446')
ON DUPLICATE KEY UPDATE password=VALUES(password);

INSERT INTO jwebak03_user_roles (username, role_name) VALUES
('admin', 'admin'),
('admin', 'manager'),
('admin', 'user'),
('manager', 'manager'),
('manager', 'user'),
('user', 'user')
ON DUPLICATE KEY UPDATE role_name=VALUES(role_name);
