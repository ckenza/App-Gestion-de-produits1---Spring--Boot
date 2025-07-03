use glam_db;

CREATE TABLE IF NOT EXISTS `user`(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `product`(
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    stock int
);

CREATE TABLE IF NOT EXISTS `invoice`(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_user INT NOT NULL,
    date date,
    total DECIMAL(10,2),
    FOREIGN KEY (id_user) REFERENCES `user`(id)
);

CREATE TABLE IF NOT EXISTS `invoice_item`(
    id_invoice int,
    id_product int,
    quantity int,
    unit_price DECIMAL(10,2),
    PRIMARY KEY (id_invoice, id_product),
    FOREIGN KEY (id_invoice) REFERENCES invoice(id),
    FOREIGN KEY (id_product) REFERENCES product(id)
);