package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.dto.response.AddExhibitionResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateExhibitionResponse;

import java.io.IOException;
import java.util.List;

public interface ExhibitionService {
     AddExhibitionResponse addExhibition(String artistId, AddExhibitionRequest addExhibitionRequest) throws IOException;
     List<Exhibition> getExhibitionByArtistId(String artistId);

    UpdateExhibitionResponse updateExhibition(String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) throws IOException;

     void removeExhibition(String exhibitionId);
     List<Exhibition> getAllExhibitionByArtistId(String artistId);
     List<Exhibition> getAllExhibition();

}
