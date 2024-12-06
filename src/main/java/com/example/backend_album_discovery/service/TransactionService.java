package com.example.backend_album_discovery.service;

import com.example.backend_album_discovery.dto.album.AlbumDto;
import com.example.backend_album_discovery.dto.album.AlbumPhotoDto;
import com.example.backend_album_discovery.dto.user.PhotoDto;
import com.example.backend_album_discovery.dto.user.UserDto;
import com.example.backend_album_discovery.mapper.AppMapper;
import com.example.backend_album_discovery.model.Album;
import com.example.backend_album_discovery.model.AlbumPhoto;
import com.example.backend_album_discovery.model.User;
import com.example.backend_album_discovery.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final AppMapper appMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TransactionService(UserRepository userRepository, AppMapper appMapper, RestTemplate restTemplate, ObjectMapper objectMapper, EntityManager entityManager, EntityManager entityManager1) {
        this.userRepository = userRepository;
        this.appMapper = appMapper;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    @Transactional
    public void fetchAndSaveData() {
        try {
            String userUrl = "https://jsonplaceholder.typicode.com/users";
            String albumUrl = "https://jsonplaceholder.typicode.com/albums";
            String photoBaseUrl = "https://picsum.photos/id/%d/info";
            String albumPhotoUrl = "https://jsonplaceholder.typicode.com/photos";

            List<UserDto> userDtos = fetchFromUrl(userUrl, new TypeReference<>() {
            });
            List<AlbumDto> albumDtos = fetchFromUrl(albumUrl, new TypeReference<>() {
            });
            List<AlbumPhotoDto> albumPhotoDtos = fetchFromUrl(albumPhotoUrl, new TypeReference<>() {
            });


            Map<Long, List<AlbumPhoto>> albumPhotosMap = albumPhotoDtos.stream()
                    .collect(Collectors.groupingBy(
                            AlbumPhotoDto::getAlbumId,
                            Collectors.mapping(appMapper::toEntity, Collectors.toList())
                    ));


            Map<Long, List<Album>> userAlbumsMap = albumDtos.stream()
                    .collect(Collectors.groupingBy(
                            AlbumDto::getUserId,
                            Collectors.mapping(albumDto -> {
                                List<AlbumPhoto> albumPhotos = albumPhotosMap.getOrDefault(albumDto.getId(), new ArrayList<>());
                                Album album = appMapper.toEntity(albumDto);

                                albumPhotos.forEach(photo -> photo.setAlbum(album)); // Connect photos to album
                                album.setPhotos(albumPhotos);

                                return album;
                            }, Collectors.toList())
                    ));

            List<User> users = userDtos.stream()
                    .map(userDto -> {
                        List<Album> albums = userAlbumsMap.getOrDefault(userDto.getId(), new ArrayList<>());
                        PhotoDto photoDto = fetchFromUrl(String.format(photoBaseUrl, userDto.getId()), PhotoDto.class);

                        User user = appMapper.toEntity(userDto, photoDto);
                        albums.forEach(album -> {
                            album.setUser(user);
                        });
                        user.setAlbums(albums);
                        return user;
                    })
                    .toList();

            userRepository.saveAll(users);
            System.out.println("All saved");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private <T> T fetchFromUrl(String url, TypeReference<T> typeReference) {
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(response, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch data from " + url, e);
        }
    }

    private <T> T fetchFromUrl(String url, Class<T> responseType) {
        try {
            return restTemplate.getForObject(url, responseType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch data from " + url, e);
        }
    }

}
