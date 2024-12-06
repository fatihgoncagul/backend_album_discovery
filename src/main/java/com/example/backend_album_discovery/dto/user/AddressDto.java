package com.example.backend_album_discovery.dto.user;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;
}
