package dao;

import enums.Gender;
import models.Singer;
import models.Song;

import java.util.List;

public interface SingerDao extends GenericDao<Singer> {

    List<Song> getAllSongsBySingerId(Long singerId);

    List<Singer> filterByGender(Gender gender);
}
