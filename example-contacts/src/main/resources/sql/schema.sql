create table tb_user(
id int primary key auto_increment,
username varchar(50) not null unique,
password varchar(50) not null
);

create table tb_contact(
id int primary key auto_increment,
userid int not null,
username varchar(50) not null,
mobile varchar(50)
);

create table tb_log(
id int primary key auto_increment,
userid int,
content varchar(200) not null,
created datetime not null
);