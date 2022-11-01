drop table if exists sessions;
create table sessions (
                       id                    serial,
                       session_start_time                  varchar(5) not null,
                       status               boolean         default false,
                       primary key (id)
);

insert into sessions (session_start_time)
values
    ('08:00'),
    ('10:00'),
    ('12:00'),
    ('14:00'),
    ('16:00'),
    ('18:00'),
    ('20:00'),
    ('22:00'),
    ('00:00'),
    ('02:00');


create table user_session (
                              user_pass_number      varchar(10) not null,
                              session_id               int not null,
                              primary key (user_pass_number, session_id),
                              foreign key (user_pass_number) references users (pass_number),
                              foreign key (session_id) references sessions (id)
);