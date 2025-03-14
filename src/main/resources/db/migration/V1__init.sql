create table advertisement
(
    halal                   boolean,
    has_medical_certificate boolean,
    is_active               boolean,
    is_frozen               boolean,
    is_mramor               boolean,
    is_retail               boolean,
    months_age              integer,
    price                   numeric(38, 2),
    quantity                integer,
    weight                  float,
    creation_date           timestamp(6),
    id                      bigint      not null,
    kill_begin              date,
    kill_date               date,
    kill_end                date,
    seller_user_id          bigint,
    ad_type                 varchar(31) not null,
    animal_type             varchar(255),
    bird_type               varchar(255),
    breed                   varchar(255),
    description             varchar(255),
    fat_content             varchar(255),
    feeding_type            varchar(255),
    location                varchar(255),
    processing_type         varchar(255),
    title                   varchar(255),
    primary key (id)
);
create table opt_order
(
    is_active        boolean,
    is_confirmed     boolean,
    quantity         integer,
    advertisement_id bigint,
    buyer_user_id    bigint,
    id               bigint not null,
    kill_date        date,
    primary key (id)
);
create table retail_order
(
    is_active        boolean,
    is_confirmed     boolean,
    weight           float(53),
    advertisement_id bigint,
    buyer_user_id    bigint,
    id               bigint not null,
    primary key (id)
);
create table users
(
    id           bigint not null,
    city         varchar(255),
    email        varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    surname      varchar(255),
    primary key (id)
);
alter table if exists advertisement add constraint seller_user_id_foreign_key foreign key (seller_user_id) references users;
alter table if exists opt_order add constraint buyer_user_id_foreign_key foreign key (buyer_user_id) references users;
alter table if exists opt_order add constraint advertisement_id_foreign_key foreign key (advertisement_id) references advertisement;
alter table if exists retail_order add constraint buyer_user_id_foreign_key foreign key (buyer_user_id) references users;
alter table if exists retail_order add constraint advertisement_id_foreign_key foreign key (advertisement_id) references advertisement;