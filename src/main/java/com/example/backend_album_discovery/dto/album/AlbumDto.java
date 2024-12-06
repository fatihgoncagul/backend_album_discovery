package com.example.backend_album_discovery.dto.album;

import lombok.Data;

@Data
public class AlbumDto {

    private Long id;
    private Long userId;
    private String title;
}
