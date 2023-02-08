-- liquibase formatted sql
-- changeset clutrzykowski:10.0

alter table review
    add moderated boolean default false;