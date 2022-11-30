create table product
(
    product_id   bigint primary key,
    product_name varchar(20) not null,
    price        integer     not null,
    size         varchar(10) not null
);

create table member
(
    member_id   bigint primary key,
    member_name varchar(5)  not null,
    email     varchar(20) not null
);

create table draw
(
    draw_id    bigint primary key,
    product_id bigint    not null,
    member_id    bigint    not null,
    due_date   timestamp not null,
    foreign key (product_id) references product (product_id),
    foreign key (member_id) references member (member_id)
)