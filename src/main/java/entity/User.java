package entity;

import entity.many_to_many_tables.UserSong;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Entity
@Table(name = "user", schema = "reverie")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Integer id;
    @Column(name = "name")
    private
    String name;
    @Column(name = "email")
    private
    String email;
    @Column
    private
    String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private
    List<UserSong> userSongs;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<UserSong> getUserSongs() {
        return this.userSongs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserSongs(List<UserSong> userSongs) {
        this.userSongs = userSongs;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$userSongs = this.getUserSongs();
        final Object other$userSongs = other.getUserSongs();
        if (this$userSongs == null ? other$userSongs != null : !this$userSongs.equals(other$userSongs)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $userSongs = this.getUserSongs();
        result = result * PRIME + ($userSongs == null ? 43 : $userSongs.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", userSongs=" + this.getUserSongs() + ")";
    }
}
