package entity;

import entity.many_to_many_tables.AuthorSong;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "song")
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String link;
    private String genre;
    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER)

    private List<AuthorSong> authorSongs;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Song() {
    }

    public static SongBuilder builder() {
        return new SongBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }

    public String getGenre() {
        return this.genre;
    }

    public List<AuthorSong> getAuthorSongs() {
        return this.authorSongs;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthorSongs(List<AuthorSong> authorSongs) {
        this.authorSongs = authorSongs;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Song)) return false;
        final Song other = (Song) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$link = this.getLink();
        final Object other$link = other.getLink();
        if (this$link == null ? other$link != null : !this$link.equals(other$link)) return false;
        final Object this$genre = this.getGenre();
        final Object other$genre = other.getGenre();
        if (this$genre == null ? other$genre != null : !this$genre.equals(other$genre)) return false;
        final Object this$authorSongs = this.getAuthorSongs();
        final Object other$authorSongs = other.getAuthorSongs();
        if (this$authorSongs == null ? other$authorSongs != null : !this$authorSongs.equals(other$authorSongs))
            return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Song;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $link = this.getLink();
        result = result * PRIME + ($link == null ? 43 : $link.hashCode());
        final Object $genre = this.getGenre();
        result = result * PRIME + ($genre == null ? 43 : $genre.hashCode());
        final Object $authorSongs = this.getAuthorSongs();
        result = result * PRIME + ($authorSongs == null ? 43 : $authorSongs.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    public String toString() {
        return "Song(id=" + this.getId() + ", name=" + this.getName() + ", link=" + this.getLink() + ", genre=" + this.getGenre() + ", authorSongs=" + this.getAuthorSongs() + ", user=" + this.getUser() + ")";
    }

    public static class SongBuilder {
        private Integer id;
        private String name;
        private String link;
        private String genre;
        private List<AuthorSong> authorSongs;
        private User user;

        SongBuilder() {
        }

        public SongBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SongBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SongBuilder link(String link) {
            this.link = link;
            return this;
        }

        public SongBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public SongBuilder authorSongs(List<AuthorSong> authorSongs) {
            this.authorSongs = authorSongs;
            return this;
        }

        public SongBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Song build() {
            return new Song(this.id, this.name, this.link, this.genre, this.authorSongs, this.user);
        }

        public String toString() {
            return "Song.SongBuilder(id=" + this.id + ", name=" + this.name + ", link=" + this.link + ", genre=" + this.genre + ", authorSongs=" + this.authorSongs + ", user=" + this.user + ")";
        }
    }
}
