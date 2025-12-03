package models;

import enums.Genre;

import java.time.LocalDate;

public class Song {
    private Long id;
    private String name;
    private LocalDate addedDate;
    private String duration;
    private Genre genre;
    private Singer singer;

    public Song() {
    }

    public Song(Long id, String name, LocalDate addedDate, String duration, Genre genre, Singer singer) {
        this.id = id;
        this.name = name;
        this.addedDate = addedDate;
        this.duration = duration;
        this.genre = genre;
        this.singer = singer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addedDate=" + addedDate +
                ", duration='" + duration + '\'' +
                ", genre=" + genre +
                ", singer=" + singer +
                '}';
    }
}
