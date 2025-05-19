drop database if exists athletix;
create database athletix;
use athletix;

create table users (
	id smallint unsigned,
    username varchar(50) not null,
    password varchar(50) not null
);

alter table users
	add constraint users_PK primary key(id);

insert into users (id, username, password) values
(1, 'ibai', '$2a$10$/5w6KE9wRrlbOHhTw6xrge1Irv7Uf8IRL00qXnmH6LVYEUrj3yEGm'); -- -u ibai -p ibai