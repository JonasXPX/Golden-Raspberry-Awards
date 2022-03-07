CREATE TABLE movie
(
    id       integer auto_increment not null,
    title    varchar(255) not null,
    year     integer,
    studio   varchar,
    winner   bool,

    constraint pk_id_movies primary key (id)
);

CREATE TABLE producer
(
    id integer auto_increment not null,
    name varchar(255) not null,
    id_movie integer not null,

    constraint pk_id_movie foreign key (id_movie) references movie(id)
);
