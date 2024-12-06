package com.example.backend_album_discovery.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
public class Album {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String title;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AlbumPhoto> photos;

    @Override
    public String toString() {
        return "Album{id=" + id + ", title='" + title + "'}";
    }

}
