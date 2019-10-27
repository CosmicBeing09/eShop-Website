package com.daraz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.imageio.ImageIO;

import com.cloudinary.utils.ObjectUtils;
import com.daraz.component.CloudinaryConfig;
import com.daraz.obj.FileStorageException;
import com.daraz.obj.MyFileNotFoundException;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    @Autowired
    CloudinaryConfig cloudc;
	

    @Autowired
    public FileStorageService(com.daraz.obj.FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

   
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String[] temp = fileName.split("[.]", 2);
//        temp[0]=temp[0]+"small";
//        String fina_l = temp[0]+"."+temp[1];
//        System.out.println(fina_l);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            BufferedImage input = ImageIO.read(file.getInputStream());
            BufferedImage output = Thumbnails.of(input).size(555,600).asBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(output, "png", baos);
            baos.flush();
            MultipartFile file_temp =new MockMultipartFile(fileName,baos.toByteArray());
            Files.copy(file_temp.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            Map uploadResult = null;
//    		if (file_temp.isEmpty()){
//                //model.addAttribute("message","Please select a file to upload");
//                return "NO FILE";
//            }
//            try {
//                 uploadResult =  cloudc.upload(file_temp.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
////                model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
////                model.addAttribute("imageurl", uploadResult.get("url"));
//            } catch (IOException e){
//                e.printStackTrace();
////                model.addAttribute("message", "Sorry I can't upload that!");
//                
//            }
//            return (String) uploadResult.get("url");
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public String storeSmallFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String[] temp = fileName.split("[.]", 2);
        temp[0]=temp[0]+"small";
        fileName = temp[0]+"."+temp[1];
        //System.out.println(fileName);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            BufferedImage input = ImageIO.read(file.getInputStream());
            BufferedImage output = Thumbnails.of(input).size(60,60).asBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(output, "png", baos);
            baos.flush();
            MultipartFile file_temp =new MockMultipartFile(fileName,baos.toByteArray());
            Files.copy(file_temp.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            Map uploadResult = null;
//    		if (file_temp.isEmpty()){
//                //model.addAttribute("message","Please select a file to upload");
//                return "NO FILE";
//            }
//            try {
//                 uploadResult =  cloudc.upload(file_temp.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
////                model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
////                model.addAttribute("imageurl", uploadResult.get("url"));
//            } catch (IOException e){
//                e.printStackTrace();
////                model.addAttribute("message", "Sorry I can't upload that!");
//                
//            }
//            return (String) uploadResult.get("url");
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public String storeSlide(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            BufferedImage input = ImageIO.read(file.getInputStream());
            BufferedImage output = Thumbnails.of(input).size(3860,3060).asBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(output, "png", baos);
            baos.flush();
            MultipartFile file_temp =new MockMultipartFile(fileName,baos.toByteArray());
            Files.copy(file_temp.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}