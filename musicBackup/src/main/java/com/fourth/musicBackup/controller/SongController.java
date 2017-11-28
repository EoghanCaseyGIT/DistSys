package com.fourth.musicBackup.controller;

import com.fourth.musicBackup.model.Song;
import com.fourth.musicBackup.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongRepository songRepository;

    //Get all users
    @GetMapping("/viewSongs")
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    //create new user
    @PostMapping("/songs")
    public Song createSongs(@Valid @RequestBody Song song){
        return songRepository.save(song);
    }

    @GetMapping( "/songs/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable(value = "id") Long songId) {
        Song song = songRepository.findOne(songId);
        if(song == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(song);
    }

    //update song
    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable(value = "id") Long songId,
                                                 @Valid @RequestBody Song songDetails){
        Song song = songRepository.findOne(songId);
        if(song == null) {
            return ResponseEntity.notFound().build();
        }
        song.setId(song.getId());


        Song updatedSong = songRepository.save(song);
        return ResponseEntity.ok(updatedSong);
    }

    //delete song

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable(value = "id") Long songId){
        Song song  = songRepository.findOne(songId);
        if(song == null){
            return ResponseEntity.notFound().build();

        }

        songRepository.delete(song);
        return ResponseEntity.ok().build();
    }





}
