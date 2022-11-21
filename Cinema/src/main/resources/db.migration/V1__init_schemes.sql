
create table usr
(
    id              int8            not null,
    active          boolean         not null,
    email           varchar(255)    not null,
    password        varchar(255)    not null,
    name            varchar(255),
    surname         varchar(255),
    phone           varchar(255),
    primary key (id)
);