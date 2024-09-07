package com.beesidk.projet.service.fileService;

import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.interfaces.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class CategorieFileService implements FileService<Categorie> {

    private com.beesidk.projet.repository.CategorieRepository CategorieRepository;


    @Override
    public void uploadFile(MultipartFile file, String id) throws Exception {
        Categorie Categorie = CategorieRepository.findById(id).orElseThrow(() -> new Exception("Categorie not found"));
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
        Categorie.setImage(filename);

        // Define the file path
        Path filePath = Paths.get(System.getProperty("user.home") + "/ASacademy/Categorie/affiche/" + filename);

        // Write the file to the defined path
        Files.write(filePath, file.getBytes());

        // Save the Categoriese entity with the updated image filename
        CategorieRepository.save(Categorie);
    }

    @Override
    public byte[] loadFile(String image) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ASacademy/Categorie/Image/" + image));
    }
}
