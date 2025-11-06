

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



INSERT INTO event_entity (id_event, event_name, event_date_time, event_type)
SELECT 1, 'La Tragedia de Hamlet', '2025-11-20T20:00:00', 'THEATER'
    WHERE NOT EXISTS (SELECT 1 FROM event_entity WHERE id_event = 1);

INSERT INTO event_entity (id_event, event_name, event_date_time, event_type)
SELECT 2, 'Recital Soda Stereo', '2025-12-05T22:00:00', 'CONCERT'
    WHERE NOT EXISTS (SELECT 1 FROM event_entity WHERE id_event = 2);



INSERT INTO seat_capacity_entity (id_seat_capacity, seat_type, capacity, event_id)
SELECT 1, 'OBRA_ENTRADA_GENERAL', 100, 1
    WHERE NOT EXISTS (SELECT 1 FROM seat_capacity_entity WHERE id_seat_capacity = 1);

INSERT INTO seat_capacity_entity (id_seat_capacity, seat_type, capacity, event_id)
SELECT 2, 'OBRA_ENTRADA_VIP', 50, 1
    WHERE NOT EXISTS (SELECT 1 FROM seat_capacity_entity WHERE id_seat_capacity = 2);



INSERT INTO seat_capacity_entity (id_seat_capacity, seat_type, capacity, event_id)
SELECT 3, 'RECITAL_CAMPO', 300, 2
    WHERE NOT EXISTS (SELECT 1 FROM seat_capacity_entity WHERE id_seat_capacity = 3);

INSERT INTO seat_capacity_entity (id_seat_capacity, seat_type, capacity, event_id)
SELECT 4, 'RECITAL_PLATEA', 150, 2
    WHERE NOT EXISTS (SELECT 1 FROM seat_capacity_entity WHERE id_seat_capacity = 4);

INSERT INTO seat_capacity_entity (id_seat_capacity, seat_type, capacity, event_id)
SELECT 5, 'RECITAL_CAMPO', 100, 2
    WHERE NOT EXISTS (SELECT 1 FROM booking_entity WHERE id_booking = 3);



INSERT INTO booking_entity (id_booking, booking_price, seat_type, fk_event, fk_user)
SELECT 1, 2000, 'OBRA_ENTRADA_GENERAL', 1, 2
    WHERE NOT EXISTS (SELECT 1 FROM booking_entity WHERE id_booking = 1);

INSERT INTO booking_entity (id_booking, booking_price, seat_type, fk_event, fk_user)
SELECT 2, 5000, 'OBRA_ENTRADA_VIP', 1, 2
    WHERE NOT EXISTS (SELECT 1 FROM booking_entity WHERE id_booking = 2);
