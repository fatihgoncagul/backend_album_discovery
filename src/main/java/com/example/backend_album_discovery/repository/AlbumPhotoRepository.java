package com.example.backend_album_discovery.repository;

import com.example.backend_album_discovery.model.AlbumPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumPhotoRepository extends JpaRepository<AlbumPhoto, Long> {
    Optional<List<AlbumPhoto>> findAlbumPhotoByAlbumId(Long albumId);
}

