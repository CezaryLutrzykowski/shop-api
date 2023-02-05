-- liquibase formatted sql
-- changeset clutrzykowski:9.0

create table review
(
    id          bigint      not null auto_increment PRIMARY KEY,
    product_id  bigint      not null,
    author_name varchar(60) not null,
    content     text
);