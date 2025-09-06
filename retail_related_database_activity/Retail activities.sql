-- Task 1
SELECT p.product_name, c.category_name
FROM product p 
INNER JOIN  category c ON p.category_id = c.category_id;

-- Task 2
SELECT p.product_name, s.supplier_name
FROM product p
LEFT JOIN supplier s ON p.supplier_id = s.supplier_id;

-- Task 3
SELECT s.supplier_name, p.product_name
FROM product p 
RIGHT JOIN supplier s ON s.supplier_id = p.supplier_id;

-- Task 4
(SELECT s.supplier_name, p.product_name
FROM product AS p 
LEFT JOIN supplier AS s ON s.supplier_id = p.supplier_id)
UNION(SELECT s.supplier_name, p.product_name
FROM product AS p 
RIGHT JOIN supplier AS s ON s.supplier_id = p.supplier_id);

-- Task 5
SELECT p.product_name, s.supplier_name, i.inventory_quantity
FROM inventory i
INNER JOIN product p ON p.product_id = i.product_id
INNER JOIN supplier s ON s.supplier_id = p.supplier_id;

-- Task 6
SELECT p.product_name, sum(o.orders_quantity), sum(o.orders_total_price) AS total_revenue
FROM orders o
INNER JOIN product p ON p.product_id = o.product_id
GROUP BY p.product_name ORDER BY total_revenue DESC;

-- Task 7
SELECT o.orders_id, o.orders_date, p.product_name, c.category_name, s.supplier_name, o.orders_quantity, o.orders_total_price
FROM orders o
INNER JOIN product p ON p.product_id = o.product_id
INNER JOIN category c ON p.category_id = c.category_id
INNER JOIN supplier s ON s.supplier_id = p.supplier_id;

-- Task 8
SELECT p.product_name, s.supplier_name, i.inventory_quantity 
FROM product p
INNER JOIN supplier s ON p.supplier_id =  s.supplier_id
INNER JOIN inventory i ON i.product_id = p.product_id
WHERE i.inventory_quantity < 10;

-- Task 9.1
SELECT s.supplier_name
FROM product p
INNER JOIN supplier s ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_name
HAVING COUNT(DISTINCT p.category_id) > 1;

-- Task 9.2
SELECT p.product_name
FROM product p
LEFT JOIN orders o ON p.product_id = o.product_id
WHERE o.product_id IS NULL;

-- Task 9.3
SELECT c.category_name
FROM product p
INNER JOIN category c ON c.category_id = p.category_id
INNER JOIN orders o ON o.product_id = p.product_id
GROUP BY c.category_id
ORDER BY SUM(o.orders_total_price) DESC
LIMIT 1;