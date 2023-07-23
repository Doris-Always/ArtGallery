package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/artwork")
public class ArtWorkController {
    @Autowired
    ArtWorkService artWorkService;

    @PostMapping("/addArtWork/{artistName}")
    public ResponseEntity<?> addArtWork(@PathVariable String artistName, @ModelAttribute AddArtWorkRequest addArtWorkRequest) throws ArtWorkAlreadyExistException, IOException {
         return new ResponseEntity<>(artWorkService.addArtWorkToArtist(artistName,addArtWorkRequest), HttpStatus.OK);
    }

    @GetMapping("/{artWorkId}")
    public ResponseEntity<?> getArtWorkById(@PathVariable String artWorkId){
        return new ResponseEntity<>(artWorkService.getArtWorkById(artWorkId),HttpStatus.OK);
    }

    @PatchMapping("/updateArtWork/{artWorkId}")
    public ResponseEntity<?> updateArtWork(@PathVariable String artWorkId, @ModelAttribute UpdateArtWorkRequest updateArtWorkRequest) throws IOException {
        return new ResponseEntity<>(artWorkService.updateArtWork(artWorkId,updateArtWorkRequest),HttpStatus.OK);
    }
    //checkout this method
    @DeleteMapping("/deleteArtWork/{artWorkId}")
    public ResponseEntity<?> removeArtWork(@PathVariable String artWorkId){
        artWorkService.removeArtWork(artWorkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
