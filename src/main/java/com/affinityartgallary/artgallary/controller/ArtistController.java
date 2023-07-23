package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

//    @PostMapping("/addArtist")
//    public String method(){
//        return "am here";
//    }
    @PostMapping("/addArtist")
    public ResponseEntity<?> addArtist(@ModelAttribute AddArtistRequest addArtistRequest
                                       ) throws IOException {
//        addArtistRequest.setImage(file);
        return new ResponseEntity<>(artistService.addArtist(addArtistRequest), HttpStatus.OK);
    }

    @GetMapping("/findArtistById/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable String id){
        return new ResponseEntity<>(artistService.getArtistById(id),HttpStatus.OK);
    }

    @GetMapping("/findArtistByName/{artistName}")
    public ResponseEntity<?> getArtistByName(@PathVariable("artistName") String artistName){
        return new ResponseEntity<>(artistService.getArtistByName(artistName),HttpStatus.OK);

    }
    @PatchMapping("/updateArtist/{id}")
    public ResponseEntity<?> updateArtist (@PathVariable ("id")String id, @ModelAttribute UpdateArtistRequest updateArtistRequest) throws IOException {
        return new ResponseEntity<>(artistService.updateArtistInformation(id,updateArtistRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeArtist/{artistName}")
    public ResponseEntity<?> removeArtist(@PathVariable ("artistName") String artistName){
        artistService.removeArtistByName(artistName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/findArtistByArtWork/{artistId}")
    public ResponseEntity<?> getAllArtWorkByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(artistService.getAllArtWorkByArtistId(artistId),HttpStatus.OK);
    }

}
