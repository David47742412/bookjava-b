create table category
(
    category_id          varchar(36) default uuid_generate_v4() not null
        constraint pk_category_id
            primary key,
    description          varchar(200),
    __workspace_create__ varchar(50),
    __workspace_update__ varchar(50),
    __create_date__      timestamp   default CURRENT_TIMESTAMP,
    __update_date__      timestamp   default CURRENT_TIMESTAMP,
    __ip_req__           varchar(50),
    __deleted__          boolean     default false
);

create table book
(
    book_id              varchar(36) default uuid_generate_v4() not null
        constraint pk_book_id
            primary key,
    title                varchar(200),
    description          varchar(500),
    __workspace_create__ varchar(50),
    __workspace_update__ varchar(50),
    __create_date__      timestamp   default CURRENT_TIMESTAMP,
    __update_date__      timestamp   default CURRENT_TIMESTAMP,
    __ip_req__           varchar(50),
    __deleted__          boolean     default false
);

create table book_detail
(
    book_id     varchar(36)
        constraint fk_book_detail_book_id
            references book,
    category_id varchar(36)
        constraint fk_book_detail_category_id
            references category,
    __deleted__ boolean default false
);
