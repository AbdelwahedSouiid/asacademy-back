package com.beesidk.projet.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("formateur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Formateur {

    @Id
    String id;

    String nom;
    String prenom;
    String adresse;
    String telephone;
    String email;

    //
    String profesion;
    String description;


    @DBRef(lazy = true)
    @JsonIgnore
    List<Cour> cours;

}
