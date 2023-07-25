package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;
import com.affinityartgallary.artgallary.dto.response.AddArtistResponse;
import com.affinityartgallary.artgallary.dto.response.UpdaterArtistResponse;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ArtistServiceTest {
    AddArtistRequest addArtistRequest;
    @BeforeEach
    void setUp(){
        addArtistRequest = new AddArtistRequest();
        addArtistRequest.setName("Ben");
        addArtistRequest.setYearOfBirth("1904");
        addArtistRequest.setArtistBio("the work is good");
//        addArtistRequest.setImageUrl("www.cloudinary.com");

    }

    @Autowired
    ArtistService artistService;


    @Test
    void addOneArtistTestThatAnArtistExist() throws IOException {
        AddArtistResponse artist =  artistService.addArtist(addArtistRequest);
        assertEquals(artist.getName(),addArtistRequest.getName());
        assertEquals(artist.getMessage(),"Ben added successfully");
    }
    @Test
    void testThatICanFindAnArtistById() throws IOException {
        AddArtistResponse artist =  artistService.addArtist(addArtistRequest);
        assertEquals(artist.getName(),addArtistRequest.getName());
        var artistId = artist.getId();
        var addedArtist = artistService.getArtistById(artistId);
        assertEquals(artistId,addedArtist.getId());
    }

    @Test
    void testThatAnArtistInformationCanBeUpdated() throws IOException {
        AddArtistResponse artist =  artistService.addArtist(addArtistRequest);
        UpdateArtistRequest updateArtist = new UpdateArtistRequest();
        updateArtist.setName("Benny");
        updateArtist.setYearOfBirth("1234");
        UpdaterArtistResponse updatedArtist = artistService.updateArtistInformation(artist.getId(), updateArtist);
        assertEquals(artist.getId(),updatedArtist.getId());

    }
    @Test
    void testThatAnArtistCanBeRemovedByName() throws IOException {
        AddArtistResponse artist =  artistService.addArtist(addArtistRequest);
        String artistName = artist.getName();
        artistService.removeArtistByName(artistName);
        assertNull(artistService.getArtistByName(artistName));
    }

}
