package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.model.Category;
import com.affinityartgallary.artgallary.data.model.Exhibition;
import com.affinityartgallary.artgallary.data.repository.ArtistRepository;
import com.affinityartgallary.artgallary.dto.request.AddArtistRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateArtistRequest;
import com.affinityartgallary.artgallary.exception.ArtistAlreadyExistException;
import com.affinityartgallary.artgallary.exception.ArtistNotFoundException;
import com.affinityartgallary.artgallary.services.ArtistService;
import com.affinityartgallary.artgallary.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    private FileUploadService fileUploadService;
    @Override
    public Artist addArtist(AddArtistRequest addArtistRequest) throws IOException {
       Optional<Artist> existingArtist = Optional.ofNullable(artistRepository.findByName(addArtistRequest.getName()));
        if (existingArtist.isPresent()){
            throw new ArtistAlreadyExistException("User already exist");

        }

        Artist artist = Artist.builder()
                .name(addArtistRequest.getName())
                .artistBio(addArtistRequest.getArtistBio())
                .yearOfBirth(addArtistRequest.getYearOfBirth())
//                .category(Category.valueOf(addArtistRequest.getCategory().toUpperCase()))
                .artWorkList(new ArrayList<>())
                .exhibitions(new ArrayList<>())
                .build();
                Artist savedArtist = artistRepository.save(artist);
                String imageUrl = fileUploadService.uploadFile(addArtistRequest.getImage(), savedArtist.getId());
                 savedArtist.setImage(imageUrl);

        return artistRepository.save(savedArtist);

    }

    @Override
    public Artist getArtistById(String artistId) {
        return artistRepository.findById(artistId).orElseThrow(()->new ArtistNotFoundException("This Artist does not exist"));
    }

    @Override
    public Artist updateArtistInformation(String id,UpdateArtistRequest updateArtist) throws IOException {
        Artist existingArtist = artistRepository.findById(id).orElseThrow(()->new ArtistNotFoundException("artist not found"));
        existingArtist.setName(updateArtist.getName() != null ? updateArtist.getName() : existingArtist.getName());
        existingArtist.setArtistBio(updateArtist.getArtistBio() != null ? updateArtist.getArtistBio() : existingArtist.getArtistBio());
        String imageUrl = fileUploadService.uploadFile(updateArtist.getImage(), existingArtist.getId());
        existingArtist.setImage(imageUrl);
        existingArtist.setImage(updateArtist.getImage() != null ? imageUrl : existingArtist.getImage());
        existingArtist.setYearOfBirth(updateArtist.getYearOfBirth() != null ? updateArtist.getYearOfBirth() : existingArtist.getYearOfBirth());
//        existingArtist.setCategory(updateArtist.getCategory() != null ? updateArtist.getCategory() : existingArtist.getCategory());
        return artistRepository.save(existingArtist);
    }
    @Override
    public Artist getArtistByName(String artistName){
        return artistRepository.findByName(artistName);
    }

    @Override
    public Artist saveArtist(Artist existingArtist) {
        return artistRepository.save(existingArtist);
    }

    @Override
    public List<ArtWork> getAllArtWorkByArtistId(String artistId) {
        var foundArtist = artistRepository.findById(artistId).orElseThrow(()->new ArtistNotFoundException("artist does not exist"));
       List<ArtWork> listOfArtWorks = foundArtist.getArtWorkList();
       return listOfArtWorks;
    }


    @Override
    public void removeArtistByName(String artistName) {
        Artist artist = artistRepository.findByName(artistName);
        artistRepository.delete(artist);
    }


}
