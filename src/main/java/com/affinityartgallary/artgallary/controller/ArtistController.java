package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;
import com.affinityartgallary.artgallary.dto.response.AddArtistResponse;
import com.affinityartgallary.artgallary.dto.response.UpdaterArtistResponse;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @PostMapping("/addArtist")
    public ResponseEntity<AddArtistResponse> addArtist(@ModelAttribute AddArtistRequest addArtistRequest) throws IOException {
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
    public ResponseEntity<UpdaterArtistResponse> updateArtist (@PathVariable ("id")String id, @ModelAttribute UpdateArtistRequest updateArtistRequest) throws IOException {
        return new ResponseEntity<>(artistService.updateArtistInformation(id,updateArtistRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeArtist/{artistName}")
    public String removeArtist(@PathVariable ("artistName") String artistName){
        artistService.removeArtistByName(artistName);
        return "artist deleted";
    }
    @GetMapping("/findArtistByArtWork/{artistId}")
    public ResponseEntity<List<ArtWork>> getAllArtWorkByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(artistService.getAllArtWorkByArtistId(artistId),HttpStatus.OK);
    }
    @GetMapping("/getAllArtist")
    public ResponseEntity<List<Artist>> getAllArtist(){
        return new ResponseEntity<>(artistService.getAllArtist(),HttpStatus.OK);
    }

}
