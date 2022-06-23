DROP table if exists article cascade;
DROP table if exists reference cascade;
DROP table if exists category cascade;

create table category(
    category_id bigserial primary key,
    name text unique not null
);

create table article(
    article_id bigserial primary key,
    create_timestamp timestamp not null ,
    timestamp timestamp not null ,
    language varchar(2) not null ,
    wiki text not null,
    title text not null,
    auxiliary_text text[]
);
create table reference(
    reference_id bigserial primary key,
    article_id bigint not null,
    category_id bigint not null,
    FOREIGN KEY (article_id) REFERENCES article(article_id) on delete restrict,
    FOREIGN KEY (category_id) REFERENCES category(category_id) on delete restrict
);
