package com.daraz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.*;
import com.daraz.obj.UploadFileResponse;
import com.daraz.repo.ProductRepo;
import com.daraz.obj.Product;
import com.daraz.obj.Product_temp;
import com.daraz.service.FeaturedProductService;
import com.daraz.service.FileStorageService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FeaturedProductController {
	public ArrayList<String>arrayList = new ArrayList<String>(); 
	private static final Logger logger = LoggerFactory.getLogger(FeaturedProductController.class);

    @Autowired
    private FileStorageService fileStorageService;
	@Autowired
	private FeaturedProductService featuredProductService;
	@Autowired
	private ProductRepo productRepo;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allfeatured")
	public List<Product> getall(){

		return featuredProductService.getall();
	}
	@RequestMapping("/hi")
	public String s(){
		return "welcome";
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allfeatured/{id}")
	public List<Product> getOne(@PathVariable int id){

		List<Product> all = featuredProductService.getall();
		List<Product> result = new ArrayList<>();
		for(int i = 0;i<all.size();i++) {
			if(all.get(i).getProduct_id() == id) {
				result.add(all.get(i));
				break;
			}
		}
		return result;
	}
	
	@CrossOrigin
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
       
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        arrayList.add(fileName);
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@CrossOrigin
	@PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files,@RequestPart("product")Product_temp product_temp) {
		List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
		
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(arrayList.get(1));
		product_temp.setImage3(arrayList.get(2));
		
		productRepo.save(product_temp);
        return list;
    }

	@CrossOrigin
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
}
