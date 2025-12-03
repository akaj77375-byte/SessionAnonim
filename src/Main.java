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
        PlayListService playlistService = new PlayListServiceImpl();
        SongService songService = new SongServiceImpl();
        SingerService singerService = new SingerServiceImpl();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                    MENU:
                    1) Playlist
                    2) Song
                    3) Singer
                    4) Exit
                    Выберите категорию:""");
            int mainChoice = safeInt(sc);

            switch (mainChoice) {
                case 1 -> playlistMenu(sc, playlistService, songService);
                case 2 -> songMenu(sc, songService, singerService, playlistService);
                case 3 -> singerMenu(sc, singerService, songService);
                case 4 -> {
                    System.out.println("Выход...");
                    isRunning = false;
                }
                default -> System.out.println("Нет такого пункта!");
            }
        }
    }

    private static void playlistMenu(Scanner sc, PlayListService playlistService, SongService songService) {
        boolean playlistMenu = true;
        while (playlistMenu) {
            System.out.println("""
                    Playlist Menu:
                    1) Create Playlist
                    2) Get All Playlists
                    3) Get Playlist By ID
                    4) Update Playlist
                    5) Delete Playlist
                    6) Get All Songs By Playlist ID
                    7) Delete Song From Playlist
                    8) Sort Songs By Added Date
                    9) Filter Songs By Genre
                    0) Back""");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите название плейлиста: ");
                        String title = safeString(sc);
                        System.out.print("Введите ссылку на изображение: ");
                        String image = safeString(sc);
                        playlistService.create(new Playlist(GenId.getPlaylistsId(), title, image, new ArrayList<>()));
                        System.out.println("Playlist создан!");
                        valid = true;
                    }
                }
                case "2" -> System.out.println(playlistService.getAll());
                case "3" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите ID плейлиста: ");
                        Long id = safeLong(sc);
                        Playlist pl = playlistService.getById(id);
                        if (pl != null) System.out.println(pl);
                        valid = true;
                    }
                }
                case "4" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите ID плейлиста для обновления: ");
                        Long id = safeLong(sc);
                        System.out.print("Введите новое название: ");
                        String title = safeString(sc);
                        System.out.print("Введите новую ссылку на изображение: ");
                        String image = safeString(sc);
                        Playlist p = new Playlist();
                        p.setTitle(title);
                        p.setImageUrl(image);
                        playlistService.update(id, p);
                        System.out.println("Playlist обновлён!");
                        valid = true;
                    }
                }
                case "5" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите ID плейлиста для удаления: ");
                        Long id = safeLong(sc);
                        playlistService.delete(id);
                        System.out.println("Playlist удалён!");
                        valid = true;
                    }
                }
                case "6" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите ID плейлиста: ");
                        Long id = safeLong(sc);
                        System.out.println(playlistService.getAllSongsByPlaylistId(id));
                        valid = true;
                    }
                }
                case "7" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите ID плейлиста: ");
                        Long pid = safeLong(sc);
                        System.out.print("Введите ID песни: ");
                        Long sid = safeLong(sc);
                        playlistService.deleteSongFromPlaylist(pid, sid);
                        System.out.println("Песня удалена из плейлиста!");
                        valid = true;
                    }
                }
                case "8" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите asc или desc: ");
                        String order = sc.nextLine().toLowerCase();
                        if (order.equals("asc") || order.equals("desc")) {
                            playlistService.sortSongByAddedDate(order);
                            System.out.println("Сортировка выполнена!");
                            valid = true;
                        } else {
                            System.out.println("Ошибка: введите asc или desc.");
                        }
                    }
                }
                case "9" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите жанр (ROCK, POP, JAZZ, DISCO): ");
                        try {
                            Genre genre = Genre.valueOf(sc.nextLine().toUpperCase());
                            System.out.println(playlistService.filterSongByGenre(genre));
                            valid = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: неверный жанр.");
                        }
                    }
                }
                case "0" -> playlistMenu = false;
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void songMenu(Scanner sc, SongService songService, SingerService singerService, PlayListService playlistService) {
        boolean songMenu = true;
        while (songMenu) {
            System.out.println("""
                    Song Menu:
                    1) Create Song
                    2) Get All Songs
                    3) Get Song By ID
                    4) Update Song
                    5) Delete Song
                    6) Assign Song To Playlist
                    0) Back""");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите название песни: ");
                        String name = safeString(sc);
                        System.out.print("Введите длительность: ");
                        String duration = safeString(sc);
                        System.out.print("Введите жанр (ROCK, POP, JAZZ, DISCO): ");
                        Genre genre = safeGenre(sc);
                        System.out.print("Введите ID певца: ");
                        Long singerId = safeLong(sc);
                        Singer singer = singerService.getById(singerId);
                        if (singer == null) {
                            System.out.println("Певец не найден!");
                            continue;
                        }
                        Song song = new Song(GenId.getSongIdId(), name, LocalDate.now(), duration, genre, singer);
                        songService.create(song);
                        System.out.println("Песня создана!");
                        valid = true;
                    }
                }
                case "2" -> System.out.println(songService.getAll());
                case "0" -> songMenu = false;
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void singerMenu(Scanner sc, SingerService singerService, SongService songService) {
        boolean singerMenu = true;
        while (singerMenu) {
            System.out.println("""
                    Singer Menu:
                    1) Create Singer
                    2) Get All Singers
                    3) Get Singer By ID
                    4) Update Singer
                    5) Delete Singer
                    6) Get All Songs By Singer ID
                    7) Filter By Gender
                    0) Back""");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    boolean valid = false;
                    while (!valid) {
                        System.out.print("Введите имя: ");
                        String firstName = safeString(sc);
                        System.out.print("Введите фамилию: ");
                        String lastName = safeString(sc);
                        System.out.print("Введите пол (MALE/FEMALE): ");
                        Gender gender = safeGender(sc);
                        Singer singer = new Singer(GenId.getSingerIdId(), firstName, lastName, gender, new ArrayList<>());
                        singerService.create(singer);
                        System.out.println("Певец создан!");
                        valid = true;
                    }
                }
                case "2" -> System.out.println(singerService.getAll());
                case "0" -> singerMenu = false;
                default -> System.out.println("Неверный выбор!");
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
                System.out.println("Введите ROCK/POP/JAZZ/DISCO!");
            }
        }
    }


}