package com.example.backend_album_discovery.dto.user;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressDto address;
    private CompanyDto company;

}
