package com.beesidk.projet.controller;


import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Video;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.service.fileService.CourAfficheFileService;
import com.beesidk.projet.service.fileService.CourVideoFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/load")
public class LoadFileController {

    private CourAfficheFileService courFileService;
    private CourVideoFileService courVideoFileService;
    private FileService<AppUser> userFileService;
    private FileService<Video> videoFileService;

    @GetMapping(value = "/AfficheCour/{affiche}")
    public ResponseEntity<byte[]> getAffiche(@PathVariable("affiche") String affiche) throws Exception {
        byte[] imageBytes = courFileService.loadFile(affiche);

        // Determine the media type based on the file extension
        String mediaType;
        if (affiche.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG_VALUE;
        } else if (affiche.toLowerCase().endsWith(".jpeg") || affiche.toLowerCase().endsWith(".jpg")) {
            mediaType = MediaType.IMAGE_JPEG_VALUE;
        } else {
            // Return 415 Unsupported Media Type if the file extension is not supported
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(imageBytes);
    }

    @GetMapping(value = "/PhotoUser/{image}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("image") String image) throws Exception {
        byte[] imageBytes = userFileService.loadFile(image);

        String mediaType;
        if (image.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG_VALUE;
        } else if (image.toLowerCase().endsWith(".jpeg") || image.toLowerCase().endsWith(".jpg")) {
            mediaType = MediaType.IMAGE_JPEG_VALUE;
        } else {
            // Return 415 Unsupported Media Type if the file extension is not supported
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(imageBytes);
    }


    @GetMapping(value = "/CategorieImage/{image}")
    public ResponseEntity<byte[]> getCategorieImage(@PathVariable("image") String image) throws Exception {
        byte[] imageBytes = userFileService.loadFile(image);

        String mediaType;
        if (image.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG_VALUE;
        } else if (image.toLowerCase().endsWith(".jpeg") || image.toLowerCase().endsWith(".jpg")) {
            mediaType = MediaType.IMAGE_JPEG_VALUE;
        } else {
            // Return 415 Unsupported Media Type if the file extension is not supported
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(imageBytes);
    }

    @GetMapping(value = "/video/{filename}")
    public ResponseEntity<byte[]> getVideo(@PathVariable("filename") String filename) {
        try {
            byte[] videoBytes = videoFileService.loadFile(filename);

            String mediaType;
            if (filename.toLowerCase().endsWith(".mp4")) {
                mediaType = "video/mp4";
            } else if (filename.toLowerCase().endsWith(".webm")) {
                mediaType = "video/webm";
            } else if (filename.toLowerCase().endsWith(".ogg")) {
                mediaType = "video/ogg";
            } else {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mediaType))
                    .body(videoBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/courVideo/{filename}")
    public ResponseEntity<byte[]> getCourVideo(@PathVariable("filename") String filename) {
        try {
            byte[] videoBytes = courVideoFileService.loadFile(filename);
            String mediaType;
            if (filename.toLowerCase().endsWith(".mp4")) {
                mediaType = "video/mp4";
            } else if (filename.toLowerCase().endsWith(".webm")) {
                mediaType = "video/webm";
            } else if (filename.toLowerCase().endsWith(".ogg")) {
                mediaType = "video/ogg";
            } else {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mediaType))
                    .body(videoBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
