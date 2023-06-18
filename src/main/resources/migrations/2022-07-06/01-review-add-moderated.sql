--liquibase formatted sql
--changeset clutrzykowski:9
alter table review add moderated boolean  default false;
