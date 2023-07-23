package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/exhibition")
public class ExhibitionController {
    @Autowired
    ExhibitionService exhibitionService;
    @Autowired
    ArtistService artistService;

    @PostMapping("/addExhibition/{artistId}")
    public ResponseEntity<?> addExhibition(@PathVariable("artistId") String artistId, @ModelAttribute AddExhibitionRequest addExhibitionRequest) throws IOException {
        return new ResponseEntity<>(exhibitionService.addExhibition(artistId,addExhibitionRequest), HttpStatus.OK);
    }

    @GetMapping("/getExhibition/{artistId}")
    public ResponseEntity<?> getExhibitionByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(exhibitionService.getExhibitionByArtistId(artistId),HttpStatus.OK);
    }

    @PatchMapping("/updateExhibition/{id}")
    public ResponseEntity<?> updateExhibition(@PathVariable ("id")String exhibitionId, @ModelAttribute UpdateExhibitionRequest updateExhibitionRequest) throws IOException {
        return new ResponseEntity<>(exhibitionService.updateExhibition(exhibitionId,updateExhibitionRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeExhibition/{id}")
    public ResponseEntity<?> removeExhibition(@PathVariable ("id") String exhibitionId){
        exhibitionService.removeExhibition(exhibitionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/findAllExhibitionByArtist/{artistId}")
    public ResponseEntity<?> getAllArtWorkByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(exhibitionService.getAllExhibitionByArtistId(artistId),HttpStatus.OK);
    }

}
