package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Video;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.interfaces.IVideoService;
import com.beesidk.projet.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final com.beesidk.projet.service.VideoService videoService;
    private IVideoService VideoService;


    // http://localhost:8089/projet/Video/retrieve-all-Video
    @Operation(description = "Ce web service permet de récupérer toutes les Videos de la base de données")
    @GetMapping("/retrieve-all-Video")
    public List<Video> getVideos() {
        List<Video> listVideos = VideoService.retrieveAll();

        return listVideos;
    }

    // http://localhost:8089/projet/Video/retrieve-Video/8
    @GetMapping("/retrieve-Video/{Video-id}")
    public Video retrieveVideo(@PathVariable("Video-id") String id) {
        Video Video = VideoService.retrieve(id);
        return Video;
    }

    // http://localhost:8089/projet/Video/add-Video
    @PostMapping("/add-Video")
    public Video addVideo(@RequestBody Video c) {
        Video Video = VideoService.add(c);
        return Video;
    }

    // http://localhost:8089/projet/Video/remove-Video/{Video-id}
    @DeleteMapping("/remove-Video/{Video-id}")
    public void removeVideo(@PathVariable("Video-id") String id) {
        VideoService.remove(id);
    }

    // http://localhost:8089/projet/Video/modify-Video
    @PutMapping("/modify-Video")
    public ResponseEntity<?> modifyVideo(@RequestBody Video c) {

        try {
            Video Video = VideoService.modify(c);
            return ResponseEntity.ok(Video);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating cour: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Video>> search(@RequestParam(value = "titre", required = false) String titre,
                                              @RequestParam(value = "category", required = false) String category) {
        List<Video> videos = videoService.search(titre, category);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}
