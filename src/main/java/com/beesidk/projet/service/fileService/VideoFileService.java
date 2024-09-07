package com.beesidk.projet.service.fileService;

import com.beesidk.projet.entity.Video;
import com.beesidk.projet.interfaces.FileService;
import com.beesidk.projet.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class VideoFileService implements FileService<Video> {
    private VideoRepository videoRepository;

    @Override
    public void uploadFile(MultipartFile file, String id) throws Exception {
        // Find the video by ID
        Video video = videoRepository.findById(id).orElseThrow(() -> new Exception("Video not found"));

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

        // Set the video filename with the correct extension
        String filename = id + fileExtension;
        video.setVideoUrl(filename);

        // Define the file path
        Path filePath = Paths.get(System.getProperty("user.home") + "/ASacademy/videos/" + filename);

        // Create directories if they do not exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Write the file to the defined path
        Files.write(filePath, file.getBytes());

        // Save the video entity with the updated video filename
        videoRepository.save(video);
    }

    @Override
    public byte[] loadFile(String video) throws Exception {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ASacademy/videos/" + video));
    }
}
