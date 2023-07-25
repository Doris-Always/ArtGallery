package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.repository.ArtWorkRepository;
import com.affinityartgallary.artgallary.data.repository.ArtistRepository;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.dto.response.AddArtWorkResponse;
import com.affinityartgallary.artgallary.dto.response.UpdateArtWorkResponse;
import com.affinityartgallary.artgallary.exception.ArtWorkNotFoundException;
import com.affinityartgallary.artgallary.exception.ArtistAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ArtistNotFoundException;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {
    @Autowired
    ArtistService artistService;

    @Autowired
    ArtWorkRepository artWorkRepository;
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    FileUploadService fileUploadService;
    @Override
    public AddArtWorkResponse addArtWorkToArtist(String artistName, AddArtWorkRequest addArtWorkRequest) throws IOException {
        Artist existingArtist = artistService.getArtistByName(artistName);
        if (existingArtist.getName() == null){
           throw new ArtistNotFoundException("artist not found");

        }
        boolean artWorkExists = existingArtist.getArtWorkList().stream()
                .anyMatch(artWork -> artWork.getTitle().equals(addArtWorkRequest.getTitle()));
        if (artWorkExists){
            throw new ArtistAlreadyExistException("artwork already exist");
        }

        ArtWork artWork = ArtWork.builder()
                .medium(addArtWorkRequest.getMedium())
                .dimension(addArtWorkRequest.getDimension())
                .title(addArtWorkRequest.getTitle())
                .artistId(existingArtist.getId())
                .build();
       ArtWork savedArtwork =  artWorkRepository.save(artWork);
       String imageUrl = fileUploadService.uploadFile(addArtWorkRequest.getImage(),savedArtwork.getId());
       savedArtwork.setImage(imageUrl);
       artWorkRepository.save(savedArtwork);
       artWork.setId(savedArtwork.getId());

        existingArtist.getArtWorkList().add(artWork);
        artistService.saveArtist(existingArtist);

        AddArtWorkResponse addArtWorkResponse = new AddArtWorkResponse();
        addArtWorkResponse.setArtistId(savedArtwork.getArtistId());
        addArtWorkResponse.setId(artWork.getId());
        return addArtWorkResponse;

    }

    @Override
    public Optional<ArtWork> getArtWorkById(String id) {
        return Optional.ofNullable(artWorkRepository.findById(id).orElseThrow(() -> new ArtWorkNotFoundException("artwork does not exist")));
    }

    @Override
    public UpdateArtWorkResponse updateArtWork(String artWorkId, UpdateArtWorkRequest updateArtWorkRequest) throws IOException {
        ArtWork existingArtWork = artWorkRepository.findById(artWorkId).orElseThrow(()->new ArtWorkNotFoundException("artwork not found"));
        update(updateArtWorkRequest, existingArtWork);
        ArtWork updatedArtwork = artWorkRepository.save(existingArtWork);
        updateArtWorkInArtist(updatedArtwork.getId(), updatedArtwork.getArtistId(), updateArtWorkRequest);
        UpdateArtWorkResponse updateArtWorkResponse = new UpdateArtWorkResponse();
        updateArtWorkResponse.setId(updatedArtwork.getId());
        updateArtWorkResponse.setTitle(updateArtWorkResponse.getTitle());
        return updateArtWorkResponse;
    }


    private void updateArtWorkInArtist(String artWorkId, String artistId, UpdateArtWorkRequest updateArtWorkRequest) throws IOException {
       Artist existingArtist = artistService.getArtistById(artistId);
       List<ArtWork> artWorks = existingArtist.getArtWorkList();

       for(ArtWork artWork: artWorks){
           if (artWork.getId().equals(artWorkId)){
               update(updateArtWorkRequest, artWork);
           }
       }

        artistService.saveArtist(existingArtist);


    }

    private void update(UpdateArtWorkRequest updateArtWorkRequest, ArtWork artWork) throws IOException {
        artWork.setTitle(updateArtWorkRequest.getTitle() != null ? updateArtWorkRequest.getTitle() : artWork.getTitle());
        artWork.setDimension(updateArtWorkRequest.getDimension() != null ? updateArtWorkRequest.getDimension() : artWork.getDimension());
        artWork.setMedium(updateArtWorkRequest.getMedium() != null ? updateArtWorkRequest.getMedium() : artWork.getMedium());

        MultipartFile image = updateArtWorkRequest.getImage();
        String imageUrl = (image != null && !image.isEmpty() ?
                fileUploadService.uploadFile(updateArtWorkRequest.getImage(), artWork.getId()) : artWork.getImage());
        artWork.setImage(imageUrl);


    }

    @Override
    public void removeArtWork(String artWorkId) {
        ArtWork existingArtWork = artWorkRepository.findById(artWorkId).orElseThrow(()->new ArtWorkNotFoundException("artwork not found"));
         var artist = existingArtWork.getArtistId();
        artWorkRepository.delete(existingArtWork);
        removeArtWorkFromArtist(artist,existingArtWork.getId());

    }

    @Override
    public List<ArtWork> getAllArtWork() {
        return artWorkRepository.findAll();
    }

    private void removeArtWorkFromArtist(String artistId,String artWorkId){
        Artist existingArtist =  artistService.getArtistById(artistId);
        List<ArtWork> artWorkList = existingArtist.getArtWorkList();
        Iterator<ArtWork> iterator = artWorkList.iterator();
        while (iterator.hasNext()) {
            ArtWork artWork = iterator.next();
            if (artWork.getId().equals(artWorkId)) {
                iterator.remove();
                break;
            }
        }
        artistService.saveArtist(existingArtist);
    }



}
