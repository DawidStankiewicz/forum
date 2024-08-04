
DELETE FROM forum.posts;
DELETE FROM forum.topics;
DELETE FROM forum.sections;
DELETE FROM forum.user_roles;
DELETE FROM forum.roles;
DELETE FROM forum.users;

INSERT INTO forum.users (id, createdAt, email, emailToken, enabled, lastLoginTime, password, removed, secondaryEmail, username) VALUES (1, '2024-08-01 13:26:41.657118', 'admin@admin.com', '3faeca896e0cb050', true, null, '$2a$10$rNQGG7DQcCNYl3Lp/1V/XuOgAPFhNVX3C2Kc3BUFYoGfi.1ENc45i', false, null, 'admin');

INSERT INTO forum.roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO forum.roles (id, name) VALUES (2, 'USER');
INSERT INTO forum.roles (id, name) VALUES (3, 'MOD');


INSERT INTO forum.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO forum.user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO forum.user_roles (user_id, role_id) VALUES (1, 3);

INSERT INTO forum.sections (id, description, name) VALUES (1, 'Hello section', 'General');
INSERT INTO forum.sections (id, description, name) VALUES (2, 'Test', 'Test');