CREATE DATABASE db_retail;

CREATE TABLE category(
category_id INT PRIMARY KEY AUTO_INCREMENT,
category_name VARCHAR(30)
);

CREATE TABLE supplier(
supplier_id INT PRIMARY KEY AUTO_INCREMENT,
supplier_name VARCHAR(40),
supplier_email VARCHAR(40) UNIQUE CHECK(supplier_email LIKE '%@%')
);

CREATE TABLE product(
product_id INT PRIMARY KEY AUTO_INCREMENT,
product_name VARCHAR(30),
product_price DECIMAL CHECK(product_price > 0),
category_id INT,
supplier_id INT,
FOREIGN KEY(category_id) REFERENCES category(category_id),
FOREIGN KEY(supplier_id) REFERENCES supplier(supplier_id)
);

CREATE TABLE inventory(
inventory_id INT PRIMARY KEY AUTO_INCREMENT,
inventory_quantity INT CHECK(inventory_quantity >= 0),
inventory_last_updated DATE,
product_id INT,
FOREIGN KEY(product_id) REFERENCES product(product_id)
);

CREATE TABLE orders(
orders_id INT PRIMARY KEY AUTO_INCREMENT,
orders_date DATE,
orders_quantity INT CHECK(orders_quantity >= 0),
orders_total_price DECIMAL CHECK(orders_total_price > 0),
product_id INT,
FOREIGN KEY(product_id) REFERENCES product(product_id)
);

DELIMITER $$
	CREATE TRIGGER calculate_price 
	BEFORE INSERT ON orders 
	FOR EACH ROW
	BEGIN
		DECLARE p_price DECIMAL;
		
		SELECT product_price INTO p_price
		FROM product
		WHERE product_id = NEW.product_id;
		
		SET NEW.orders_total_price = NEW.orders_quantity * p_price;		
	END$$
DELIMITER ;