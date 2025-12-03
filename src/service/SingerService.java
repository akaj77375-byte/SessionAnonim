package service;

import enums.Gender;
import models.Singer;
import models.Song;

import java.util.List;

public interface SingerService extends GenericService<Singer> {

    List<Song> getAllSongsBySingerId(Long singerId);

    List<Singer> filterBbyGender(Gender gender);
}
