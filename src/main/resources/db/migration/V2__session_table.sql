drop table if exists sessions cascade;
create table sessions (
                       id                    serial,
                       session_start_time                  varchar(5) not null,
                       status               boolean         default false,
                       floor                int not null,
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

drop table if exists user_session cascade;
create table user_session (
                              user_pass_number      varchar(10) not null,
                              session_id               int not null,
                              primary key (user_pass_number, session_id),
                              foreign key (user_pass_number) references users (pass_number),
                              foreign key (session_id) references sessions (id)
);