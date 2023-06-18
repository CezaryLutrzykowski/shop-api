--liquibase formatted sql
--changeset clutrzykowski:4
alter table product add full_description text default null after description;
