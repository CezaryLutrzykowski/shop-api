-- liquibase formatted sql
-- changeset clutrzykowski:1.0
-- validCheckSum: ANY

ALTER TABLE `product` ADD `image` VARCHAR(128) NULL AFTER `currency`;