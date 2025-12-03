package dao.daoImpl;

import dao.SingerDao;
import dataBase.Db;
import enums.Gender;
import models.Singer;
import models.Song;

import java.util.ArrayList;
import java.util.List;

public class SingerDaoImpl implements SingerDao {



    @Override
    public void create(Singer singer) {
        Db.singers.add(singer);
    }

    @Override
    public List<Singer> getAll() {
        return Db.singers;
    }

    @Override
    public Singer getById(Long id) {
        for (Singer s:Db.singers){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }

    @Override
    public void update(Long id, Singer singer) {
        for (int i = 0; i < Db.singers.size(); i++) {
            if (Db.singers.get(i).getId().equals(id)){
                Db.singers.set(i,singer);
            }
        }
    }

    @Override
    public void delete(Long id) {
Db.singers.removeIf(singer -> singer.getId().equals(id));
    }
    @Override
    public List<Song> getAllSongsBySingerId(Long singerId) {
       for (Singer s: Db.singers){
           if (s.getId().equals(singerId)){
               return s.getSongs();
           }
       }
        return List.of();
    }

    @Override
    public List<Singer> filterByGender(Gender gender) {
        List<Singer>genders=new ArrayList<>();
        for (Singer s:Db.singers) {
            if (s.getGender().equals(gender)){
                genders.add(s);
            }
        }
        return genders;
    }
}
