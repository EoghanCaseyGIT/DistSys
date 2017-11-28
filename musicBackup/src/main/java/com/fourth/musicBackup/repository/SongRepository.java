package com.fourth.musicBackup.repository;

import com.fourth.musicBackup.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


	

}
