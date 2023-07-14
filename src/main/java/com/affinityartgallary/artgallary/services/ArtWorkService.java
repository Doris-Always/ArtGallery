package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;

import java.util.Optional;

public interface ArtWorkService {
    ArtWork addArtWorkToArtist(String artistName, AddArtWorkRequest addArtWorkRequest);
    Optional<ArtWork> getArtWorkById(String artistName);
}
