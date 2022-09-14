create table employee_project
(
    employee_id int8 not null,
    project_id  int8 not null,
    primary key (employee_id, project_id)
);
alter table employee_project
    add constraint FK4yddvnm7283a40plkcti66wv9
        foreign key (project_id)
            references project;
alter table employee_project
    add constraint FKb25s5hgggo6k4au4sye7teb3a
        foreign key (employee_id)
            references employee;