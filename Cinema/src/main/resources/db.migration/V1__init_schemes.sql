
create table usr
(
    usr_id          bigserial primary key ,
    active          boolean         not null,
    email           varchar(255)    not null,
    password        varchar(255)    not null,
    name            varchar(255),
    surname         varchar(255),
    phone           varchar(255)
);

create table session_data
(
    s_id bigserial primary key ,
    usr_id int8 not null,
    date timestamp not null,
    ip varchar(255) not null
);

create table upload
(
    p_id bigserial primary key ,
    usr_id int8 not null,
    name varchar(255) not null,
    mime varchar(255) not null
);