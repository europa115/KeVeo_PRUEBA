package com.example.KeVeo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.KeVeo.service.AWSS3Service;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Getter
@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private static final Logger LOGGER= LoggerFactory.getLogger(AWSS3ServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    private String newFileName;
    @Override
    public void uploadFile(MultipartFile file) {
        File mainFile=new File(file.getOriginalFilename());
        try(FileOutputStream stream=new FileOutputStream(mainFile)){
            stream.write(file.getBytes());
            newFileName=System.currentTimeMillis()+"_"+mainFile.getName();
            LOGGER.info("Subiendo archivo "+newFileName);
            PutObjectRequest request=new PutObjectRequest(bucketName,newFileName,mainFile);
            amazonS3.putObject(request);
        }catch (IOException e){
        LOGGER.error(e.getMessage(), e);
        }

    }
}
