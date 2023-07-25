package com.affinityartgallary.artgallary.controller;

import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.dto.response.AddExhibitionResponse;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/exhibition")
public class ExhibitionController {
    @Autowired
    ExhibitionService exhibitionService;
    @Autowired
    ArtistService artistService;

    @PostMapping("/addExhibition/{artistId}")
    public ResponseEntity<AddExhibitionResponse> addExhibition(@PathVariable("artistId") String artistId, @ModelAttribute AddExhibitionRequest addExhibitionRequest) throws IOException {
        return new ResponseEntity<>(exhibitionService.addExhibition(artistId,addExhibitionRequest), HttpStatus.OK);
    }

    @GetMapping("/getExhibition/{artistId}")
    public ResponseEntity<?> getExhibitionByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(exhibitionService.getExhibitionByArtistId(artistId),HttpStatus.OK);
    }

    @PatchMapping("/updateExhibition/{exhibitionId}")
    public ResponseEntity<?> updateExhibition(@PathVariable String exhibitionId, @ModelAttribute UpdateExhibitionRequest updateExhibitionRequest) throws IOException {
        return new ResponseEntity<>(exhibitionService.updateExhibition(exhibitionId,updateExhibitionRequest),HttpStatus.OK);
    }
    @DeleteMapping("/removeExhibition/{exhibitionId}")
    public String removeExhibition(@PathVariable String exhibitionId){
        exhibitionService.removeExhibition(exhibitionId);
        return "exhibition deleted";
    }
    @GetMapping("/findAllExhibitionByArtist/{artistId}")
    public ResponseEntity<List<Exhibition>> getAllArtWorkByArtistId(@PathVariable String artistId){
        return new ResponseEntity<>(exhibitionService.getAllExhibitionByArtistId(artistId),HttpStatus.OK);
    }
    @GetMapping("/getAllExhibition")
    public ResponseEntity<List<Exhibition>> getAllExhibition(){
        return new ResponseEntity<>(exhibitionService.getAllExhibition(),HttpStatus.OK);
    }

}
