INSERT INTO category(category_name) VALUES("Electronics");
INSERT INTO category(category_name) VALUES("Clothing");
INSERT INTO category(category_name) VALUES("Groceries");
INSERT INTO category(category_name) VALUES("Furniture");
INSERT INTO category(category_name) VALUES("Sports Equipment");

INSERT INTO supplier(supplier_name, supplier_email) VALUES("TechSource Ltd.", "sales@techsource.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("FreshFoods Co.", "contact@freshfoods.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("HomeComfort Furnish", "info@homecomfort.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("ActiveLife Sports", "support@activelife.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("TrendyWear", "hello@trendywear.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("GreenEarth", "care@greenearth.com");
INSERT INTO supplier(supplier_name, supplier_email) VALUES("GlobalTech", "service@grlobaltech.com");


INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("IPhone 15 Pro Max", 699.99, 1, 1);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Asus Zephyrus G16", 1299.50, 1, 1);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Running Shoes", 89.99, 5, 4);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Office Chair", 159, 4, 3);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("T-Shirt", 19.99, 2, 5);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Organic Apples", 4.50, 3, 2);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Dining Table", 450.00, 4, 3);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Basketball", 29.99, 5, 4);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Jeans", 49.99, 2, 5);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Sony XM4", 149, 1, 1);
INSERT INTO product(product_name, product_price, category_id) VALUES("Hoodie", 20.50, 2);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Gaming Mouse", 49.99, 1, 5);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Smart Desk Lamp", 79.99, 4, 1);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Cotton Tote Bag", 12.99, 2, 2);
INSERT INTO product(product_name, product_price, category_id, supplier_id) VALUES("Protein Bar Pack", 15.50, 3, 4);

INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(25, '2025-09-01', 4);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(10, '2025-08-25', 5);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(50, '2025-09-02', 6);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(8, '2025-09-03', 7);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(100, '2025-08-30', 8);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(200, '2025-09-04', 9);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(5, '2025-09-01', 10);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(30, '2025-08-29', 11);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(60, '2025-09-03', 12);
INSERT INTO inventory(inventory_quantity, inventory_last_updated, product_id) VALUES(40, '2025-09-04', 13);

INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-02', 2, 4);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-03', 5, 9);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-04', 1, 6);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-08-28', 10, 8);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-01', 1, 5);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-02', 2, 7);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-03', 3, 11);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-08-30', 4, 12);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-01', 1, 13);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-02', 2, 10);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-04', 1, 4);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-04', 3, 8);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-04', 20, 9);
INSERT INTO orders(orders_date, orders_quantity, product_id) VALUES('2025-09-05', 2, 6);