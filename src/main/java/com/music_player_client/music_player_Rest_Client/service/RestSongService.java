package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestSongService implements IRestSongService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;

    public List<Song> getAllSongs() {
        String HTTP_REQUEST_GET_ALL_SONGS = host + port + "/song/";
        Song[] songs = restTemplate.getForObject(HTTP_REQUEST_GET_ALL_SONGS, Song[].class);
        return Arrays.asList(songs);
    }

    public Song findById(Long song_id) {
        String HTTP_REQUEST_GET_SONG_BY_ID = host + port + "/song/getSong/{song_id}";
        return restTemplate.getForObject(HTTP_REQUEST_GET_SONG_BY_ID, Song.class, song_id);
    }

    @Override
    public byte[] getFile(String songName, String fileType, String storageType) {
        String HTTP_REQUEST_GET_FILE_IN_ARRAY
                = host + port + "/song/file/{name}?file_type={file_type}&storage_type={storage_type}";
        byte[] songFile = restTemplate.getForObject(HTTP_REQUEST_GET_FILE_IN_ARRAY
                , byte[].class
                , songName
                , fileType
                , storageType);
        return songFile;
    }
}

