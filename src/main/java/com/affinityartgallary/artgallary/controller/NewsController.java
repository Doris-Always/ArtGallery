package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;
import com.affinityartgallary.artgallary.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @PostMapping("/createNews")
    public ResponseEntity<?> createNews(@RequestBody CreateNewsRequest newsRequest){
        return new ResponseEntity<>(newsService.createNews(newsRequest), HttpStatus.OK);
    }
    @GetMapping("/{newsId}")
    public ResponseEntity<?> getArtWorkById(@PathVariable String newsId){
        return new ResponseEntity<>(newsService.getNewsById(newsId),HttpStatus.OK);
    }

    @PatchMapping("/updateNews/{newsId}")
    public ResponseEntity<?> updateArtWork(@PathVariable String newsId, UpdateNewsRequest updateNewsRequest){
        return new ResponseEntity<>(newsService.updateNews(newsId,updateNewsRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeNews/{newsId}")
    public ResponseEntity<?> removeArtist(@PathVariable ("newsId") String newsId){
        newsService.removeNews(newsId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
