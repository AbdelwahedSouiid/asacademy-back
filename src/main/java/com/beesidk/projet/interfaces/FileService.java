package com.beesidk.projet.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileService<T> {


    void uploadFile(MultipartFile file, String id) throws Exception;

    byte[] loadFile(String image) throws Exception;

}
