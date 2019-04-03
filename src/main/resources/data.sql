DROP TABLE IF EXISTS users;

CREATE TABLE users (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
name VARCHAR(256) NOT NULL,
username VARCHAR(256) NOT NULL,
email VARCHAR(256) NOT NULL,
password VARCHAR(256) NOT NULL
);

INSERT INTO users (name,username,email,password) VALUES ('Santiago','santiago','colo@colo.com','$2a$10$QStG9IjkEkmqA7SBR/puN.rn.odQmLcWx94WwtYOcIZHM8xAlVtDG');
INSERT INTO users (name,username,email,password) VALUES ('Joe','joe','colo@colo.com','$2a$10$MKg2jPCfIDH3CSLXhP37ROsO4qauG6YHeHyEgzZwzwbqwlrLmQioW');

	
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
name VARCHAR(50) NOT NULL
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles (
user_id LONG  NOT NULL,
role_id LONG NOT NULL
);

INSERT INTO user_roles (user_id, role_id) VALUES (1,1);
INSERT INTO user_roles (user_id, role_id) VALUES (1,2);
INSERT INTO user_roles (user_id, role_id) VALUES (1,3);
INSERT INTO user_roles (user_id, role_id) VALUES (2,1);



DROP TABLE IF EXISTS balance;

CREATE TABLE balance (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
username VARCHAR(50) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
currency VARCHAR(3) NOT NULL
);

INSERT INTO balance (username,amount,currency) VALUES ('santiago',5000.43,'ARS');
INSERT INTO balance (username,amount,currency) VALUES ('joe',3000.43,'ARS');
