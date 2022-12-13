drop table if exists sessions cascade;
create table sessions (
                       id                    serial,
                       session_start_time   varchar(5)  not null,
                       status               boolean     default false,
                       floor                int         not null,
                       user_pass_number     varchar(10),
                       foreign key (user_pass_number) references users (pass_number),
                       primary key (id)
);


insert into sessions (session_start_time, floor)
values
    ('08:00', 15),
    ('10:00', 15),
    ('12:00', 15),
    ('14:00', 15),
    ('16:00', 15),
    ('18:00', 15),
    ('20:00', 15),
    ('22:00', 15),
    ('00:00', 15);
