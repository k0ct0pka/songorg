package service;

import dao.impls.UserDao;
import dto.UserDto;
import entity.Song;
import entity.User;
import entity.embeddableIds.UserSongId;
import entity.many_to_many_tables.UserSong;
import lombok.AllArgsConstructor;
import service.entityService.SongService;
import service.entityService.UserService;

@AllArgsConstructor
public class LikeService {
    private SongService songService;
    private UserDao userDao;
    private UserService userService;
    public void likeSong(Integer id, Integer songId) {
        User user = userService.findById(id);
        Song song = songService.getSongById(songId);
        UserSong userSong = UserSong.builder()
                .userSongId(
                        UserSongId.builder()
                                .userId(user.getId())
                                .songId(song.getId())
                                .build()
                )
                .user(
                        user
                ).song(
                        song
                ).build();
        userDao.likeSong(userSong);
    }

}
