package com.beesidk.projet.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("video")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video {
    @Id
    String id;
    String titre;
    String description;
    String videoUrl;
    String thumbnailUrl;
    Long vues = 0L;
    int duree;
    int nbreLike = 0;
    int nbreDisLike = 0;

    LocalDateTime dateAjout;

    @DBRef
    List<Tag> tags = new ArrayList<>();

    @DBRef
    List<Avis> listAvis = new ArrayList<>();

    @DBRef
    Formateur formateur;

    @DBRef
    Categorie categorie;

}
