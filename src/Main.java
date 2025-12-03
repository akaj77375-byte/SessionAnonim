import dao.daoImpl.PlayListDaoImpl;
import dataBase.GenId;
import enums.Gender;
import enums.Genre;
import models.Playlist;
import models.Singer;
import models.Song;
import service.PlayListService;
import service.SingerService;
import service.SongService;
import service.serviceImpl.PlayListServiceImpl;
import service.serviceImpl.SingerServiceImpl;
import service.serviceImpl.SongServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayListService playListService = new PlayListServiceImpl();
        SongService songService = new SongServiceImpl();
        SingerService singerService = new SingerServiceImpl();

        while (true) {
            System.out.println("""
                    MENU:
                    1. Создать плейлист
                    2. Создать певца
                    3. Создать песню
                    4. Все плейлисты
                    5. Все певцы
                    6. Все песни
                    7. Выйти
                    Выберите: 
                    """);

            int choice = safeInt(sc);

            switch (choice) {

                case 1 -> {
                    System.out.println("Название плейлиста: ");
                    String title = safeString(sc);

                    System.out.println("Image URL:");
                    String image = safeString(sc);

                    Long id = autoIncrementPlayListId();

                    playListService.create(new Playlist(GenId.getPlaylistsId(),title, image,new ArrayList<Song>()));
                    System.out.println("Плейлист создан!");
                }

                case 2 -> {
                    System.out.println("Имя певца:");
                    String firstName = safeString(sc);

                    System.out.println("Фамилия певца:");
                    String lastName = safeString(sc);

                    System.out.println("Пол (MALE/FEMALE):");
                    Gender gender = safeGender(sc);

                    Long id = autoIncrementSingerId();

                    singerService.create(new Singer(id, firstName, lastName, gender, List.of()));
                    System.out.println("Певец создан!");
                }

                case 3 -> {
                    System.out.println("Название песни:");
                    String name = safeString(sc);

                    System.out.println("Длительность:");
                    String duration = safeString(sc);

                    System.out.println("Жанр (RAP/ROCK/JAZZ/CLASSIC):");
                    Genre genre = safeGenre(sc);

                    System.out.println("ID певца:");
                    Long singerId = safeLong(sc);

                    Singer singer = singerService.getById(singerId);
                    if (singer == null) {
                        System.out.println("Певец не найден!");
                        break;
                    }


                    Song song = new Song(GenId.getSongIdId(), name, LocalDate.now(), duration, genre, singer);

                    songService.create(song);
                    System.out.println("Песня создана!");
                }

                case 4 -> playListService.getAll();

                case 5 -> singerService.getAll();

                case 6 -> songService.getAll();

                case 7 -> {
                    System.out.println("Выход...");
                    return;
                }

                default -> System.out.println("Нет такого пункта!");
            }
        }
    }


    public static int safeInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Введите число!");
            }
        }
    }

    public static Long safeLong(Scanner sc) {
        while (true) {
            try {
                return Long.parseLong(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Введите корректный Long ID!");
            }
        }
    }

    public static String safeString(Scanner sc) {
        while (true) {
            String s = sc.nextLine();
            if (!s.isBlank()) return s;
            System.out.println("Поле не может быть пустым!");
        }
    }

    public static Gender safeGender(Scanner sc) {
        while (true) {
            try {
                return Gender.valueOf(sc.nextLine().toUpperCase());
            } catch (Exception e) {
                System.out.println("Введите MALE или FEMALE!");
            }
        }
    }

    public static Genre safeGenre(Scanner sc) {
        while (true) {
            try {
                return Genre.valueOf(sc.nextLine().toUpperCase());
            } catch (Exception e) {
                System.out.println("Введите RAP/ROCK/JAZZ/CLASSIC!");
            }
        }
    }



}