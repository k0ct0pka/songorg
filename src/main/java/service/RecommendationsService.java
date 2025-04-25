package service;

import dto.UserDto;
import entity.Song;
import java.util.*;

import entity.many_to_many_tables.UserSong;
import jakarta.servlet.http.HttpServletRequest;

public class RecommendationsService {
    public List<Song> getRecommendations(UserDto user) {
        List<UserSong> userSongs = user.getUserSongs();
        for (UserSong userSong : userSongs) {
            userSong.getSong();
        }
    }
}
