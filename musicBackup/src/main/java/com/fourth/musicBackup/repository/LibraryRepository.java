package com.fourth.musicBackup.repository;

import com.fourth.musicBackup.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {


	

}
