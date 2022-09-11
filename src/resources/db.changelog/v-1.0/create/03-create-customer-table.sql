create table customer
(
    customer_id   bigserial not null,
    email         varchar(255),
    customer_name varchar(255),
    primary key (customer_id)
);
alter table customer
    add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email)