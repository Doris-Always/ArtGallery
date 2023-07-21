package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.data.repository.ExhibitionRepository;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.exception.ArtistAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ArtistNotFoundException;
import com.affinityartgallary.artgallary.exception.ExhibitionAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ExhibitionNotFoundException;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    ArtistService artistService;
    @Autowired
    ExhibitionRepository exhibitionRepository;

    @Override
    public Exhibition addExhibition(String artistId, AddExhibitionRequest addExhibitionRequest) {
        Artist existingArtist = artistService.getArtistById(artistId);
        boolean exhibitionExisting = existingArtist.getExhibitions().stream()
                .anyMatch(exhibition -> exhibition.getExhibitionName().equals(addExhibitionRequest.getExhibitionName()));
        if (exhibitionExisting){
            throw new ExhibitionAlreadyExistException("this exhibition already exist");
        }
        Exhibition exhibition = Exhibition.builder()
                .exhibitionName(addExhibitionRequest.getExhibitionName())
                .imageUrl(addExhibitionRequest.getImageUrl())
                .year(addExhibitionRequest.getYear())
                .location(addExhibitionRequest.getLocation())
                .artistId(existingArtist.getId())
                .build();

        Exhibition savedExhibition = exhibitionRepository.save(exhibition);
        exhibition.setId(savedExhibition.getId());
        existingArtist.getExhibitions().add(exhibition);
        artistService.saveArtist(existingArtist);
        return savedExhibition;
    }

    @Override
    public List<Exhibition> getExhibitionByArtistId(String artistId) {
        Artist existingArtist = artistService.getArtistById(artistId);
        List<Exhibition> exhibitions = existingArtist.getExhibitions();
        return exhibitions;
    }

    @Override
    public Exhibition updateExhibition(String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) {
        Exhibition existingExhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new ExhibitionNotFoundException("exhibition not found "));
        update(updateExhibitionRequest,existingExhibition);
        Exhibition updatedExhibition = exhibitionRepository.save(existingExhibition);
        updateExhibitionInArtist(existingExhibition.getArtistId(),exhibitionId,updateExhibitionRequest);
        return updatedExhibition;

    }

    @Override
    public void removeExhibition(String exhibitionId) {
        Exhibition existingExhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new ExhibitionNotFoundException("exhbition not found"));
        var artist = existingExhibition.getArtistId();
        exhibitionRepository.delete(existingExhibition);
        removeExhibitionFromArtist(artist,exhibitionId);
    }
    private void removeExhibitionFromArtist(String artistId,String exhibitionId){
        Artist existingArtist = artistService.getArtistById(artistId);
        List<Exhibition> exhibitions = existingArtist.getExhibitions();
        Iterator<Exhibition> iterator = exhibitions.iterator();
        while (iterator.hasNext()) {
            Exhibition exhibition= iterator.next();
            if (exhibition.getId().equals(exhibitionId)) {
                iterator.remove();
                break;
            }
        }
        artistService.saveArtist(existingArtist);
    }

    private void updateExhibitionInArtist(String artistId, String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) {
        Artist existingArtist = artistService.getArtistById(artistId);
        List<Exhibition> exhibitions = existingArtist.getExhibitions();
        for (Exhibition exhibition : exhibitions){
            if (exhibition.getId().equals(exhibitionId)){
                update(updateExhibitionRequest,exhibition);
            }
        }
        artistService.saveArtist(existingArtist);
    }

    private void update(UpdateExhibitionRequest exhibitionRequest, Exhibition exhibition) {
        exhibition.setExhibitionName(exhibitionRequest.getExhibitionName() != null ? exhibitionRequest.getExhibitionName() : exhibition.getExhibitionName());
        exhibition.setImageUrl(exhibitionRequest.getImageUrl() != null ? exhibitionRequest.getImageUrl() : exhibition.getImageUrl());
        exhibition.setLocation(exhibitionRequest.getLocation() != null ? exhibitionRequest.getLocation() : exhibition.getLocation());
        exhibition.setYear(exhibitionRequest.getYear() != null ? exhibitionRequest.getYear() : exhibition.getYear());
    }
    @Override
    public List<Exhibition> getAllExhibitionByArtistId(String artistId) {
       var foundArtist = artistService.getArtistById(artistId);
        List<Exhibition> listOfExhibition = foundArtist.getExhibitions();
        return listOfExhibition;
    }
}
