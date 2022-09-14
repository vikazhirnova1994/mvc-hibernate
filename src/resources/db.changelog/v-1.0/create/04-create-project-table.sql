create table project
(
    project_id   bigserial    not null,
    createat     varchar(255) not null,
    finishat     varchar(255),
    name_project varchar(100) not null,
    customer_id  int8         not null,
    primary key (project_id)
);
alter table project
    add constraint FKj948tru2ilgqxfxsppp9mom5j
        foreign key (customer_id)
            references customer;