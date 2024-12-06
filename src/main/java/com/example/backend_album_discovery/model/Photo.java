package com.example.backend_album_discovery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {


    private Long photoId;
    private String download_url;


}
