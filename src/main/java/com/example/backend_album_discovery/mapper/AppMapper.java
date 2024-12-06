package com.example.backend_album_discovery.mapper;

import com.example.backend_album_discovery.dto.album.AlbumDto;
import com.example.backend_album_discovery.dto.album.AlbumPhotoDto;
import com.example.backend_album_discovery.dto.user.PhotoDto;
import com.example.backend_album_discovery.dto.user.UserDto;
import com.example.backend_album_discovery.model.*;
import org.springframework.stereotype.Component;



@Component
public class AppMapper {

    public User toEntity(UserDto userDto,  PhotoDto photoDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .website(userDto.getWebsite())
                .address(new Address(
                        userDto.getAddress().getStreet(),
                        userDto.getAddress().getSuite(),
                        userDto.getAddress().getCity(),
                        userDto.getAddress().getZipcode(),new Geo(userDto.getAddress().getGeo().getLat(),userDto.getAddress().getGeo().getLng())))
                .company(new Company(
                        userDto.getCompany().getName(),
                        userDto.getCompany().getCatchPhrase(),
                        userDto.getCompany().getBs()))
                .photo(new Photo(photoDto.getId(), photoDto.getDownload_url()))
                .build();
    }
    public Album toEntity(AlbumDto albumDto) {
        return Album.builder()
                .id(albumDto.getId())
                .title(albumDto.getTitle())
                .build();
    }

    public AlbumPhoto toEntity(AlbumPhotoDto albumPhotoDto) {
        return AlbumPhoto.builder()
               .id(albumPhotoDto.getId())
                .title(albumPhotoDto.getTitle())
                .url(albumPhotoDto.getUrl())
                .thumbnailUrl(albumPhotoDto.getThumbnailUrl())
                .build();
    }

}
