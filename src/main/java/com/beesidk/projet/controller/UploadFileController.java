package com.beesidk.projet.controller;

import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Video;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.service.fileService.CourAfficheFileService;
import com.beesidk.projet.service.fileService.CourVideoFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/upload")
public class UploadFileController {

    private CourAfficheFileService courFile;
    private CourVideoFileService courVideoFile;
    private FileService<AppUser> userFile;
    private FileService<Video> videoFile;

    // Upload affiche photo
    @PostMapping("/UserPhoto/{id}")
    public void uploadPhotoUser(@RequestParam("file") MultipartFile file, @PathVariable String id) throws Exception {
        userFile.uploadFile(file, id);
    }

    // Upload user photo
    @PostMapping("/AfficheCour/{id}")
    public void uploadCourAffiche(@RequestParam("file") MultipartFile file, @PathVariable String id) throws Exception {
        courFile.uploadFile(file, id);
    }

    // Upload categorie image
    @PostMapping("/CategorieImage/{id}")
    public void uploadCategorieImage(@RequestParam("file") MultipartFile file, @PathVariable String id) throws Exception {
        courFile.uploadFile(file, id);
    }


    // Upload video for VideoClass
    @PostMapping("/Video/{id}")
    public ResponseEntity<Void> uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        try {
            if (file == null || file.isEmpty()) {
                System.out.println("No file provided or file is empty.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            videoFile.uploadFile(file, id);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // Log d'une erreur liée à l'IOException
            System.err.println("IOException during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Log d'une erreur inattendue
            System.err.println("Unexpected error during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }// Upload video for VideoClass

    @PostMapping("/CourVideo/{id}")
    public ResponseEntity<Void> uploadCourVideo(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        try {
            if (file == null || file.isEmpty()) {
                System.out.println("No file provided or file is empty.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            courVideoFile.uploadFile(file, id);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // Log d'une erreur liée à l'IOException
            System.err.println("IOException during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Log d'une erreur inattendue
            System.err.println("Unexpected error during file upload: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
