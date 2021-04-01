-- 建库  建表

drop database if exists blog;

create database blog character set utf8mb4;
use blog;
create table user ( -- 用户表
    id int primary key auto_increment,
    username varchar(20) not null unique comment '账号',
    password varchar(20) not null,
    nickname varchar(20),-- 昵称
    sex bit,
    birthday date,
    head varchar(50) -- 头像
);

create table article( -- 文章表
    id int primary key auto_increment,
    title varchar(20) not null,  -- 标题
    content mediumtext not null, -- 内容
    create_time timestamp default now(),
    view_count int default 0, -- 浏览量
    user_id int,
    foreign key(user_id) references user(id)
);

-- 插入数据
insert into user(username,password) value ('a','1');
insert into user(username,password) value ('b','2');
insert into user(username,password) value ('c','3');

insert into article(title, content, user_id) value ('快速排序','public ...',1);
insert into article(title, content, user_id) value ('选择排序','public ...',1);
insert into article(title, content, user_id) value ('归并排序','public ...',1);
insert into article(title, content, user_id) value ('希尔排序','public ...',2);
insert into article(title, content, user_id) value ('插入排序','public ...',2);
-- 主外键关联的表，默认创建的主外键约束时restrict严格模式，比如从表
-- 有数据关联到主表某一行数据X，X不能删，否则会报错
-- 真实的设计上是不删除物理设计，在每一张表上设计一个字段表示是否有效

select id, username, password, nickname, sex, birthday, head from user where username = 'a'
select id,title from article where user_id =1;
delete from article where id in(1,2,3);