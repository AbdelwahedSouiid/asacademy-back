package com.beesidk.projet.service.fileService;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.repository.CourRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class CourAfficheFileService implements FileService<Cour> {

    private CourRepository courRepository;


    @Override
    public void uploadFile(MultipartFile file, String id) throws Exception {
        Cour cour = courRepository.findById(id).orElseThrow(() -> new Exception("Cour not found"));
        // Get the content type of the uploaded file
        String contentType = file.getContentType();
        // Determine the file extension based on the content type
        String fileExtension;
        if (contentType.equals(MediaType.IMAGE_PNG_VALUE)) {
            fileExtension = ".png";
        } else if (contentType.equals(MediaType.IMAGE_JPEG_VALUE)) {
            fileExtension = ".jpeg";
        } else {
            throw new Exception("Unsupported file type. Please upload PNG or JPEG images.");
        }

        // Set the image filename with the correct extension
        String filename = id + fileExtension;
        cour.setAffiche(filename);

        // Define the file path
        Path filePath = Paths.get(System.getProperty("user.home") + "/ASacademy/Cours/affiche/" + filename);

        // Write the file to the defined path
        Files.write(filePath, file.getBytes());

        // Save the course entity with the updated image filename
        courRepository.save(cour);
    }

    @Override
    public byte[] loadFile(String affiche) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ASacademy/Cours/affiche/" + affiche));
    }


}
