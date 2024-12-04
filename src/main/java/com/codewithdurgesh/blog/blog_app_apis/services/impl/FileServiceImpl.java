package com.codewithdurgesh.blog.blog_app_apis.services.impl;

import com.codewithdurgesh.blog.blog_app_apis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File Name
        String fileName = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String fileName1 = randomId + extension;
        //Full Path
        String directoryPath = path + File.separator;
        String filePath = directoryPath + fileName1;
        //create folder if not created
        File directoryFile = new File(directoryPath);
        if(!directoryFile.exists()){
            boolean mkdir = directoryFile.mkdirs();
            System.out.println("Directory created: " + mkdir);
        }
        //fileCopy

            Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }



    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is=new FileInputStream(fullPath);

        return is;

    }
}
