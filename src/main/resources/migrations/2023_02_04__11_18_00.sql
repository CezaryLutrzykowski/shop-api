-- liquibase formatted sql
-- changeset clutrzykowski:7.0


alter table product add constraint fk_product_category_id foreign key (category_id) references category (id);