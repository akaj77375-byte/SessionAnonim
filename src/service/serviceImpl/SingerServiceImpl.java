package service.serviceImpl;

import dao.SingerDao;
import dao.daoImpl.SingerDaoImpl;
import enums.Gender;
import models.Singer;
import models.Song;
import service.SingerService;

import java.util.List;

public class SingerServiceImpl implements SingerService {

SingerDao singerDao= new SingerDaoImpl();
    @Override
    public void create(Singer singer) {
try {
    if (singer.getFirstName()==null||singer.getFirstName().isBlank()||singer.getLastName()==null||singer.getLastName().isBlank()){
        throw new NullPointerException("Вы не написали имя или фамилию!");
    } else if (singer.getGender()==null) {
        throw new NullPointerException("Напишите пол");
    }
    singerDao.create(singer);
}catch (NullPointerException e){
    System.out.println(e.getMessage());
}
    }

    @Override
    public List<Singer> getAll() {
        return singerDao.getAll();
    }

    @Override
    public Singer getById(Long id) {
        try{
            if (id==null){
                throw new NullPointerException("Не написали Id");
            }
            return singerDao.getById(id);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Long id, Singer singer) {
        try {
            if (id == null) {
                throw new NullPointerException("Не написали Id");
            }
            if (singer == null) {
                throw new NullPointerException("Данные для обновления не могут быть null");
            }
            singerDao.update(id, singer);
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
            singerDao.delete(id);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Song> getAllSongsBySingerId(Long singerId) {
        try {
            if (singerId == null) {
                throw new NullPointerException("Не написали Id певца");
            }
            return singerDao.getAllSongsBySingerId(singerId);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Singer> filterBbyGender(Gender gender) {
        try {
            if (gender == null) {
                throw new NullPointerException("Вы не указали пол");
            }
            return singerDao.filterByGender(gender);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }
}
