use glam_db;


CREATE TABLE IF NOT EXISTS user(
	id_user INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS product(
	id_product int PRIMARY KEY NOT NULL,
    title_product VARCHAR(255),
    image_url VARCHAR(255),
    price double,
    stock int
);




CREATE TABLE IF NOT EXISTS invoice(
    id_invoice int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    date date,
    total double
);


CREATE TABLE IF NOT EXISTS invoice_item(
    id_invoice int,
    id_product int,
    quantity int,
    FOREIGN KEY (id_invoice) REFERENCES invoice(id_invoice),
    FOREIGN KEY (id_product) REFERENCES product(id_product)
);