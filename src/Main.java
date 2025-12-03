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
                    Выберите категорию:
                    1) Playlist
                    2) Song
                    3) Singer
                    4) Exit
                    """);
            String mainChoice = sc.nextLine();

            switch (mainChoice) {
                case "1" -> {
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
                                0) Back
                                """);
                        String choice = sc.nextLine();
                        switch (choice) {
                            case "1" -> {
                                Playlist p;
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите название плейлиста: ");
                                    String title = sc.nextLine();
                                    System.out.print("Введите ссылку на изображение: ");
                                    String image = sc.nextLine();
                                    if (title.isBlank() || image.isBlank()) {
                                        System.out.println("Ошибка: Название и ссылка не могут быть пустыми.");
                                    } else {

                                        playlistService.create(new Playlist(GenId.getPlaylistsId(),title,image,new ArrayList<Song>() ));
                                        System.out.println("Playlist создан!");
                                        valid = true;
                                    }
                                }
                            }
                            case "2" -> System.out.println(playlistService.getAll());
                            case "3" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите ID плейлиста: ");
                                    String input = sc.nextLine();
                                    if (input.matches("\\d+")) {
                                        Long id = Long.parseLong(input);
                                        Playlist pl = playlistService.getById(id);
                                        if (pl != null) System.out.println(pl);
                                        valid = true;
                                    } else {
                                        System.out.println("Ошибка: Введите число.");
                                    }
                                }
                            }
                            case "4" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите ID плейлиста для обновления: ");
                                    String idInput = sc.nextLine();
                                    System.out.print("Введите новое название: ");
                                    String title = sc.nextLine();
                                    System.out.print("Введите новую ссылку на изображение: ");
                                    String image = sc.nextLine();
                                    if (!idInput.matches("\\d+") || title.isBlank() || image.isBlank()) {
                                        System.out.println("Ошибка: неверные данные.");
                                    } else {
                                        Long id = Long.parseLong(idInput);
                                        Playlist p = new Playlist();
                                        p.setTitle(title);
                                        p.setImageUrl(image);
                                        playlistService.update(id, p);
                                        System.out.println("Playlist обновлён!");
                                        valid = true;
                                    }
                                }
                            }
                            case "5" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите ID плейлиста для удаления: ");
                                    String input = sc.nextLine();
                                    if (input.matches("\\d+")) {
                                        Long id = Long.parseLong(input);
                                        playlistService.delete(id);
                                        System.out.println("Playlist удалён!");
                                        valid = true;
                                    } else {
                                        System.out.println("Ошибка: Введите число.");
                                    }
                                }
                            }
                            case "6" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите ID плейлиста: ");
                                    String input = sc.nextLine();
                                    if (input.matches("\\d+")) {
                                        Long id = Long.parseLong(input);
                                        System.out.println(playlistService.getAllSongsByPlaylistId(id));
                                        valid = true;
                                    } else {
                                        System.out.println("Ошибка: Введите число.");
                                    }
                                }
                            }
                            case "7" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите ID плейлиста: ");
                                    String pidInput = sc.nextLine();
                                    System.out.print("Введите ID песни: ");
                                    String sidInput = sc.nextLine();
                                    if (pidInput.matches("\\d+") && sidInput.matches("\\d+")) {
                                        Long pid = Long.parseLong(pidInput);
                                        Long sid = Long.parseLong(sidInput);
                                        playlistService.deleteSongFromPlaylist(pid, sid);
                                        System.out.println("Песня удалена из плейлиста!");
                                        valid = true;
                                    } else {
                                        System.out.println("Ошибка: Введите числа.");
                                    }
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
                                    String genreInput = sc.nextLine().toUpperCase();
                                    try {
                                        Genre genre = Genre.valueOf(genreInput);
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

                case "2" -> {
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
                                0) Back
                                """);
                        String choice = sc.nextLine();
                        switch (choice) {
                            case "1" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите название песни: ");
                                    String name = sc.nextLine();
                                    System.out.print("Введите длительность: ");
                                    String duration = sc.nextLine();
                                    System.out.print("Введите жанр (ROCK, POP, JAZZ, DISCO): ");
                                    String genreInput = sc.nextLine().toUpperCase();
                                    System.out.print("Введите ID певца: ");
                                    String singerIdInput = sc.nextLine();

                                    if (name.isBlank() || duration.isBlank() || !singerIdInput.matches("\\d+")) {
                                        System.out.println("Ошибка: неверные данные.");
                                        continue;
                                    }

                                    try {
                                        Genre genre = Genre.valueOf(genreInput);
                                        Long singerId = Long.parseLong(singerIdInput);
                                        Singer singer = singerService.getById(singerId);
                                        if (singer == null) {
                                            System.out.println("Ошибка: певец не найден.");
                                            continue;
                                        }
                                        Song song = new Song();
                                        song.setName(name);
                                        song.setDuration(duration);
                                        song.setGenre(genre);
                                        song.setSinger(singer);
                                        songService.create(song);
                                        System.out.println("Песня создана!");
                                        valid = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Ошибка: неверный жанр.");
                                    }
                                }
                            }
                            case "2" -> System.out.println(songService.getAll());
                            case "0" -> songMenu = false;
                            default -> System.out.println("Неверный выбор!");
                        }
                    }
                }

                case "3" -> {
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
                                0) Back
                                """);
                        String choice = sc.nextLine();
                        switch (choice) {
                            case "1" -> {
                                boolean valid = false;
                                while (!valid) {
                                    System.out.print("Введите имя: ");
                                    String firstName = sc.nextLine();
                                    System.out.print("Введите фамилию: ");
                                    String lastName = sc.nextLine();
                                    System.out.print("Введите пол (MALE/FEMALE): ");
                                    String genderInput = sc.nextLine().toUpperCase();

                                    if (firstName.isBlank() || lastName.isBlank()) {
                                        System.out.println("Ошибка: имя и фамилия не могут быть пустыми.");
                                        continue;
                                    }
                                    try {
                                        Gender gender = Gender.valueOf(genderInput);
                                        Singer singer = new Singer();
                                        singer.setFirstName(firstName);
                                        singer.setLastName(lastName);
                                        singer.setGender(gender);
                                        singerService.create(singer);
                                        System.out.println("Певец создан!");
                                        valid = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Ошибка: неверный пол.");
                                    }
                                }
                            }
                            case "2" -> System.out.println(singerService.getAll());
                            case "0" -> singerMenu = false;
                            default -> System.out.println("Неверный выбор!");
                        }
                    }
                }

                case "4" -> isRunning = false;
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }



}