drop table if exists sessions cascade;
create table sessions (
                       id                    serial,
                       session_start_time   timestamp  not null,
                       status               boolean     default false,
                       floor                int         not null,
                       user_pass_number     varchar(10),
                       foreign key (user_pass_number) references users (pass_number),
                       primary key (id)
);


insert into sessions (session_start_time, floor)
values
    ('2022-12-15 08:00', 15),
    ('2022-12-15 10:00', 15),
    ('2022-12-15 12:00', 15),
    ('2022-12-15 14:00', 15),
    ('2022-12-15 16:00', 15),
    ('2022-12-15 18:00', 15),
    ('2022-12-15 20:00', 15),
    ('2022-12-15 22:00', 15),
    ('2022-12-15 00:00', 15);
