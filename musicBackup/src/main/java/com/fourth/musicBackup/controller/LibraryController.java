package com.fourth.musicBackup.controller;

import com.fourth.musicBackup.model.Library;
import com.fourth.musicBackup.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    //Get all users
    @GetMapping("/libraries")
    public List<Library> getAllLibraries(){
        return libraryRepository.findAll();
    }

    //create new user
    @PostMapping("/libraries")
    public Library createLibraries(@Valid @RequestBody Library library){
        return libraryRepository.save(library);
    }

    //get single user
    @GetMapping( "/libraries/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable(value = "id") Long libraryId) {
        Library library = libraryRepository.findOne(libraryId);
        if(library == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(library);
    }

    //update User
    @PutMapping("/libraries/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable(value = "id") Long libraryId,
                                                   @Valid @RequestBody Library libraryDetails){
        Library library = libraryRepository.findOne(libraryId);
        if(library == null) {
            return ResponseEntity.notFound().build();
        }
        library.setId(library.getId());


        Library updatedLibrary = libraryRepository.save(library);
        return ResponseEntity.ok(updatedLibrary);
    }

    //delete user

    @DeleteMapping("/libraries/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable(value = "id") Long libraryId){
        Library library = libraryRepository.findOne(libraryId);
        if(library == null){
            return ResponseEntity.notFound().build();

        }

        libraryRepository.delete(library);
        return ResponseEntity.ok().build();
    }



}
