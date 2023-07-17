package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.repository.ArtWorkRepository;
import com.affinityartgallary.artgallary.data.repository.ArtistRepository;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtWorkRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ArtWorkNotFoundException;
import com.affinityartgallary.artgallary.exception.ArtistAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ArtistNotFoundException;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public ArtWork addArtWorkToArtist(String artistName,AddArtWorkRequest addArtWorkRequest)  {
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
                .imageUrl(addArtWorkRequest.getImageUrl())
                .artistId(existingArtist.getId())
                .build();
       ArtWork savedArtwork =  artWorkRepository.save(artWork);
       artWork.setId(savedArtwork.getId());

        existingArtist.getArtWorkList().add(artWork);
        artistService.saveArtist(existingArtist);

        return savedArtwork;

    }

    @Override
    public Optional<ArtWork> getArtWorkById(String id) {
        return Optional.ofNullable(artWorkRepository.findById(id).orElseThrow(() -> new ArtWorkNotFoundException("artwork does not exist")));
    }

    @Override
    public ArtWork updateArtWork(String artWorkId,UpdateArtWorkRequest updateArtWorkRequest) {
        ArtWork existingArtWork = artWorkRepository.findById(artWorkId).orElseThrow(()->new ArtWorkNotFoundException("artwork not found"));
        existingArtWork.setTitle(updateArtWorkRequest.getTitle());
        existingArtWork.setDimension(updateArtWorkRequest.getDimension());
        existingArtWork.setMedium(updateArtWorkRequest.getMedium());
        existingArtWork.setImageUrl(updateArtWorkRequest.getImageUrl());
        ArtWork updatedArtwork = artWorkRepository.save(existingArtWork);

        updateArtWorkInArtist(updatedArtwork.getId(), updatedArtwork.getArtistId(), updateArtWorkRequest);

        return updatedArtwork;
    }


    private void updateArtWorkInArtist(String artWorkId, String artistId, UpdateArtWorkRequest updateArtWorkRequest) {
       Artist existingArtist = artistService.getArtistById(artistId);
       List<ArtWork> artWorks = existingArtist.getArtWorkList();

       for(ArtWork artWork: artWorks){
           if (artWork.getId().equals(artWorkId)){
               artWork.setTitle(updateArtWorkRequest.getTitle());
               artWork.setDimension(updateArtWorkRequest.getDimension());
               artWork.setMedium(updateArtWorkRequest.getMedium());
               artWork.setImageUrl(updateArtWorkRequest.getImageUrl());
           }
       }

        artistService.saveArtist(existingArtist);


    }
    @Override
    public void removeArtWork(String artWorkId) {
        ArtWork existingArtWork = artWorkRepository.findById(artWorkId).orElseThrow(()->new ArtWorkNotFoundException("artwork not found"));
         var artist = existingArtWork.getArtistId();
        artWorkRepository.delete(existingArtWork);
        removeArtWorkFromArtist(artist,existingArtWork.getId());

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
