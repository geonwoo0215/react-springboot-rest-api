create table product
(
    product_id   bigint primary key,
    product_name varchar(20) not null,
    price        integer     not null,
    size         varchar(10) not null
);

create table user
(
    user_id   bigint primary key,
    user_name varchar(5)  not null,
    email     varchar(20) not null
);

create table draw
(
    draw_id    bigint primary key,
    product_id bigint    not null,
    user_id    bigint    not null,
    due_date   timestamp not null,
    foreign key (product_id) references product (product_id),
    foreign key (user_id) references user (user_id)
)