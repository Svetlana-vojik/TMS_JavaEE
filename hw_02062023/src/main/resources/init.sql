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
                                                imageName VARCHAR(100) NOT NULL,
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
                                                    imageName  VARCHAR(100) NOT NULL,
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
    INSERT INTO shop.categories(name, imageName) VALUES(?, ?);

    INSERT INTO shop.categories(name, imageName) VALUES('Капкейки', 'images/categories/cupcakes.png');
    INSERT INTO shop.categories(name, imageName) VALUES('Торты', 'images/categories/cakes.png');
    INSERT INTO shop.categories(name, imageName) VALUES('Пирожные', 'images/categories/pastries.png');
    INSERT INTO shop.categories(name, imageName) VALUES('Круассаны', 'images/categories/croissants.png');
    INSERT INTO shop.categories(name, imageName) VALUES('Киши', 'images/categories/quiche.png');

    --------------------------------------------------------
--  DML for Table products
--------------------------------------------------------
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES(?, ?, ?, ?, ?);

    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Капкейки', 'Лимонный капкейк', 5, 1,'images/products/lemon.png');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Капкейки', 'Шоколадный капкейк', 7, 1,'images/products/chocolate.png');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Капкейки', 'Клубничный капкейк', 10, 1,'images/products/strawberries.png');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Капкейки', 'Сырный капкейк', 8, 1,'images/products/cheese.jpg');

    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Торты', 'Наполеон', 5, 2,'images/products/napoleon.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Торты', 'Красный бархат', 7, 2,'images/products/red.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Торты', 'Медовик', 10, 2,'images/products/medovik.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Торты', 'Тирамису', 8, 2,'images/products/tiramisu.jpg');

    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Пирожные', 'Картошка', 5, 3,'images/products/kartoshka.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Пирожные', 'Классический эклер', 7, 3,'images/products/ekler.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Пирожные', 'Корзиночка', 10, 3,'images/products/korzinochka.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Пирожные', 'Павлова', 8, 3,'images/products/pavlova.jpg');

    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Круассаны', 'Шоколадный круассан', 10, 4,'images/products/croissant_chocolate.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Круассаны', 'Сырный круассан', 10, 4,'images/products/chessecroissant.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Круассаны', 'Круассан с беконом', 15, 4,'images/products/bacon.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Круассаны', 'Круассан с яблочно-грушевой начинкой', 14, 4,'images/products/apple.jpg');

    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Киши', 'Киш с броколли', 5, 5,'images/products/brokoli.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Киши', 'Киш с сыром', 7, 5,'images/products/cheesekish.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Киши', 'Киш с семгой и броколли', 10, 5,'images/products/kish-s-semgoi-i-brokkoli.jpg');
    INSERT INTO shop.products(name, description, price, category_id, imageName) VALUES('Киши', 'Киш Лорен с курицей', 8, 5,'images/products/kish-loren-s-kuricei.jpg');