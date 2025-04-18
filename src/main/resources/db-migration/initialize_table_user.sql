create table reverie."user"
(
    id       integer
        constraint user_id
            primary key,
    name     varchar(255),
    email    varchar(255)
        constraint email
            unique,
    password varchar(255)
);
create table reverie."author"
(
    id            integer
        constraint author_id
            primary key,
    name          varchar(255),
    concerts_link varchar(255)
);

create table reverie.song
(
    id        integer
        constraint song_id
            primary key,
    name      varchar(255),
    link      varchar(255),
    genre     varchar(255),
    author_id integer
        constraint author_id
            references reverie.author (id),
    user_id   integer
        constraint user_id
            references reverie."user" (id)
);
