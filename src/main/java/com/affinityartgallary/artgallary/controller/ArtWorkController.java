package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/artwork")
public class ArtWorkController {
    @Autowired
    ArtWorkService artWorkService;

    @PostMapping("/addArtWork/{artistName}")
    public ResponseEntity<?> addArtWork(@PathVariable String artistName, @RequestBody AddArtWorkRequest addArtWorkRequest){
         return new ResponseEntity<>(artWorkService.addArtWorkToArtist(artistName,addArtWorkRequest), HttpStatus.OK);
    }

    @GetMapping("/{artWorkId}")
    public ResponseEntity<?> getArtWorkById(@PathVariable String artWorkId){
        return new ResponseEntity<>(artWorkService.getArtWorkById(artWorkId),HttpStatus.OK);
    }
}
