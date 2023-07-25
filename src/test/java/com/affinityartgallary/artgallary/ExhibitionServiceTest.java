package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.dto.response.AddExhibitionResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateExhibitionResponse;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.ExhibitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExhibitionServiceTest {
    @Autowired
    ArtistService artistService;

    @Autowired
    ExhibitionService exhibitionService;

    AddArtistRequest addArtistRequest;
    AddExhibitionRequest addExhibitionRequest;

    UpdateExhibitionRequest updateExhibitionRequest;
    @BeforeEach
    void setUp(){
        addArtistRequest = new AddArtistRequest();
        addArtistRequest.setName("Ben");
        addArtistRequest.setYearOfBirth("1904");
        addArtistRequest.setArtistBio("the work is good");
//        addArtistRequest.setImage(MultipartFile);

        addExhibitionRequest = new AddExhibitionRequest();
        addExhibitionRequest.setExhibitionName("The things left unsaid,2023");
//        addExhibitionRequest.setImageUrl("www.cloudinary.com");
        addExhibitionRequest.setLocation("Lagos,Nigeria");
        addExhibitionRequest.setYear("2023");

        updateExhibitionRequest = new UpdateExhibitionRequest();
        updateExhibitionRequest.setExhibitionName("The things left unsaid,2021");
        updateExhibitionRequest.setLocation("Enugu,Nigeria");
        updateExhibitionRequest.setYear("2023");
//        updateExhibitionRequest.setImageUrl("www.cloudinary.com");

    }

    @Test
    void testThatExhibitionCanBeAddedToArtist() throws IOException {
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        AddExhibitionResponse exhibition = exhibitionService.addExhibition(foundArtist.getId(),addExhibitionRequest);
        assertEquals(exhibition.getArtistId(),foundArtist.getId());

    }
    @Test
    void testThatAnExhibitionCanBeFoundByArtistId() throws IOException {
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        AddExhibitionResponse exhibition = exhibitionService.addExhibition(foundArtist.getId(),addExhibitionRequest);
        List<Exhibition> exhibitions = exhibitionService.getExhibitionByArtistId(foundArtist.getId());
        assertEquals(1,exhibitions.size());
    }
    @Test
    void testThatExhibitionCanBeUpdated() throws IOException {
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        AddExhibitionResponse exhibition = exhibitionService.addExhibition(foundArtist.getId(),addExhibitionRequest);
       UpdateExhibitionResponse updatedExhibition =  exhibitionService.updateExhibition(exhibition.getId(),updateExhibitionRequest);
        assertEquals(updatedExhibition.getExhibitionName(),"The things left unsaid,2021");
    }
    @Test
    void testThatExhibitionCanBeDeleted() throws IOException {
        artistService.addArtist(addArtistRequest);
        Artist foundArtist = artistService.getArtistByName("Ben");
        AddExhibitionResponse exhibition = exhibitionService.addExhibition(foundArtist.getId(),addExhibitionRequest);
        exhibitionService.removeExhibition(exhibition.getId());
        assertEquals(0,foundArtist.getExhibitions().size());

    }
   

}
