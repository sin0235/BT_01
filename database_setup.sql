-- Database setup script for login_demo
-- Run this script in MySQL to create the database and sample data

CREATE DATABASE IF NOT EXISTS login_demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE login_demo;

CREATE TABLE `User` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(120) UNIQUE NOT NULL,
  username VARCHAR(60) UNIQUE NOT NULL,
  fullname VARCHAR(120) NOT NULL,
  password VARCHAR(120) NOT NULL,   -- demo: plain text
  avatar VARCHAR(255),
  roleid INT NOT NULL,              -- 1=admin, 2=manager, else=user
  phone VARCHAR(20),
  createddate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample users for testing
INSERT INTO `User`(email,username,fullname,password,avatar,roleid,phone)
VALUES
('admin@ex.com','admin','System Admin','admin123',NULL,1,'0900000001'),
('manager@ex.com','manager','Store Manager','manager123',NULL,2,'0900000002'),
('user@ex.com','user','Regular User','user123',NULL,3,'0900000003');

-- Verify the data
SELECT * FROM `User`;

-- Category table for user categories (1-N relationship with User)
CREATE TABLE IF NOT EXISTS `Category` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  name VARCHAR(120) NOT NULL,
  description VARCHAR(255),
  createddate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Insert sample categories for testing (for user with id=3)
INSERT INTO `Category`(user_id, name, description) VALUES 
(3, 'Work', 'Work related tasks and projects'),
(3, 'Personal', 'Personal notes and reminders'),
(3, 'Study', 'Learning materials and courses');

-- Verify the category data
SELECT * FROM `Category`;

-- Note: For SQL Server, use the following modifications:
-- Replace AUTO_INCREMENT with IDENTITY(1,1)
-- Replace DATETIME with DATETIME2
-- Replace CURRENT_TIMESTAMP with GETDATE()
-- Remove backticks around table/column names