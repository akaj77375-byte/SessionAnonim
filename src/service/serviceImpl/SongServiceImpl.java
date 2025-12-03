package service.serviceImpl;

import dao.SongDao;
import dao.daoImpl.SongDaoImpl;
import models.Song;
import service.SongService;

import java.time.LocalDate;
import java.util.List;

public class SongServiceImpl implements SongService {
SongDao songDao=new SongDaoImpl();
    @Override
    public void create(Song song) {
        try {
            if (song == null) {
                throw new NullPointerException("Song не должен быть null");
            }
            if (song.getName() == null || song.getName().isBlank()) {
                throw new NullPointerException("Название песни не должно быть пустым");
            }
            if (song.getDuration() == null || song.getDuration().isBlank()) {
                throw new NullPointerException("Вы не указали длительность песни");
            }
            if (song.getGenre() == null) {
                throw new NullPointerException("Вы не указали жанр песни");
            }
            if (song.getSinger() == null) {
                throw new NullPointerException("Вы не указали певца");
            }


            song.setAddedDate(LocalDate.now());

            songDao.create(song);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Song> getAll() {
        return songDao.getAll();
    }

    @Override
    public Song getById(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("Не написали Id песни");
            }
            return songDao.getById(id);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long id, Song song) {
        try {
            if (id == null) {
                throw new NullPointerException("Не написали Id");
            }
            if (song == null) {
                throw new NullPointerException("Данные для обновления не могут быть null");
            }

            songDao.update(id, song);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("Не написали Id");
            }
            songDao.delete(id);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignSongToPlayList(Long songId, Long playListId) {
        try {
            if (songId == null || playListId == null) {
                throw new NullPointerException("songId и playListId не должны быть null");
            }

            songDao.assignSongToPlayList(songId, playListId);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
