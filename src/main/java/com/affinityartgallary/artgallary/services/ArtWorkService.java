package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;

import java.util.Optional;

public interface ArtWorkService {
    ArtWork addArtWorkToArtist(String artistName, AddArtWorkRequest addArtWorkRequest) throws ArtWorkAlreadyExistException;
    Optional<ArtWork> getArtWorkById(String artWorkId);

    ArtWork updateArtWork(String id,UpdateArtWorkRequest updateArtWorkRequest);

    public void removeArtWork(String artWorkId);


}
