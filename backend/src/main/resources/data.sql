INSERT INTO tb_owner (first_name, last_name, email, password, age, gender) VALUES ('Yann', 'Figueiredo', 'yann.fabricio@hotmail.com', 'senha123', 27, 0);
INSERT INTO tb_owner (first_name, last_name, email, password, age, gender) VALUES ('Max', 'Figueiredo', 'max18@hotmail.com', 'senha456', 4, 0);
INSERT INTO tb_owner (first_name, last_name, email, password, age, gender) VALUES ('Son', 'Goku', 'comida@hotmail.com', 'refeicao', 40, 0);

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_owner_role(owner_id, role_id) VALUES (1, 1);
INSERT INTO tb_owner_role(owner_id, role_id) VALUES (1, 2);
INSERT INTO tb_owner_role(owner_id, role_id) VALUES (2, 1);
INSERT INTO tb_owner_role(owner_id, role_id) VALUES (3, 1);

INSERT INTO tb_pet(name, age, type, description, gender, owner_id) VALUES ('Pandora', 0, 0, 'Uma labrador muito fofa.', 1, 1);
INSERT INTO tb_pet(name, age, type, description, gender, owner_id) VALUES ('Barrento', 5, 5, 'Uma cágado muito fujão.', 0, 1);
INSERT INTO tb_pet(name, age, type, description, gender, owner_id) VALUES ('Filó', 3, 1, 'Uma gata muito manhosa.', 1, 2);
INSERT INTO tb_pet(name, age, type, description, gender, owner_id) VALUES ('Pietra', 10, 0, 'Uma pitbull velhinha.', 1, 2);