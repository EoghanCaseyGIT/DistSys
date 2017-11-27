package com.fourth.musicBackup.model;

import java.io.Serializable;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.*;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)




public class User implements Serializable {

	@OneToOne
	private Library library;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}



}
