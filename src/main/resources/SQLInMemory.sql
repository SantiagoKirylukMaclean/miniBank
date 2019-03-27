DROP TABLE IF EXISTS users;

CREATE TABLE users (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
name VARCHAR(256) NOT NULL,
username VARCHAR(256) NOT NULL,
email VARCHAR(256) NOT NULL,
password VARCHAR(256) NOT NULL
);

INSERT INTO users (name,username,email,password) VALUES ('Santiago','elkolo','colo@colo.com','$2a$10$eBbnPVpD99yrk9oTz/tkjeAo5CXu9jKWBQLOSQB52s7JktAeUYi7a');
INSERT INTO users (name,username,email,password) VALUES ('alberto','alber','alber@alber.com','$2a$10$ICt5yv3DV21GMSY6g/fhTepVNahENudKQPi9tEpGmTmAKdA687mMO');
INSERT INTO users (name,username,email,password) VALUES ('leo','leo','leo@leo.leo','$2a$10$TdFeoLJAlnfrqGcM9B5mSezpyDCoo/6bPiCQDee/h3ZrUgYz2MUhm');

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
name VARCHAR(50) NOT NULL
);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_PM');

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
user_id LONG  NOT NULL,
role_id LONG NOT NULL
);

INSERT INTO roles (user_id, role_id) VALUES (1,1);
INSERT INTO roles (user_id, role_id) VALUES (2,1);
INSERT INTO roles (user_id, role_id) VALUES (2,3);
INSERT INTO roles (user_id, role_id) VALUES (3,2);


DROP TABLE IF EXISTS balance;

CREATE TABLE balance (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
identityId VARCHAR(50) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
currency VARCHAR(3) NOT NULL
);

INSERT INTO balance (identityId,amount,currency) VALUES ('30366514',2453.43,'ARS');
INSERT INTO balance (identityId,amount,currency) VALUES ('12121212',1234.43,'ARS');
INSERT INTO balance (identityId,amount,currency) VALUES ('21212121',4323.43,'ARS');

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
identityId LONG VARCHAR(50) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
created DATETIME NOT NULL
);