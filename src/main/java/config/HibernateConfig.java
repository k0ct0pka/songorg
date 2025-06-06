package config;

import entity.Author;
import entity.Song;
import entity.User;
import entity.embeddableIds.AuthorSongId;
import entity.embeddableIds.UserSongId;
import entity.many_to_many_tables.AuthorSong;
import entity.many_to_many_tables.UserSong;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;

public class HibernateConfig {
    public SessionFactory createSessionFactory() {
        return new Configuration()
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL,"jdbc:postgresql://localhost:5432/reverie")
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER,"postgres")
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD,"postgres")
                .setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER,"org.postgresql.Driver")
                .setProperty(AvailableSettings.HBM2DDL_AUTO,"validate")
                .setProperty(AvailableSettings.SHOW_SQL,true)
                .setProperty(AvailableSettings.HIGHLIGHT_SQL,true)
                .setProperty(AvailableSettings.FORMAT_SQL,true)
                .setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,"thread")
                .setProperty(AvailableSettings.DEFAULT_SCHEMA, "reverie")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Song.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(UserSong.class)
                .addAnnotatedClass(AuthorSongId.class)
                .addAnnotatedClass(AuthorSong.class)
                .addAnnotatedClass(UserSongId.class)
                .buildSessionFactory();
    }
}
