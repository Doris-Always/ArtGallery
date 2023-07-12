package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @PostMapping("/addArtist")
    public ResponseEntity<?> addArtist(@RequestBody AddArtistRequest addArtistRequest){
        return new ResponseEntity<>(artistService.addArtist(addArtistRequest), HttpStatus.OK);
    }


}
