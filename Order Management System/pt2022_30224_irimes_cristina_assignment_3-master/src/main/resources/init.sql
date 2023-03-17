DROP DATABASE `assig3`;
CREATE SCHEMA `assig3`;
CREATE TABLE `assig3`, `client`(
    `id` INT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(64) NOT NULL,
    `address` VARCHAR(64) NOT NULL,
    PRIMARY KEY(`id`)
    );