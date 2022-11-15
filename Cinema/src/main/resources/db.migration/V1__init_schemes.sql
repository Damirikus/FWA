
create table usr
(
    id              int8            not null,
    activation_code varchar(255),
    active          boolean         not null,
    email           varchar(255)    not null,
    password        varchar(255)    not null,
    name            varchar(255),
    surname         varchar(255),
    phone           varchar(255),
    primary key (id)
);

create table usr_role
(
    usr_id  int8    not null,
    roles   varchar(255)
);

alter table if exists usr_role
    add constraint user_role_user_fk
    foreign key (usr_id) references usr;