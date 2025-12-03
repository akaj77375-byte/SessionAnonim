package service;

import models.Song;

public interface SongService extends GenericService<Song> {
    void assignSongToPlayList(Long songId,Long playListId);
}
