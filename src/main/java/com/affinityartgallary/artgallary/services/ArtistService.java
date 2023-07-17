package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;

import java.util.List;

public interface ArtistService {

    Artist addArtist(AddArtistRequest addArtistRequest);

    Artist getArtistById(String artistId);

    Artist updateArtistInformation(String id,UpdateArtistRequest updateArtist);

    void removeArtistByName(String artistName);
    Artist getArtistByName(String artistName);

    Artist saveArtist(Artist existingArtist);

    List<ArtWork> getAllArtWorkByArtistId(String artistId);


}
