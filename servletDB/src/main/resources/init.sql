--------------------------------------------------------
--  DDL for schema USERS
--------------------------------------------------------
DROP SCHEMA IF EXISTS USERS;
CREATE SCHEMA IF NOT EXISTS USERS;

--------------------------------------------------------
--  DDL for Table user_login
--------------------------------------------------------
DROP TABLE IF EXISTS USERS.user_login;
CREATE TABLE IF NOT EXISTS USERS.user_login (
                                                            id INT NOT NULL AUTO_INCREMENT,
                                                            login VARCHAR(30) NOT NULL,
                                                            password VARCHAR(10) NOT NULL;
                                                    --------------------------------------------------------
--------------------------------------------------------
--  DML for Table user_login
--------------------------------------------------------
INSERT INTO users.user_login(login, password) VALUES(?, ?);