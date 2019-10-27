package com.daraz.controller;

import java.io.IOException;

import com.daraz.service.EmailSenderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.*;
import com.daraz.obj.UploadFileResponse;
import com.daraz.repo.HomeSliderRepo;
import com.daraz.repo.MailBodyRepo;
import com.daraz.repo.ProductRepo;
import com.cloudinary.utils.ObjectUtils;
import com.daraz.component.CloudinaryConfig;
import com.daraz.obj.HomeSlider;
import com.daraz.obj.MailBody;
import com.daraz.obj.Product;
import com.daraz.obj.Product_temp;
import com.daraz.service.FeaturedProductService;
import com.daraz.service.FileStorageService;
import com.daraz.service.HomeSliderService;
import com.daraz.service.ProductService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FeaturedProductController {
	//ArrayList contains the URL of the product images
	public ArrayList<String>arrayList = new ArrayList<String>(); 
	public ArrayList<String>sliderURL = new ArrayList<String>();
	public ArrayList<String>arrayListSmallFile = new ArrayList<String>();
	
	private static final Logger logger = LoggerFactory.getLogger(FeaturedProductController.class);

    @Autowired
    private FileStorageService fileStorageService;
	@Autowired
	private FeaturedProductService featuredProductService;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ProductService productService;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private MailBodyRepo mailBodyRepo;
	@Autowired
	private HomeSliderRepo homeSliderRepo;
	@Autowired
	private HomeSliderService homeSliderService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allfeatured")
	public List<Product> getall(){

		return featuredProductService.getall();
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allproduct")
	public List<Product_temp> getAllProduct(){

		return productService.getall();
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/all_user_product/{userId}")
	public List<Product_temp> getAllProductByUserId(@PathVariable String userId){
		
		List<Product_temp> all = productService.getall();
		List<Product_temp> temp = new ArrayList<Product_temp>();
		for(int i=0;i<all.size();i++) {
			if(all.get(i).getUserId()!=null)
			if(all.get(i).getUserId().equals(userId))
				temp.add(all.get(i));
			
		}
		return temp;
	}
	
	@RequestMapping("/hi")
	public String s(){
		return "welcome";
	}
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allproduct/{id}")
	public Product_temp getOneProduct(@PathVariable String id){


		List<Product_temp> all = productService.getall();
		Product_temp temp = new Product_temp();
		for(int i=0;i<all.size();i++) {
			if(all.get(i).getId().equals(id)) {
				temp=all.get(i);
				break;
			}
		}
		return temp;
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
	@RequestMapping(method = RequestMethod.DELETE , value = "/delete/{id}")
	public String deleteOne(@PathVariable String id){

		String result = productService.deleteOne(id);
		return result;
	}

	
	@CrossOrigin
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	    
		
		
        String fileName = fileStorageService.storeFile(file);
        String smallFileName = fileStorageService.storeSmallFile(file);
       
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
        arrayList.add(fileDownloadUri);
   //     arrayList.add(fileName);
        
        String smallFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(smallFileName)
                .toUriString();
        
         arrayListSmallFile.add(smallFileDownloadUri);
        
        
        return new UploadFileResponse(fileName, fileName,
                file.getContentType(), file.getSize());
    }
	
	
	///////////////////// =============   Cloudinary Image Upload Testing   ==================   ////////////////////////
	@Autowired
    CloudinaryConfig cloudc;
	
	@CrossOrigin
	@PostMapping("/cloudc")
    public String singleImageUpload(@RequestParam("file") MultipartFile file){
        Map uploadResult = null;
		if (file.isEmpty()){
            //model.addAttribute("message","Please select a file to upload");
            return "NO FILE";
        }
        try {
             uploadResult =  cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
//            model.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
//            model.addAttribute("imageurl", uploadResult.get("url"));
        } catch (IOException e){
            e.printStackTrace();
//            model.addAttribute("message", "Sorry I can't upload that!");
            
        }
        return (String) uploadResult.get("url");
    }
	
	
	////////////////////////////////////// ================ End ================= ////////////////////////////////
	@CrossOrigin
	@PostMapping("/uploadProduct")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files,@RequestPart("product")Product_temp product_temp) {
		arrayList.clear();
		arrayListSmallFile.clear();
		
		List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
		
		if(arrayList.size()==1) {
		product_temp.setImage1(arrayList.get(0));
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		}
		else if(arrayList.size()==2) {
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(arrayList.get(1));
		
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		product_temp.setSmallImage2(arrayListSmallFile.get(1));
		}
		else if (arrayList.size()>2){
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(arrayList.get(1));	
		product_temp.setImage3(arrayList.get(2));
		
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		product_temp.setSmallImage2(arrayListSmallFile.get(1));
		product_temp.setSmallImage3(arrayListSmallFile.get(2));
		}
		
		productRepo.save(product_temp);
        return list;
    }
	
	@CrossOrigin
	@PutMapping("/updateProduct")
    public Product_temp updateProduct(@RequestPart("files") MultipartFile[] files,@RequestPart("product")Product_temp product_temp) {
		arrayList.clear();
		arrayListSmallFile.clear();
		
		List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
		
		List<Product_temp> all = productService.getall();
		Product_temp temp = new Product_temp();
		for(int i=0;i<all.size();i++) {
			if(all.get(i).getId().equals(product_temp.getId())) {
				temp = all.get(i);
			}
		}
		if(arrayList.size()==0) {
			product_temp.setImage1(temp.getImage1());
			product_temp.setImage2(temp.getImage2());	
			product_temp.setImage3(temp.getImage3());
			
			product_temp.setSmallImage1(temp.getSmallImage1());
			product_temp.setSmallImage2(temp.getSmallImage2());
			product_temp.setSmallImage3(temp.getSmallImage3());
		}
		else if(arrayList.size()==1) {
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(temp.getImage2());	
		product_temp.setImage3(temp.getImage3());
		
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		product_temp.setSmallImage2(temp.getSmallImage2());
		product_temp.setSmallImage3(temp.getSmallImage3());
		}
		
		else if(arrayList.size()==2) {
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(arrayList.get(1));
		product_temp.setImage3(temp.getImage3());
		
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		product_temp.setSmallImage2(arrayListSmallFile.get(1));
		product_temp.setSmallImage3(temp.getSmallImage3());
		}
		else if (arrayList.size()==3 || arrayList.size()>2){
		product_temp.setImage1(arrayList.get(0));
		product_temp.setImage2(arrayList.get(1));	
		product_temp.setImage3(arrayList.get(2));
		
		product_temp.setSmallImage1(arrayListSmallFile.get(0));
		product_temp.setSmallImage2(arrayListSmallFile.get(1));
		product_temp.setSmallImage3(arrayListSmallFile.get(2));
		}
		
		productRepo.save(product_temp);
        return product_temp;
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
	
	@CrossOrigin
	@PostMapping("/order")
	public String takeOrder(@RequestPart("mailbody") MailBody mailBody) {
		mailBody.setDate(new Date());
		mailBodyRepo.save(mailBody);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("proda5.order@gmail.com");
		mailMessage.setSubject("Order on product: "+mailBody.getProductName());
		mailMessage.setFrom("proda5.temp@gmail.com");
		mailMessage.setText("Ordered from phone number: "+mailBody.getPhoneNo()+"."+"\n" +
		"Address: "+mailBody.getAddress()+"\n"+"Product Name: "+mailBody.getProductName()
		+"\n"+"Price: "+mailBody.getPrice()+"\n"+mailBody.getShopName());
		emailSenderService.sendEmail(mailMessage);
		return "success";
	}
	
	
	
	  public UploadFileResponse uploadSlide(@RequestParam("file") MultipartFile file) {
		    
	        String fileName = fileStorageService.storeSlide(file);
	       
	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        
	         sliderURL.add(fileDownloadUri);
	        return new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	    }
	
	@CrossOrigin
	@PostMapping("/uploadSlider")
	public List<UploadFileResponse> uploadSlider(@RequestPart("files")MultipartFile[] files){
		sliderURL.clear();
		List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadSlide(file))
                .collect(Collectors.toList());
		for(int i=0;i<sliderURL.size();i++) {
			HomeSlider tempSlider = new HomeSlider();
			tempSlider.setImage(sliderURL.get(i));
			homeSliderRepo.save(tempSlider);
		}
		return list;
	}
	
	@CrossOrigin
	@GetMapping("/allSlider")
	public List<HomeSlider> getAllSlider(){
	 return homeSliderService.getAll();	
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE , value = "/deleteSlide/{id}")
	public String deleteOneSlide(@PathVariable String id){

		String result = homeSliderService.deleteOne(id);
		return result;
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET , value = "/allproduct_type/{type}")
	public List<Product_temp> getProductOfType(@PathVariable String type){

		return productService.getProductOfType(type);
	}
}