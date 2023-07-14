package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ArtWorkServiceTest {

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtWorkService artWorkService;
    AddArtWorkRequest addArtWorkRequest;
    AddArtistRequest addArtistRequest;

    UpdateArtWorkRequest updateArtWorkRequest;
    @BeforeEach
    void setUp(){
        addArtWorkRequest = new AddArtWorkRequest();
        addArtWorkRequest.setTitle("Birthdays are scary,2022");
        addArtWorkRequest.setMedium("Acrylic");
        addArtWorkRequest.setDimension("112  X 345 ");
        addArtWorkRequest.setImageUrl("www.cloudinary.com");

        addArtistRequest = new AddArtistRequest();
        addArtistRequest.setName("Ben");
        addArtistRequest.setYearOfBirth("1904");
        addArtistRequest.setArtistBio("the work is good");
        addArtistRequest.setImageUrl("www.cloudinary.com");

        updateArtWorkRequest = new UpdateArtWorkRequest();


    }

    @Test
    void testThatArtWorkCanBeAddedToAnArtist(){
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        var artwork = artWorkService.addArtWorkToArtist(foundArtist.getName(),addArtWorkRequest);
        assertEquals(artwork.getArtistId(),foundArtist.getId());


    }
    @Test
    void testThatICanFindArtworkByArtistId(){
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        ArtWork artwork = artWorkService.addArtWorkToArtist(foundArtist.getName(),addArtWorkRequest);
       List<ArtWork> artWorks= artistService.getAllArtWorkByArtistId(foundArtist.getId());
       assertEquals(1,artWorks.size());



    }
//    @Test
//    void testThatAllArtWorkCanBeFoundById(){
//        artistService.addArtist(addArtistRequest);
//        Artist foundArtist = artistService.getArtistByName("Ben");
//        ArtWork artwork = artWorkService.addArtWorkToArtist(foundArtist.getName(),addArtWorkRequest);
//        String id = String.valueOf(artWorkService.getArtWorkById(artwork.getId()));
//        assertEquals(id,artwork.getId());
//    }
    @Test
    void testThatArtWorkCanBeUpdated(){

    }
}