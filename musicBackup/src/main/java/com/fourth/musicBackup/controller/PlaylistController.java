package com.fourth.musicBackup.controller;

import com.fourth.musicBackup.model.Playlist;
import com.fourth.musicBackup.repository.playlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
//@RequestMapping("/api")
public class PlaylistController {

    @Autowired
    playlistRepository playlistRepository;

    //Get all users
    @GetMapping("/viewPlaylists")
    public List<Playlist> getAllPlaylists(){
        return playlistRepository.findAll();
    }

    //create new user
    @PostMapping("/playlists")
    public Playlist createPlaylists(@Valid @RequestBody Playlist playlist){
        return playlistRepository.save(playlist);
    }

    //get single user
    @GetMapping( "/playlists/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable(value = "id") Long playlistId) {
        Playlist playlist = playlistRepository.findOne(playlistId);
        if(playlist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(playlist);
    }

    //update User
    @PutMapping("/playlists/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable(value = "id") Long playlistId,
                                           @Valid @RequestBody Playlist playlistDetails){
        Playlist playlist = playlistRepository.findOne(playlistId);
        if(playlist == null) {
            return ResponseEntity.notFound().build();
        }
        playlist.setName(playlist.getName());


        Playlist updatedPlaylist = playlistRepository.save(playlist);
        return ResponseEntity.ok(updatedPlaylist);
    }

    //delete user

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Playlist> deletePlaylist(@PathVariable(value = "id") Long playlistId){
        Playlist playlist = playlistRepository.findOne(playlistId);
        if(playlist == null){
            return ResponseEntity.notFound().build();

        }

        playlistRepository.delete(playlist);
        return ResponseEntity.ok().build();
    }



}
