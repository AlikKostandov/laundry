create table roles (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);
insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN'), ('DELETE_MANAGER');