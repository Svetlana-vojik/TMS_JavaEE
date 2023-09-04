--------------------------------------------------------
--  DDL for schema shop
--------------------------------------------------------
DROP SCHEMA IF EXISTS shop;
CREATE SCHEMA IF NOT EXISTS shop;

--------------------------------------------------------
--  DDL for Table users
--------------------------------------------------------
DROP TABLE IF EXISTS shop.users;
CREATE TABLE IF NOT EXISTS shop.users (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                email VARCHAR(60) NOT NULL,
                                                password VARCHAR(60) NOT NULL,
                                                name VARCHAR(30) NOT NULL,
                                                surname VARCHAR(60) NOT NULL,
                                                balance DECIMAL(10, 2) NOT NULL,
                                                PRIMARY KEY (`id`);
--------------------------------------------------------
--  DDL for Table categories
--------------------------------------------------------
    DROP TABLE IF EXISTS shop.categories;
    CREATE TABLE IF NOT EXISTS shop.categories (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                name VARCHAR(30) NOT NULL,
                                                imagePath VARCHAR(100) NOT NULL,
                                                PRIMARY KEY (`id`);
    --------------------------------------------------------
--  DDL for Table products
--------------------------------------------------------
    DROP TABLE IF EXISTS shop.products;
    CREATE TABLE IF NOT EXISTS shop.products (
                                                    id INT NOT NULL AUTO_INCREMENT,
                                                    name VARCHAR(30) NOT NULL,
                                                    description VARCHAR(100) NOT NULL,
                                                    price INT NOT NULL,
                                                    category_id INT  NOT NULL,
                                                    imagePath  VARCHAR(100) NOT NULL,
                                                    PRIMARY KEY (`id`);
    --------------------------------------------------------

--------------------------------------------------------
--  DML for Table users
--------------------------------------------------------
    INSERT INTO shop.users(email, password, name, surname, balanca) VALUES(?, ?, ?, ?, ?);

    INSERT INTO shop.users(email, password, name, surname, balance) VALUES ('user1@mail.ru', '1111', 'Sveta', 'Kot', 10.00);
    INSERT INTO shop.users(email, password, name, surname, balance) VALUES ('user2@mail.ru', '2222', 'Dima', 'Black', 2.50);
    INSERT INTO shop.users(email, password, name, surname, balance) VALUES ('user3@mail.ru', '3333', 'Ira', 'Smith', 0.00);
    INSERT INTO shop.users(email, password, name, surname, balance) VALUES ('user4@mail.ru', '4444', 'Lena', 'White', 13.15);
--------------------------------------------------------
--  DML for Table categories
--------------------------------------------------------
    INSERT INTO shop.categories(name, imagePath) VALUES(?, ?);

    INSERT INTO shop.categories(name, imagePath) VALUES('Капкейки', 'images/categories/cupcakes.png');
    INSERT INTO shop.categories(name, imagePath) VALUES('Торты', 'images/categories/cakes.png');
    INSERT INTO shop.categories(name, imagePath) VALUES('Пирожные', 'images/categories/pastries.png');
    INSERT INTO shop.categories(name, imagePath) VALUES('Круассаны', 'images/categories/croissants.png');
    INSERT INTO shop.categories(name, imagePath) VALUES('Киши', 'images/categories/quiche.png');

    --------------------------------------------------------
--  DML for Table products
--------------------------------------------------------
    INSERT INTO shop.products(name, description, price, category_id, imagePath) VALUES(?, ?, ?, ?, ?);

    INSERT INTO shop.products(name, description, price, category_id, imagePath) VALUES('Капкейки', 'Лимонный капкейк', 5, 1,'images/products/lemon.png');
