drop database if exists social_db;

create database social_db;

use social_db;

create table users(
id bigint primary key auto_increment,
email varchar(255) not null unique,
name varchar(255),
password varchar(255),
role enum('ADMIN', 'MEMBER') default(1),
status enum('ACTIVE', 'SUSPENDED') default(1),
created_at timestamp default(current_timestamp),
updated_at timestamp default(current_timestamp)
);

create table user_details(
user_id bigint primary key auto_increment,
birth_date date null,
address varchar(255) null,
foreign key (user_id) references users (id)
);

-- special query for profile_images table

-- select pi.id as id, pi.name as image from profile_images as pi
-- join users as u on u.id = pi.user_id
-- where pi.user_id = ?
-- order by (pi.id) desc;
create table profile_images(
id bigint primary key auto_increment,
name varchar(255) not null unique,
user_id bigint not null,
status enum('ACTIVE', 'INACTIVE'),
uploaded_at timestamp default(current_timestamp),
foreign key (user_id) references users (id)
);

create table cover_images(
id bigint primary key auto_increment,
name varchar(255) not null unique,
user_id bigint not null,
status enum('ACTIVE', 'INACTIVE'),
uploaded_at timestamp default(current_timestamp),
foreign key (user_id) references users (id)
);

-- special query for friends table

-- select f.id as id, u.name as friend from friends as f
-- join users as u on u.id = f.friend_id
-- where f.user_id = ?
-- order by (f.id);
create table friends(
id bigint primary key auto_increment,
user_id bigint not null,
friend_id bigint not null,
check (user_id <> friend_id),
foreign key (user_id) references users (id),
foreign key (friend_id) references users (id)
);

create table posts(
id bigint primary key auto_increment,
content longtext,
created_at timestamp default(current_timestamp),
updated_at timestamp default(current_timestamp),
user_id bigint not null,
foreign key (user_id) references users (id)
);

create table post_images(
id bigint primary key auto_increment,
name varchar(255) not null,
post_id bigint not null,
foreign key (post_id) references posts (id)
);

create table comments(
id bigint primary key auto_increment,
content longtext,
created_at timestamp default(current_timestamp),
updated_at timestamp default(current_timestamp),
user_id bigint not null,
post_id bigint not null,
foreign key (user_id) references users (id),
foreign key (post_id) references posts (id)
);


-- special query for display_posts table
-- select sp.id, sp.user_id as shared_uid, u.name as user, sp.content, sp.shared_at,
-- 			op.id, op.user_id as original_uid, op.user_name as original_user, op.content as original_content,
-- 			op.updated_at from shared_posts as sp
-- 			join users as  u on u.id = sp.user_id
-- 			join 
-- 			(select p.id as id, users.id as user_id, users.name as user_name, p.content as content,
-- 				p.updated_at as updated_at from posts as p
-- 				join users on users.id = p.user_id)
-- 			 as op on op.id = sp.post_id;

create table shared_posts(
id bigint primary key auto_increment,
user_id bigint not null,
post_id bigint not null,
content longtext,
shared_at timestamp default(current_timestamp),
foreign key (user_id) references users (id),
foreign key (post_id) references posts (id)
);

create table saved_posts(
id bigint primary key auto_increment,
user_id bigint not null,
post_id bigint not null,
saved_at timestamp default(current_timestamp),
foreign key (user_id) references users (id),
foreign key (post_id) references posts (id)
);