package com.fourth.musicBackup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MusicBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicBackupApplication.class, args);
	}
}
