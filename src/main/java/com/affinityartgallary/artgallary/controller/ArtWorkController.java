package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.response.AddArtWorkResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateArtWorkResponse;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/artwork")
public class ArtWorkController {
    @Autowired
    ArtWorkService artWorkService;

    @PostMapping("/addArtWork/{artistName}")
    public ResponseEntity<AddArtWorkResponse> addArtWork(@PathVariable String artistName, @ModelAttribute AddArtWorkRequest addArtWorkRequest) throws ArtWorkAlreadyExistException, IOException {
         return new ResponseEntity<>(artWorkService.addArtWorkToArtist(artistName,addArtWorkRequest), HttpStatus.OK);
    }

    @GetMapping("/{artWorkId}")
    public ResponseEntity<?> getArtWorkById(@PathVariable String artWorkId){
        return new ResponseEntity<>(artWorkService.getArtWorkById(artWorkId),HttpStatus.OK);
    }

    @PatchMapping("/updateArtWork/{artWorkId}")
    public ResponseEntity<UpdateArtWorkResponse> updateArtWork(@PathVariable String artWorkId, @ModelAttribute UpdateArtWorkRequest updateArtWorkRequest) throws IOException {
        return new ResponseEntity<>(artWorkService.updateArtWork(artWorkId,updateArtWorkRequest),HttpStatus.OK);
    }
    //checkout this method
    @DeleteMapping("/deleteArtWork/{artWorkId}")
    public String removeArtWork(@PathVariable String artWorkId){
        artWorkService.removeArtWork(artWorkId);
        return "art work deleted";
    }
    @GetMapping("/getAllArtWork")
    public ResponseEntity<List<ArtWork>> getAllArtWork(){
        return new ResponseEntity<>(artWorkService.getAllArtWork(),HttpStatus.OK);
    }
}
