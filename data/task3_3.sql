-- TODO Task 3
DROP DATABASE IF EXISTS purchase_orders;

CREATE DATABASE purchase_orders;

USE purchase_orders;

CREATE TABLE orders (
  orderId CHAR(26) NOT NULL,
  orderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
  name VARCHAR(256) NOT NULL,
  address VARCHAR(256) NOT NULL,
  priority BOOLEAN,
  comments VARCHAR(256) NOT NULL,

  PRIMARY KEY (orderId)
);

CREATE TABLE cart_items (
  lineItemId INT AUTO_INCREMENT ,
  productId VARCHAR(256) NOT NULL,
  name VARCHAR(256) NOT NULL,
  quantity INT,
  price FLOAT,
  orderId CHAR(26),

  PRIMARY KEY (lineItemId),
  FOREIGN KEY (orderId) REFERENCES `orders`(orderId)
);
