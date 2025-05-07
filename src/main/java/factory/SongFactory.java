package factory;

import entity.Song;
import service.entityService.SongService;

import java.util.Map;

public class SongFactory implements BaseFactory<Song>{
    @Override
    public Song create(Map<String, Object> params) {
        return Song.builder()
                .name((String) params.get(SongService.NAME))
                .link((String) params.get(SongService.LINK))
                .genre((String) params.get(SongService.GENRE))
                .build();
    }
}
