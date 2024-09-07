package com.beesidk.projet.entity;


import com.beesidk.projet.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUser {

    @Id
    String id;
    String name;
    String email;
    String password;
    String photo;
    int phoneNumber;
    Genre genre;


    @DBRef(lazy = true)
    List<AppRole> roles = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnore
    @ToStringExclude
    List<Inscription> inscriptions = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnore
    @ToStringExclude
    List<Avis> avis = new ArrayList<>();
}
