drop database if exists servlet_blog;
-- 创建数据库
create database servlet_blog character set utf8mb4;
-- 使用数据库
use servlet_blog;
-- 建表
create table user(
    id int primary key auto_increment,
    username varchar(20) not null unique comment '账号',
    password varchar(20) not null unique comment '密码',
    nickname varchar(20),
    sex bit,
    birthday date,
    head varchar(50)
);

create table article(
    id int primary key auto_increment,
    title varchar(20) not null,
    content mediumtext not null,
    -- 使用函数now()
    create_time timestamp default now(),
    view_count int default 0,
    -- 浏览量开始默认为0
    user_id int,
    foreign key (user_id) references user(id)
);

insert into user(username,password) value ('abc1','1');
insert into user(username,password) value ('abc2','2');
insert into user(username,password) value ('abc3','3');

insert into article(title, content,user_id) value ('快排','public...',1);
insert into article(title, content,user_id) value ('冒泡','public...',1);
insert into article(title, content,user_id) value ('选择','public...',1);
insert into article(title, content,user_id) value ('归并','public...',2);
insert into article(title, content,user_id) value ('插入','public...',2);

-- 主外键关联的表，默认创建的主外键约束是restrict严格模式
-- 比如从表有数据关联到主表某一行数据X,那X不能删
-- 真实的设计上不是删除物理数据，再每一张表上设计一个字段，表示是否有效


