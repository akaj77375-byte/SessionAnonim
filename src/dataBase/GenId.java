package dataBase;

import models.Playlist;

import java.util.List;

public class GenId {
    public static Long playlistsId= 0L;
    public static Long singerId= 0L;
    public static Long songId= 0L;

     public static Long getPlaylistsId(){
return ++playlistsId;
    }
    public static Long getSingerIdId(){
return ++singerId;
    } public static Long getSongIdId(){
return ++songId;
    }
}
