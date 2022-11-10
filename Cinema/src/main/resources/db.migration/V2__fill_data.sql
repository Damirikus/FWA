insert into usr (id, email, password, active, name) values (1, 'admin', '12345', true, 'admin');
insert into usr_role (usr_id, roles) values (1, 'USER'), (1, 'ADMIN');

