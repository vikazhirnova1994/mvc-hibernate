create table employee
(
    employee_id bigserial not null,
    first_name  varchar(255),
    last_name   varchar(255),
    position_id int8      not null,
    primary key (employee_id)
);

alter table employee
    add constraint UK_6o1mvrxpyqmb7haarvayfcn5j unique (position_id);
alter table employee
    add constraint FKbc8rdko9o9n1ri9bpdyxv3x7i
        foreign key (position_id)
            references position;

