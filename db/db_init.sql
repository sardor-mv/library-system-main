
INSERT INTO account(account_id, username, password, email, active_status, deleted_status, immutable_status, creator, creation_date, updater, update_date)
VALUES(1, 'system', '', 'test@seoultech.ac.kr', TRUE, FALSE, TRUE, 1, NOW(), 1, NOW());

/*Admin user*/
INSERT INTO account(account_id, username, password, email, first_name, last_name, active_status, deleted_status, immutable_status, creator, creation_date, updater, update_date)
VALUES(2, 'admin', '$2a$10$Mz2N/DpFqDqZS85TSz4Lzussqkq3/siP6uulpcIfVH4VJuRLMjRtu', 'admin@gmail.com', 'Admin', 'Admin', TRUE, FALSE, TRUE, 1, NOW(), 1, NOW());

/*General user - member*/
INSERT INTO account(account_id, username, password, email, first_name, last_name, active_status, deleted_status, immutable_status, creator, creation_date, updater, update_date)
VALUES(3, 'test', '$2a$10$Mz2N/DpFqDqZS85TSz4Lzussqkq3/siP6uulpcIfVH4VJuRLMjRtu', 'test@naver.com', 'Test', 'Test', TRUE, FALSE, TRUE, 1, NOW(), 1, NOW());

/*Roles for Spring Security*/
INSERT INTO role(role_id, role_name, active_status, creation_date, creator, update_date, updater)
VALUES(1, 'ADMIN', TRUE, NOW(), 1, NOW(), 1);

INSERT INTO role(role_id, role_name, active_status, creation_date, creator, update_date, updater)
VALUES(2, 'USER', TRUE, NOW(), 1, NOW(), 1);

/*Give roles to accounts(users)*/
/*Give both 'ADMIN' and 'USER' roles to 'admin' user*/
INSERT INTO account_role(account_id, role_id)
VALUES(2, 1);
INSERT INTO account_role(account_id, role_id)
VALUES(2, 2);

/*Give 'USER' role to 'test' user*/
INSERT INTO account_role(account_id, role_id)
VALUES(3, 2);
