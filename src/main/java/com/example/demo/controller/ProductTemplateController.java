package com.example.demo.controller;



import com.example.demo.exception.NotFoundException;
import com.example.demo.model.request.UpdateForm;
import com.example.demo.model.request.UploadFile;
import com.example.demo.model.request.UploadForm;
import com.example.demo.service.BrandService;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


@Controller
public class ProductTemplateController {
    private static String UPLOAD_DIR =  "E:/upload";
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private FileStorageService fileStorageService;
    @GetMapping("/product")
    public String list(Model model , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @ModelAttribute("name") String name){
        model.addAttribute("product" ,productService.search(name , page , size));
        model.addAttribute("name",name);
        model.addAttribute("brand",brandService.getAllBrand());

//        model.addAttribute("myFile", new MyFile());


        return "listproduct";
    }
    @PostMapping("/product/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") UploadForm form) {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile fileData = form.getFileData();
        String name = fileData.getOriginalFilename();
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                return ResponseEntity.ok("/file/"+name);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }
//@PostMapping("/product/upload")
//    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
//    String message = "";
//    UploadFile result = new UploadFile();
//    try {
//        String newFilename = fileStorageService.store(file);
//        message = "You successfully uploaded " +
//                file.getOriginalFilename() + "!";
//        result.setMessage(message);
//        result.setSuccess(true);
//        result.setLink(UPLOAD_DIR +
//                newFilename);
//    } catch (Exception e) {
//        result.setSuccess(false);
//        result.setMessage(e.getMessage());
//    }
//    return ResponseEntity.ok(result);
//}
    @GetMapping("/file/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        File file = new File(UPLOAD_DIR + "/" + filename);
        if (!file.exists()) {
            throw new NotFoundException("File not found");
        }

        UrlResource resource;
        try {
            resource = new UrlResource(file.toURI());
        } catch (MalformedURLException e) {
            throw new NotFoundException("File not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }

}
