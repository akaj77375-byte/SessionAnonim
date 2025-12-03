package dao;

import models.Song;

public interface SongDao extends GenericDao<Song>{
    void assignSongToPlayList(Long songId,Long playListId);
}
