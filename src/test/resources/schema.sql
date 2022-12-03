create table product
(
    product_id   bigint auto_increment primary key,
    product_name varchar(20) not null,
    price        integer     not null
);

create table member
(
    member_id   bigint auto_increment primary key,
    member_name varchar(5)  not null,
    email       varchar(20) not null
);

create table detail
(
    detail_id  bigint auto_increment primary key,
    product_id bigint     not null,
    size       varchar(3) not null,
    quantity   integer
);

create table event
(
    event_id   bigint auto_increment primary key,
    product_id bigint    not null,
    deadline   timestamp not null,
    foreign key (product_id) references product (product_id)
);

create table form
(
    form_id    bigint auto_increment primary key,
    member_id  bigint     not null,
    event_id   bigint     not null,
    submission timestamp  not null,
    size       varchar(3) not null,
    foreign key (member_id) references member (member_id),
    foreign key (event_id) references event (event_id)
);