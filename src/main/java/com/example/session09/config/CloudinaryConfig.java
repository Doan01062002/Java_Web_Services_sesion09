package com.example.session09.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlz6sulzj",
                "api_key", "785158124563839",
                "api_secret", "ndEbN-AL0Xf8TupeenWvDVRsU3c",
                "secure", true));
    }
}