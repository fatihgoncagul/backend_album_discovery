package com.example.backend_album_discovery.repository;

import com.example.backend_album_discovery.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<List<Album>> findByUserId(Long userId);


}
