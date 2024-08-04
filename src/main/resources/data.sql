DELETE
FROM forum.posts;
DELETE
FROM forum.topics;
DELETE
FROM forum.sections;
DELETE
FROM forum.user_roles;
DELETE
FROM forum.roles;
DELETE
FROM forum.users;

INSERT INTO forum.users (id, createdAt, email, emailToken, enabled, lastLoginTime, password, removed, secondaryEmail,
                         username)
VALUES (1, '2024-08-01 13:26:41.657118', 'admin@admin.com', '3faeca896e0cb050', true, null,
        '$2a$10$rNQGG7DQcCNYl3Lp/1V/XuOgAPFhNVX3C2Kc3BUFYoGfi.1ENc45i', false, null, 'admin');

INSERT INTO forum.roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO forum.roles (id, name)
VALUES (2, 'USER');
INSERT INTO forum.roles (id, name)
VALUES (3, 'MOD');


INSERT INTO forum.user_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO forum.user_roles (user_id, role_id)
VALUES (1, 2);
INSERT INTO forum.user_roles (user_id, role_id)
VALUES (1, 3);

INSERT INTO forum.sections (id, description, name)
VALUES (1, 'Hello section', 'General');
INSERT INTO forum.sections (id, description, name)
VALUES (2, 'Test', 'Test');


INSERT INTO forum.topics (id, closed, creationDate, lastUpdateDate, title, views, section_id, user_id) VALUES (11, false, '2024-08-04 14:52:36.466258', null, 'Some topic with long title', 0, 1, 1);



INSERT INTO forum.posts (id, content, contentType, creationDate, modificationDate, topic_id, user_id)
VALUES ( 10, '
    The Founders offer the divided regimes on the Alpha side of the wormhole the advantages of alliance with a powerful force, and the threat that the Founders would ally against them.

What makes the Founders so powerful?

The Founders have bred a species of ferocious fighters who are under the founders complete control through use of a material which the fighters (the JemHadar) require to live (Ketracel White). The Founders also bred a species of cloned diplomats (the Vorta) who worship the Founders as gods. Together, these are formidable advantages.

The Founders also do not fight among themselves, so they are not susceptible to being divided against themselves. Even without the power to shapeshift, and thus subvert, confuse and corrupt their enemies, the Founders would be a powerful foe - and with their transforming powers, they are a threat to any Federation-level society (or even a coalition of Federation-level societies).'
       , null, '2024-08-04 14:38:30.604206', null, 11, 1);
INSERT INTO forum.posts (id, content, contentType, creationDate, modificationDate, topic_id, user_id)
VALUES (12, 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit
                            necessitatibus adipisci, ad alias, voluptate pariatur officia
                            repellendus repellat inventore fugit perferendis totam dolor
                            voluptas et corrupti distinctio maxime corporis optio?', null, '2024-08-04 14:46:34.974933',
        null, 11, 1);
