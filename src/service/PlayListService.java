package service;

import enums.Genre;
import models.Playlist;
import models.Song;

import java.util.List;

public interface PlayListService extends GenericService<Playlist> {
    List<Song> getAllSongsByPlaylistId(Long playListId);
    void deleteSongFromPlaylist(Long playListId,Long songId);

    List<Song> sortSongByAddedDate(String ascOrDesc);
    List<Song> filterSongByGenre(Genre genre);
}
