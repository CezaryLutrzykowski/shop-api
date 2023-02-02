-- liquibase formatted sql
-- changeset clutrzykowski:3.0
-- validCheckSum: ANY

ALTER TABLE `product` ADD `slug` VARCHAR(255) NOT NULL AFTER `image`;
-- najlepiej robić na oddzielnych changeset alter table (!)
ALTER TABLE `product` ADD constraint ui_product_slug unique key(slug);