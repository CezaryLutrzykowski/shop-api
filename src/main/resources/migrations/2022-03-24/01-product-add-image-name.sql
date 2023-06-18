--liquibase formatted sql
--changeset clutrzykowski:2
alter table product add image varchar(128) after currency;