insert into regions (id, name) values (1, 'Africa');
insert into regions (id, name) values (2, 'Europa');


insert into customers (id,first_name,last_name,email,created_at,region_id) values(1,'Ivan','Ivanov','ivanov@gmail.com','2020-03-03',1);
insert into customers (id,first_name,last_name,email,created_at,region_id) values(2,'Petr','Petrov','petrov@gmail.com','2020-04-04',2);

insert into users (username, password, enabled, first_name, last_name, email) values ('user', '$2a$10$P0KUwJ18KtORx3xQ31zh3OJmfFGkNnVv.aFxe7DEuBVgUlTdWXTF6', 1, 'User','User','user@gmail.com' );
insert into users (username, password, enabled, first_name, last_name, email) values ('admin', '$2a$10$.a6eLsCIXpxVmhE//ZaWPeNTPHUL480ulAK261M6eXNWjzK9pVqpa', 1,'Admin','Admin','admin@gmail.com');

insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');

insert into users_authorities (user_id, role_id) values (1,1);
insert into users_authorities (user_id, role_id) values (2,2);

insert into products (name, price, created_at) values ('Panasonic Pantalla LCD', 259990, NOW());
insert into products (name, price, created_at) values ('Sonny Camera digiatal DCS-W320B', 132453, NOW());
insert into products (name, price, created_at) values ('Applkw IPod shuffle', 1432355, NOW());
insert into products (name, price, created_at) values ('HW Notebook', 259990, NOW());

insert into bills (description, comment, customer_id, created_at) values ('Bill team of office', null, 1, NOW());
insert into bills (description, comment, customer_id, created_at) values ('Bill bicycle', 'Some important note', 1, NOW());

insert into facturas_items (quantity, factura_id, product_id) values(1,1,1);
insert into facturas_items (quantity, factura_id, product_id) values(2,1,2);
insert into facturas_items (quantity, factura_id, product_id) values(1,1,3);
insert into facturas_items (quantity, factura_id, product_id) values(1,1,4);

