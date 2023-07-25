package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.data.model.News;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;
import com.affinityartgallary.artgallary.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @PostMapping("/createNews")
    public ResponseEntity<News> createNews(@ModelAttribute CreateNewsRequest newsRequest) throws IOException {
        return new ResponseEntity<>(newsService.createNews(newsRequest), HttpStatus.OK);
    }
    @GetMapping("/{newsId}")
    public ResponseEntity<?> getNewsById(@PathVariable String newsId){
        return new ResponseEntity<>(newsService.getNewsById(newsId),HttpStatus.OK);
    }

    @PatchMapping("/updateNews/{newsId}")
    public ResponseEntity<News> updateNews(@PathVariable String newsId,@ModelAttribute UpdateNewsRequest updateNewsRequest) throws IOException {
        return new ResponseEntity<>(newsService.updateNews(newsId,updateNewsRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeNews/{newsId}")
    public String removeNews(@PathVariable ("newsId") String newsId){
        newsService.removeNews(newsId);
        return "news deleted";

    }
    @GetMapping("/getAllNews")
    public ResponseEntity<List<News>> getAllNews(){
        return new ResponseEntity<>(newsService.getAllNews(),HttpStatus.OK);
    }
}
