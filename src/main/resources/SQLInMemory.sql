DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
identityId LONG VARCHAR(50) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
created DATETIME NOT NULL
);

DROP TABLE IF EXISTS balance;

CREATE TABLE balance (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
identityId VARCHAR(50) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
currency VARCHAR(3) NOT NULL,
);

INSERT INTO balance (identityId,amount,currency) VALUES ('30366514',2453.43,'ARS');
INSERT INTO balance (identityId,amount,currency) VALUES ('12121212',1234.43,'ARS');
INSERT INTO balance (identityId,amount,currency) VALUES ('21212121',4323.43,'ARS');

DROP TABLE IF EXISTS users;

CREATE TABLE users (
id LONG PRIMARY KEY AUTO_INCREMENT  NOT NULL,
userId VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
identityId VARCHAR(50) NOT NULL,
name VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL
);

INSERT INTO users (userId,password,identityId,name,lastName) VALUES ('santi83','2846','30366514','Santiago','Kiryluk');
INSERT INTO users (userId,password,identityId,name,lastName) VALUES ('albert12','1212','12121212','Albert','Einstein');
INSERT INTO users (userId,password,identityId,name,lastName) VALUES ('leo21','2121','21212121','Leonardo','DaVinci');