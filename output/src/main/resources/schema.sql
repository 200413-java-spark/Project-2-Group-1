
drop table county;
drop table state;

--create table state(id serial primary key, state varchar not null);
create table state(id integer primary key, state varchar);

--create table county(id serial primary key, name varchar not null, state varchar not null);
create table county(id integer primary key, name varchar, state varchar);
