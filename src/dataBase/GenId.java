package dataBase;

import models.Playlist;

import java.util.List;

public class GenId {
    public static Long playlistsId=0l;
    public static Long singerId=0l;
    public static Long songId=0l;
     public static Long getPlaylistsId(){
return playlistsId++;
    }
    public static Long getSingerIdId(){
return singerId++;
    } public static Long getSongIdId(){
return songId++;
    }
}
