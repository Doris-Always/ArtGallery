package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.data.repository.ExhibitionRepository;
import com.affinityartgallary.artgallary.dto.request.AddExhibitionRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateExhibitionRequest;
import com.affinityartgallary.artgallary.dto.response.AddExhibitionResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateExhibitionResponse;
import com.affinityartgallary.artgallary.exception.ExhibitionAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ExhibitionNotFoundException;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.ExhibitionService;
import com.affinityartgallary.artgallary.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    ArtistService artistService;
    @Autowired
    ExhibitionRepository exhibitionRepository;
    @Autowired
    FileUploadService fileUploadService;

    @Override
    public AddExhibitionResponse addExhibition(String artistId, AddExhibitionRequest addExhibitionRequest) throws IOException {
        Artist existingArtist = artistService.getArtistById(artistId);
        boolean exhibitionExisting = existingArtist.getExhibitions().stream()
                .anyMatch(exhibition -> exhibition.getExhibitionName().equals(addExhibitionRequest.getExhibitionName()));
        if (exhibitionExisting){
            throw new ExhibitionAlreadyExistException("this exhibition already exist");
        }
        Exhibition exhibition = Exhibition.builder()
                .exhibitionName(addExhibitionRequest.getExhibitionName())
                .year(addExhibitionRequest.getYear())
                .location(addExhibitionRequest.getLocation())
                .artistId(existingArtist.getId())
                .build();

        Exhibition savedExhibition = exhibitionRepository.save(exhibition);
        String imageUrl = fileUploadService.uploadFile(addExhibitionRequest.getImage(), savedExhibition.getId());
        savedExhibition.setImage(imageUrl);
        exhibitionRepository.save(savedExhibition);
        exhibition.setId(savedExhibition.getId());
        existingArtist.getExhibitions().add(exhibition);
        artistService.saveArtist(existingArtist);
        AddExhibitionResponse addExhibitionResponse = new AddExhibitionResponse();
        addExhibitionResponse.setId(savedExhibition.getId());
        addExhibitionResponse.setExhibitionName(savedExhibition.getExhibitionName());
        addExhibitionResponse.setArtistId(existingArtist.getId());
        return addExhibitionResponse;
    }

    @Override
    public List<Exhibition> getExhibitionByArtistId(String artistId) {
        Artist existingArtist = artistService.getArtistById(artistId);
        List<Exhibition> exhibitions = existingArtist.getExhibitions();
        return exhibitions;
    }

    @Override
    public UpdateExhibitionResponse updateExhibition(String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) throws IOException {
        Exhibition existingExhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new ExhibitionNotFoundException("exhibition not found "));
        update(updateExhibitionRequest,existingExhibition);
        Exhibition updatedExhibition = exhibitionRepository.save(existingExhibition);
        updateExhibitionInArtist(existingExhibition.getArtistId(),exhibitionId,updateExhibitionRequest);
        UpdateExhibitionResponse updateExhibitionResponse = new UpdateExhibitionResponse();
        updateExhibitionResponse.setExhibitionName(updatedExhibition.getExhibitionName());
        updateExhibitionResponse.setId(updatedExhibition.getId());
        return updateExhibitionResponse;

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

    private void updateExhibitionInArtist(String artistId, String exhibitionId, UpdateExhibitionRequest updateExhibitionRequest) throws IOException {
        Artist existingArtist = artistService.getArtistById(artistId);
        List<Exhibition> exhibitions = existingArtist.getExhibitions();
        for (Exhibition exhibition : exhibitions){
            if (exhibition.getId().equals(exhibitionId)){
                update(updateExhibitionRequest,exhibition);
            }
        }
        artistService.saveArtist(existingArtist);
    }




    private void update(UpdateExhibitionRequest exhibitionRequest, Exhibition exhibition) throws IOException {
        exhibition.setExhibitionName(exhibitionRequest.getExhibitionName() != null ? exhibitionRequest.getExhibitionName() : exhibition.getExhibitionName());
        MultipartFile image = exhibitionRequest.getImage();
        String imageUrl =(image != null && !image.isEmpty()) ?
                fileUploadService.uploadFile(exhibitionRequest.getImage(), exhibition.getId()) : exhibition.getImage();
        exhibition.setImage(imageUrl);
        exhibition.setLocation(exhibitionRequest.getLocation() != null ? exhibitionRequest.getLocation() : exhibition.getLocation());
        exhibition.setYear(exhibitionRequest.getYear() != null ? exhibitionRequest.getYear() : exhibition.getYear());
    }
    @Override
    public List<Exhibition> getAllExhibitionByArtistId(String artistId) {
       var foundArtist = artistService.getArtistById(artistId);
        List<Exhibition> listOfExhibition = foundArtist.getExhibitions();
        return listOfExhibition;
    }

    @Override
    public List<Exhibition> getAllExhibition() {
        return exhibitionRepository.findAll();
    }
}
