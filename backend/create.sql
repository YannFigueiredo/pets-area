create table tb_owner (id int8 generated by default as identity, age int4, email varchar(255), first_name varchar(255), gender int4, last_name varchar(255), password varchar(255), photo varchar(255), primary key (id));
create table tb_owner_role (owner_id int8 not null, role_id int8 not null, primary key (owner_id, role_id));
create table tb_pet (id int8 generated by default as identity, age int4, description varchar(255), gender int4, name varchar(255), photo varchar(255), type int4, owner_id int8, primary key (id));
create table tb_role (id int8 generated by default as identity, authority varchar(255), primary key (id));
alter table if exists tb_owner add constraint UK_jwl9bb3n2hm8yvjg40xcsbg9g unique (email);
alter table if exists tb_owner_role add constraint FK787cewkluu632j1c0l8pux7lf foreign key (role_id) references tb_role;
alter table if exists tb_owner_role add constraint FKf136i2h5u6cjd9rdx4bnp0t4a foreign key (owner_id) references tb_owner;
alter table if exists tb_pet add constraint FKd1eanviq8d0uodmibjm4jndw9 foreign key (owner_id) references tb_owner;
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
INSERT INTO tb_pet(name, age, type, description, gender, owner_id) VALUES ('Pietra', 10, 0, 'Uma pitbull velhinha.', 1, 3);
CREATE EXTENSION unaccent;