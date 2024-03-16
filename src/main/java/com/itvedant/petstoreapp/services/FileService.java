package com.itvedant.petstoreapp.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itvedant.petstoreapp.FileStorageProperties;
import com.itvedant.petstoreapp.entities.Product;
import com.itvedant.petstoreapp.repositories.ProductRepository;



@Service
public class FileService {
    @Autowired
    private ProductRepository repository;

    //We are declaring the variable that will contain the path
    //of the upload folder
    private final Path rootLocation;

    //In this constructor we are initializing the value of the rootLocation
    //Here we are also doing constructor based injection
    public FileService(FileStorageProperties properties){
        this.rootLocation = Paths.get(properties.getUploadDir());
    }

    public String fileUpload(MultipartFile file, Integer id){
        try{
            //Setting the name of the file to that of the original filename
            Path destinationFile = this.rootLocation.resolve(file.getOriginalFilename());
            //Setting the input stream the read the file coming in the request
            InputStream inputStream = file.getInputStream();
            //Write the file in the upload folder
            //copy the file from the request into the uploadedimages folder
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            //now we will update the imageUrl for the product whose id will be provided
            //first lets check whether the product with this id exists or not

            Product findProduct = this.repository.findById(id).orElse(null);
            if(findProduct == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                  "User with ID does not exists");
            else{
                //ServletUriComponentsBuilder.fromCurrentContextPath() will give the 
                //current domain (http://localhost:8080)
                String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/products/download/")
                                .path(file.getOriginalFilename())
                                .toUriString();

                findProduct.setImageUrl(url);
                this.repository.save(findProduct); //updating the url for the product
            }

            return "File Uploaded Successfully";
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                                              "Server facing issue in uploading the file");
        }
    }

    public Resource fileDownload(String fileName){
        Path file = rootLocation.resolve(fileName);

        try{
            Resource resource = new UrlResource(file.toUri());

            return resource;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                            "File of this name does not exists");   
        }
    }

}

