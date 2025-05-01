package dto;


import entity.many_to_many_tables.UserSong;

import java.util.List;

public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private List<UserSong> userSongs;

    public UserDto() {
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "UserDto(name=" + this.getName() + ", email=" + this.getEmail() + ")";
    }

    public List<UserSong> getUserSongs() {
        return this.userSongs;
    }

    public void setUserSongs(List<UserSong> userSongs) {
        this.userSongs = userSongs;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
