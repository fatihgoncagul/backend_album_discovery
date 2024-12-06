package com.example.backend_album_discovery.service;


import com.example.backend_album_discovery.dto.album.AlbumDto;
import com.example.backend_album_discovery.dto.album.AlbumPhotoDto;
import com.example.backend_album_discovery.dto.user.PhotoDto;
import com.example.backend_album_discovery.dto.user.UserDto;
import com.example.backend_album_discovery.mapper.AppMapper;
import com.example.backend_album_discovery.model.Album;
import com.example.backend_album_discovery.model.AlbumPhoto;
import com.example.backend_album_discovery.model.Config;
import com.example.backend_album_discovery.model.User;
import com.example.backend_album_discovery.repository.AlbumPhotoRepository;
import com.example.backend_album_discovery.repository.AlbumRepository;
import com.example.backend_album_discovery.repository.ConfigRepository;
import com.example.backend_album_discovery.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AppService {





    private final TransactionService transactionService;
    private final ConfigRepository configRepository;

    public AppService(TransactionService transactionService, ConfigRepository configRepository) {
        this.transactionService = transactionService;
        this.configRepository = configRepository;
    }


    public void fetchAndSaveUsersWithPhotosAndAlbums() {

        boolean isUploaded = configRepository.findByKey("isUploaded")
                .map(Config::getValue)
                .orElse(false);

        if (isUploaded) {
            System.out.println("Data is already uploaded. Skipping fetch process.");
            return;
        }

        transactionService.fetchAndSaveData();


        // Fetch işlemi başarılı olursa isUploaded = true olarak işaretle
        Config config = configRepository.findByKey("isUploaded")
               .orElse(new Config(null, "isUploaded", false));
       config.setValue(true);
        configRepository.save(config);


    }







}

