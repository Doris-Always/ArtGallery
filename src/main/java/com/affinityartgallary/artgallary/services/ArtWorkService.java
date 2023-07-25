package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.response.AddArtWorkResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateArtWorkResponse;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArtWorkService {
    AddArtWorkResponse addArtWorkToArtist(String artistName, AddArtWorkRequest addArtWorkRequest) throws ArtWorkAlreadyExistException, IOException;
    Optional<ArtWork> getArtWorkById(String artWorkId);

   UpdateArtWorkResponse updateArtWork(String id, UpdateArtWorkRequest updateArtWorkRequest) throws IOException;

    public void removeArtWork(String artWorkId);
    public List<ArtWork> getAllArtWork();


}
