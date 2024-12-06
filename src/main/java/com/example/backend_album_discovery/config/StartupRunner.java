package com.example.backend_album_discovery.config;

import com.example.backend_album_discovery.service.AppService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    private final AppService appService;

    public StartupRunner(AppService appService) {
        this.appService = appService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        appService.fetchAndSaveUsersWithPhotosAndAlbums();
    }
}
