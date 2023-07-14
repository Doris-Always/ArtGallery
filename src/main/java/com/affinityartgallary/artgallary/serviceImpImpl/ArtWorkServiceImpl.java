package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.Artist;
import com.affinityartgallary.artgallary.data.repository.ArtWorkRepository;
import com.affinityartgallary.artgallary.dto.request.AddArtWorkRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkNotFoundException;
import com.affinityartgallary.artgallary.exception.ArtistNotFoundException;
import com.affinityartgallary.artgallary.services.ArtWorkService;
import com.affinityartgallary.artgallary.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {
    @Autowired
    ArtistService artistService;

    @Autowired
    ArtWorkRepository artWorkRepository;
    @Override
    public ArtWork addArtWorkToArtist(String artistName,AddArtWorkRequest addArtWorkRequest) {
        Artist existingArtist = artistService.getArtistByName(artistName);
        if (existingArtist.getName() == null){
           throw new ArtistNotFoundException("artist not found");

        }
        ArtWork artWork = ArtWork.builder()
                .medium(addArtWorkRequest.getMedium())
                .dimension(addArtWorkRequest.getDimension())
                .title(addArtWorkRequest.getTitle())
                .imageUrl(addArtWorkRequest.getImageUrl())
                .artistId(existingArtist.getId())
                .build();

        existingArtist.getArtWorkList().add(artWork);
        artistService.saveArtist(existingArtist);
        return artWorkRepository.save(artWork);

    }

    @Override
    public Optional<ArtWork> getArtWorkById(String id) {
        return Optional.ofNullable(artWorkRepository.findById(id).orElseThrow(() -> new ArtWorkNotFoundException("artwork does not exist")));
    }
}
