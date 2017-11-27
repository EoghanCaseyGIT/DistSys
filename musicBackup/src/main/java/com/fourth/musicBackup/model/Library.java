package com.fourth.musicBackup.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "library")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
		allowGetters = true)

public class Library implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Song> songs;

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Playlist> playlists;

//
	private User user;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	
	

}
