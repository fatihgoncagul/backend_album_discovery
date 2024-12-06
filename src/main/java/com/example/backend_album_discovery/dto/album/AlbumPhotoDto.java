package com.example.backend_album_discovery.dto.album;

import lombok.Data;

@Data
public class AlbumPhotoDto {
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
