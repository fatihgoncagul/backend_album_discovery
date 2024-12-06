package com.example.backend_album_discovery.controller;

import com.example.backend_album_discovery.model.Album;
import com.example.backend_album_discovery.model.AlbumPhoto;
import com.example.backend_album_discovery.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("album")
public class AlbumController {


    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Album>> getAlbumsByUserId(@PathVariable Long userId) {

        return albumService.getAlbumsByUserId(userId);

    }

    @GetMapping("{albumId}/photos")
    public ResponseEntity<List<AlbumPhoto>> getAlbumPhotosByAlbumId(@PathVariable Long albumId) {

        return albumService.getAlbumPhotosByAlbumId(albumId);

    }

}
