package com.beesidk.projet.service.fileService;

import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@AllArgsConstructor
public class UserFileService implements FileService<AppUser> {

    private AppUserRepository userRepository;

    @Override
    public void uploadFile(MultipartFile file, String id) throws Exception {
        // Find the user by ID
        AppUser user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        // Get the content type of the uploaded file
        String contentType = file.getContentType();

        // Determine the file extension based on the content type
        String fileExtension;
        if (contentType.equals(MediaType.IMAGE_PNG_VALUE)) {
            fileExtension = ".png";
        } else if (contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals("image/jpg")) {
            fileExtension = ".jpeg";
        } else {
            throw new Exception("Unsupported file type. Please upload PNG or JPEG images.");
        }

        // Set the image filename with the correct extension
        String filename = id + fileExtension;
        user.setPhoto(filename);

        // Define the file path
        Path filePath = Paths.get(System.getProperty("user.home") + "/ASacademy/user/" + filename);

        // Write the file to the defined path
        Files.write(filePath, file.getBytes());

        // Save the user entity with the updated image filename
        userRepository.save(user);
    }

    @Override
    public byte[] loadFile(String image) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ASacademy/user/" + image));
    }


}
