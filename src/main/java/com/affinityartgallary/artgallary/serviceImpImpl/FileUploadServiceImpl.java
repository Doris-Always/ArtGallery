package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.services.FileUploadService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final Cloudinary cloudinary;


    @Override
    public String uploadFile(MultipartFile multipartFile, String id) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(), Map.of("public_id", id))
                .get("url").toString();
    }
}
