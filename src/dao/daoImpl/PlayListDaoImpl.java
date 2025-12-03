package dao.daoImpl;

import dao.PlaylistDao;
import dataBase.Db;
import enums.Genre;
import models.Playlist;
import models.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayListDaoImpl implements PlaylistDao {


    @Override
    public void create(Playlist playlist) {
        Db.playlists.add(playlist);
    }

    @Override
    public List<Playlist> getAll() {
        return Db.playlists;
    }

    @Override
    public Playlist getById(Long id) {
        for (Playlist p:Db.playlists){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public void update(Long id, Playlist playlist) {
        for (int i = 0; i < Db.playlists.size(); i++) {
            if (Db.playlists.get(i).getId().equals(id)){
                Db.playlists.set(i,playlist);
        }
}
    }

    @Override
    public void delete(Long id) {
                Db.playlists.removeIf(playlist -> playlist.getId().equals(id));

    }
    @Override
    public List<Song> getAllSongsByPlaylistId(Long playListId) {
        for (Playlist p:Db.playlists){
            if (p.getId().equals(playListId)){
                return p.getSongs();
            }
        }
        return List.of();
    }

    @Override
    public void deleteSongFromPlaylist(Long playListId, Long songId) {
        for (int i = 0; i < Db.playlists.size(); i++) {
            if (Db.playlists.get(i).getId().equals(playListId)){
                Db.playlists.get(i).getSongs().removeIf(song -> song.getId().equals(songId));
            }
        }
    }

    @Override
    public List<Song> sortSongByAddedDate(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            for (int i = 0; i < Db.playlists.size(); i++) {
                Db.playlists.get(i).getSongs().sort(Comparator.comparing(Song::getAddedDate));
            }
        }
        if (ascOrDesc.equalsIgnoreCase("desc")) {
            for (int i = 0; i < Db.playlists.size(); i++) {
                Db.playlists.get(i).getSongs().sort(Comparator.comparing(Song::getAddedDate).reversed());
            }
        }
        return List.of();
    }

    @Override
    public List<Song> filterSongByGenre(Genre genre) {
        List<Song>genres=new ArrayList<>();
        for (Playlist p: Db.playlists){
            for (Song s: p.getSongs()){
                if (s.getGenre().equals(genre)){
             genres.add(s);

                }
            }
        }
        return genres;

    }
}
