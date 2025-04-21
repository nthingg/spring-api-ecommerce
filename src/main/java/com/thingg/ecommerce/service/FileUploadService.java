package com.thingg.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadFile(MultipartFile file);

    void deleteFile(String fileName);
}
