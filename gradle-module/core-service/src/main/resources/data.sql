


INSERT INTO roles (id, role_code) VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_code) VALUES (2, 'USER');


INSERT INTO users (id, name, password, email, role_id) VALUES (1, 'Alice', 'password123', 'alice@example.com', 1);
INSERT INTO users (id, name, password, email, role_id) VALUES (2, 'Bob', 'password456', 'bob@example.com', 2);