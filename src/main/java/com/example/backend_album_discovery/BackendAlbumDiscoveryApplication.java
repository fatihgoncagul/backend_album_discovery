package com.example.backend_album_discovery;

import com.example.backend_album_discovery.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendAlbumDiscoveryApplication {

    public  final AppService appService;

    public BackendAlbumDiscoveryApplication(AppService appService) {
        this.appService = appService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendAlbumDiscoveryApplication.class, args);
    }

}
