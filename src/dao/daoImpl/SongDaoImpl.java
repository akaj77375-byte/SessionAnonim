package dao.daoImpl;

import dao.SongDao;
import dataBase.Db;
import models.Playlist;
import models.Singer;
import models.Song;

import java.util.List;

public class SongDaoImpl implements SongDao {



    @Override
    public void create(Song song) {
        Db.songs.add(song);
    }

    @Override
    public List<Song> getAll() {
        return Db.songs;
    }

    @Override
    public Song getById(Long id) {
        for (Song s:Db.songs){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public void update(Long id, Song song) {
        for (int i = 0; i < Db.songs.size(); i++) {
            if (Db.songs.get(i).getId().equals(id)) {
                Db.songs.set(i, song);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Db.songs.removeIf(song -> song.getId().equals(id));
    }
    @Override
    public void assignSongToPlayList(Long songId, Long playListId) {
for (Song s:Db.songs){
    if (s.getId().equals(songId)){
        for (Playlist p:Db.playlists){
            if (p.getId().equals(playListId)){
                p.getSongs().add(s);
            }
        }
    }
}
    }
}
