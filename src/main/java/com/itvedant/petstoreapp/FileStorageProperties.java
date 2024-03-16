package com.itvedant.petstoreapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

//This class defines the property and its fields
//prefix=file means the property will start with word file
@ConfigurationProperties(prefix = "file")
@Data
public class FileStorageProperties {
    private String uploadDir;
    
}
