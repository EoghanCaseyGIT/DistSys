package com.fourth.musicBackup.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "playlists")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
		allowGetters = true)

public class Playlist implements Serializable {

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Song> songs;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String playlistPersistenceId;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPlaylistPersistenceId(String playlistPersistenceId) {
		this.playlistPersistenceId = playlistPersistenceId;
	}

    public String getPlaylistPersistenceId() {
        return playlistPersistenceId;
    }
}


