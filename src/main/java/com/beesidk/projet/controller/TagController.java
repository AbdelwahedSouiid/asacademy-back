package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Tag;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.interfaces.ITagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/tag")
public class TagController {


    private ITagService TagService;


    // http://localhost:8089/projet/Tag/retrieve-all-Tag
    @Operation(description = "Ce web service permet de récupérer toutes les Tags de la base de données")
    @GetMapping("/retrieve-all-Tag")
    public List<Tag> getTags() {
        List<Tag> listTags = TagService.retrieveAll();

        return listTags;
    }

    // http://localhost:8089/projet/Tag/retrieve-Tag/8
    @GetMapping("/retrieve-Tag/{Tag-id}")
    public Tag retrieveTag(@PathVariable("Tag-id") String id) {
        Tag Tag = TagService.retrieve(id);
        return Tag;
    }

    // http://localhost:8089/projet/Tag/add-Tag
    @PostMapping("/add-Tag")
    public Tag addTag(@RequestBody Tag c) {

        Tag Tag = TagService.add(c);
        return Tag;
    }

    // http://localhost:8089/projet/Tag/remove-Tag/{Tag-id}
    @DeleteMapping("/remove-Tag/{Tag-id}")
    public void removeTag(@PathVariable("Tag-id") String id) {
        TagService.remove(id);
    }

    // http://localhost:8089/projet/Tag/modify-Tag
    @PutMapping("/modify-Tag")
    public ResponseEntity<?> modifyTag(@RequestBody Tag c) {

        try {
            Tag Tag = TagService.modify(c);
            return ResponseEntity.ok(Tag);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating cour: " + e.getMessage());
        }
    }

    // http://localhost:8089/projet/Tag/retrieve-Tag-By-Name/#tag 2
    @GetMapping("/retrieve-Tag-By-Name/{TagName}")
    public List<Tag> retrieveTagByName(@PathVariable("TagName") String TagName) {
        return TagService.retrieveByTag(TagName);
    }

}
