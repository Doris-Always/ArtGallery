package com.affinityartgallary.artgallary.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
        private final String CLOUD_NAME = "cloud_name";
        private final String API_KEY = "api_key";
        private final String API_SECRET = "api_secret";
        @Bean
        public Cloudinary cloudinary(){
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name",CLOUD_NAME);
            config.put("api_key",API_KEY);
            config.put("api_secret",API_SECRET);
            return new Cloudinary(config);

    }
}
