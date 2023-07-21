package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface ExhibitionService {
     Exhibition addExhibition(String artistId, AddExhibitionRequest addExhibitionRequest);
     List<Exhibition> getExhibitionByArtistId(String artistId);

     Exhibition updateExhibition(String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest);

     void removeExhibition(String exhibitionId);
     public List<Exhibition> getAllExhibitionByArtistId(String artistId);
}
