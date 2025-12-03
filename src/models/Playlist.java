package models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private Long id;
    private String title;
    private String imageUrl;
    private List<Song> songs;

   

    public void PlayList() {
    }

    public void PlayList(Long id, String title, String imageUrl, List<Song> songs) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.songs = songs;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", songs=" + songs +
                '}';
    }
}

