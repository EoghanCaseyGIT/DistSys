package com.fourth.musicBackup.model;

import java.io.Serializable;

import javax.persistence.*;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "songs")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)

public class Song implements Serializable {

//    @ManyToMany
//    private Library library;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String trackId;

    @NotBlank
    private String trackName;

    @NotBlank
    private String trackArtist;

    private String albumName;

    @NotBlank
    private String libraryPersID;

    public Song(){

    }

    public Song(String trackId, String trackName, String trackArtist, String albumName, String libraryPersID) {

        this.trackId = trackId;
        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.albumName = albumName;
        this.libraryPersID = libraryPersID;

    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getLibraryPersID() {
        return libraryPersID;
    }

    public void setLibraryPersID(String libraryPersID) {
        this.libraryPersID = libraryPersID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist(String artist) {
        this.trackArtist = artist;
    }
}