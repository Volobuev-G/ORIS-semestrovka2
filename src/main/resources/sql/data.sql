insert into users (email, username, password, role)
VALUES ('admin@admin','admin','12345678','admin'),
       ('client@client','client','12345','client');

insert into flat (flatname, status, location, cost)
VALUES ('3-room apartment','free','Moscow',1000),
       ('1-room apartment','free','Kazan',100);

insert into comment (id_flat, author, date, content)
values (1,'Mike','2022-12-03','Cool');