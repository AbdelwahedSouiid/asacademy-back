package com.beesidk.projet.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {

    @Id
    String id;
    String title;
    String content;

    LocalDateTime dateCreation;


    @DBRef
    List<Tag> tags = new ArrayList<>();

    @DBRef(lazy = true)
    List<Cour> cours;

    Formateur formateur;
}
