-- USERNEW example
-- INSERT INTO USERNEW (username, password, roles) VALUES
-- INSERT INTO USERS (username, password, roles) VALUES
--     ('admin', '{noop}admin', ARRAY['User', 'Admin']),
--     ('user', '{noop}password123', ARRAY['User']);
----------------
INSERT INTO USERS (username, password) VALUES
    ('admin', '{noop}admin'),
    ('user', '{noop}password123');
INSERT INTO user_roles (userdb_id, roles) VALUES
    (1, 'User'),
    (1, 'Admin'),
    (2, 'User');

-- USER examples (old user type)
-- INSERT INTO users (username, password, role) VALUES
--     ('admin', '{noop}admin', 'ROLE_ADMIN'),
--     ('user', '{noop}password123', 'ROLE_USER');

-- APPLOGS inserter example
INSERT INTO APPLOGS (timestamp, message, level) VALUES
    ('2026-09-24 13:12:13.033', 'inserted by Database', 'WARN');

-- password123 => {bcrypt}$2a$10$e0MszvFw6l8zM2w.yWqYI.9tLpbyK7r97YshXg9bNfE3UvL8Tve4S


------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------

-- -- use dataloader;

-- -- creating a table ... example
-- CREATE TABLE upsyword (
--                           id INT AUTO_INCREMENT PRIMARY KEY,
--                           name VARCHAR(50) NOT NULL,
--                           pword VARCHAR(250) NOT NULL
-- );
--
-- INSERT INTO upsyword (name, pword) VALUES ('Alice Smith', 'alice@example.com');
-- INSERT INTO upsyword (name, pword) VALUES ('Bob Jones', 'bob@example.com');
