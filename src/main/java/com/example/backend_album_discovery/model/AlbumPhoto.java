package com.example.backend_album_discovery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album_photos")
public class AlbumPhoto {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "album_photo_title")
    private String title;
    private String url;
    private String thumbnailUrl;

}
