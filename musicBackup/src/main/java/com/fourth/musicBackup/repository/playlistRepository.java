package com.fourth.musicBackup.repository;

import com.fourth.musicBackup.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playlistRepository extends JpaRepository<Playlist, Long> {


	

}
