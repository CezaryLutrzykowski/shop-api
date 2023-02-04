-- liquibase formatted sql
-- changeset clutrzykowski:4.0
-- validCheckSum: ANY

alter table product add full_description text default null after description;
