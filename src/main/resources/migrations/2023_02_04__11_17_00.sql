-- liquibase formatted sql
-- changeset clutrzykowski:6.0

alter table product
    add category_id bigint after category;

alter table product drop column category

