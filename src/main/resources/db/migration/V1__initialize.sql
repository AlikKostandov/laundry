create table roles (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);
insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER');


create table users (
                       pass_number               varchar(10) not null,
                       name              varchar(30) not null,
                       surname              varchar(30) not null,
                       password              varchar(80),
                       room                 varchar(50),
                       primary key (pass_number)
);


CREATE TABLE users_roles (
                             user_pass_number               varchar(10) not null,
                             role_id               int not null,
                             primary key (user_pass_number, role_id),
                             foreign key (user_pass_number) references users (pass_number),
                             foreign key (role_id) references roles (id)
);