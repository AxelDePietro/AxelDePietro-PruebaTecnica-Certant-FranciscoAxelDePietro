INSERT INTO role_entity (id_role, role_name)
SELECT 1, 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM role_entity WHERE id_role = 1);

INSERT INTO role_entity (id_role, role_name)
SELECT 2, 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM role_entity WHERE id_role = 2);



INSERT INTO user_entity (id_user, username, password, enabled, free_pass)
SELECT 1, 'admin', '$2a$10$lDNGkstCMvfid1Grjf0NlOx8V1HRAQNKXJvwXeGqHlu1UQ3KrDSXe', true, false
    WHERE NOT EXISTS (SELECT 1 FROM user_entity WHERE id_user = 1);

INSERT INTO user_entity (id_user, username, password, enabled, free_pass)
SELECT 2, 'usuario', '$2a$10$lDNGkstCMvfid1Grjf0NlOx8V1HRAQNKXJvwXeGqHlu1UQ3KrDSXe', true, false
    WHERE NOT EXISTS (SELECT 1 FROM user_entity WHERE id_user = 2);

INSERT INTO user_entity (id_user, username, password, enabled, free_pass)
SELECT 3, 'usuario2', '$2a$10$lDNGkstCMvfid1Grjf0NlOx8V1HRAQNKXJvwXeGqHlu1UQ3KrDSXe', true, false
    WHERE NOT EXISTS (SELECT 1 FROM user_entity WHERE id_user = 3);
    
    INSERT INTO users_roles (user_id, role_id)
SELECT 1, 1
    WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE user_id = 1 AND role_id = 1);

INSERT INTO users_roles (user_id, role_id)
SELECT 2, 2
    WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE user_id = 2 AND role_id = 2);

INSERT INTO users_roles (user_id, role_id)
SELECT 3, 2
    WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE user_id = 3 AND role_id = 2);