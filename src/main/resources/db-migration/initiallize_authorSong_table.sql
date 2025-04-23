CREATE TABLE reverie."AuthorSong" (
    author_id  integer NOT NULL ,
    song_id integer NOT NULL ,
    PRIMARY KEY (author_id,song_id),
    CONSTRAINT integer FOREIGN KEY(author_id) references reverie.author(id) on delete cascade,
    CONSTRAINT fk_song_id  Foreign KEY (song_id) references reverie.song(id) on delete cascade
);
CREATE TABLE reverie."UserSong" (
      user_id  integer NOT NULL ,
      song_id integer NOT NULL ,
      PRIMARY KEY (user_id,song_id),
      CONSTRAINT integer FOREIGN KEY(user_id) references reverie."user"(id) on delete cascade,
      CONSTRAINT fk_song_id  Foreign KEY (song_id) references reverie.song(id) on delete cascade
);
ALTER TABLE reverie.song drop column author_id;