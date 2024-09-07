package com.beesidk.projet.service.fileService;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.repository.CourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@AllArgsConstructor
public class CourVideoFileService implements FileService<Cour> {
    private CourRepository CourRepository;

    @Override
    public void uploadFile(MultipartFile file, String id) throws Exception {
        // Find the Cour by ID
        Cour Cour = CourRepository.findById(id).orElseThrow(() -> new Exception("Cour not found"));

        // Get the content type of the uploaded file
        String contentType = file.getContentType();

        // Determine the file extension based on the content type
        String fileExtension;
        if (contentType.equals("video/mp4")) {
            fileExtension = ".mp4";
        } else if (contentType.equals("video/webm")) {
            fileExtension = ".webm";
        } else if (contentType.equals("video/ogg")) {
            fileExtension = ".ogg";
        } else {
            throw new Exception("Unsupported file type. Please upload MP4, WebM, or Ogg videos.");
        }

        // Set the Cour filename with the correct extension
        String filename = id + fileExtension;
        Cour.setVideo(filename);

        // Define the file path
        Path filePath = Paths.get(System.getProperty("user.home") + "/ASacademy/Cours/video/" + filename);

        // Create directories if they do not exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Write the file to the defined path
        Files.write(filePath, file.getBytes());

        // Save the Cour entity with the updated Cour filename
        CourRepository.save(Cour);
    }

    @Override
    public byte[] loadFile(String CourVideo) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ASacademy/Cours/video/" + CourVideo));
    }
}
