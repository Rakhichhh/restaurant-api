create table if not exists menu_items (
                                          id bigint auto_increment primary key,
                                          name varchar(100) not null,
    price decimal(10,2) not null,
    is_available boolean not null
    );

insert into menu_items(name, price, is_available)
values ('Pizza', 2500.00, true);


create table if not exists orders (
  id bigint auto_increment primary key,
 customer_name varchar(100) not null,
    order_type varchar(20) not null,
    status varchar(30) not null,
    created_at timestamp not null
    );

create table if not exists order_items (
        id bigint auto_increment primary key,
                order_id bigint not null,
               menu_item_id bigint not null,
              quantity int not null,
          unit_price decimal(10,2) not null
    );
