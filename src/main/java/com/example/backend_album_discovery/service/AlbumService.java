package com.example.backend_album_discovery.service;

import com.example.backend_album_discovery.model.Album;
import com.example.backend_album_discovery.model.AlbumPhoto;
import com.example.backend_album_discovery.repository.AlbumPhotoRepository;
import com.example.backend_album_discovery.repository.AlbumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumPhotoRepository albumPhotoRepository;

    public AlbumService(AlbumRepository albumRepository, AlbumPhotoRepository albumPhotoRepository) {
        this.albumRepository = albumRepository;
        this.albumPhotoRepository = albumPhotoRepository;
    }

    public ResponseEntity<List<Album>> getAlbumsByUserId(Long userId) {

       Optional<List<Album>> albums = albumRepository.findByUserId(userId);

       if (albums.isPresent()) {
           return new ResponseEntity<>(albums.get(),HttpStatus.OK );
       }

       return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<AlbumPhoto>> getAlbumPhotosByAlbumId(Long albumId) {

       Optional<List<AlbumPhoto>> albumPhotos=  albumPhotoRepository.findAlbumPhotoByAlbumId(albumId);

       if (albumPhotos.isPresent()) {
           return new ResponseEntity<>(albumPhotos.get(),HttpStatus.OK );
       }

       return ResponseEntity.notFound().build();

    }
}
