CREATE TABLE movie
(
    id       integer auto_increment not null,
    title    varchar(255) not null,
    year     integer,
    studio   varchar,
    producer varchar,
    winner   smallint,

    constraint pk_id_movies primary key (id)
);
