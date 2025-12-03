package service.serviceImpl;

import dao.PlaylistDao;
import dao.daoImpl.PlayListDaoImpl;
import enums.Genre;
import models.Playlist;
import models.Song;
import service.PlayListService;

import java.util.List;

public class PlayListServiceImpl implements PlayListService {

PlaylistDao playlistDao=new PlayListDaoImpl();
    @Override
    public void create(Playlist playlist) {
        try {

             if (playlist.getImageUrl()==null||playlist.getImageUrl().isBlank()) {
                throw new NullPointerException("Напишите ссылку");
            }else if (playlist.getTitle()==null||playlist.getTitle().isBlank()) {
                throw new NullPointerException("Напишите название плейлиста");
            }
            playlistDao.create(playlist);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Playlist> getAll() {
        return playlistDao.getAll();
    }

    @Override
    public Playlist getById(Long id) {
        try {
            if (id==null){
                throw new NullPointerException("НАПИШИТЕ id");
            }
            return playlistDao.getById(id);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long id, Playlist playlist) {
        try {
            if (id == null) {
                throw new NullPointerException("НАПИШИТЕ id");
            }
            if (playlist.getImageUrl() == null || playlist.getImageUrl().isBlank()) {
                throw new NullPointerException("Напишите ссылку");
            } else if (playlist.getTitle() == null || playlist.getTitle().isBlank()) {
                throw new NullPointerException("Напишите название плейлиста");
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Song> getAllSongsByPlaylistId(Long playListId) {
        try {
            if (playListId == null) {
                throw new NullPointerException("НАПИШИТЕ id");
            }
            return playlistDao.getAllSongsByPlaylistId(playListId);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public void deleteSongFromPlaylist(Long playListId, Long songId) {
        try {
            if (playListId == null||songId==null) {
                throw new NullPointerException("НАПИШИТЕ id");
            }
            playlistDao.deleteSongFromPlaylist(playListId,songId);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Song> sortSongByAddedDate(String ascOrDesc) {
try {
    if (ascOrDesc==null||ascOrDesc.isBlank()){
        throw  new NullPointerException("Не должен быть нулл");
    }
    return playlistDao.sortSongByAddedDate(ascOrDesc);
}catch (NullPointerException e){
    System.out.println(e.getMessage());
}
        return List.of();
    }

    @Override
    public List<Song> filterSongByGenre(Genre genre) {
        try {
            if (genre==null){
                throw  new NullPointerException("Не должен быть нулл");
            }
            return playlistDao.filterSongByGenre(genre);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public void delete(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("НАПИШИТЕ id");
            }
            playlistDao.delete(id);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
