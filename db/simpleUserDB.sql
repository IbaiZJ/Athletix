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