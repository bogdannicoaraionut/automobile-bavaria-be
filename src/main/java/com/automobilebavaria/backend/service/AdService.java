package com.automobilebavaria.backend.service;


import com.automobilebavaria.backend.dto.AdDTO;
import com.automobilebavaria.backend.dto.CreateAdRequest;
import com.automobilebavaria.backend.entity.Ad;
import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.exception.EntityNotFoundAlertException;
import com.automobilebavaria.backend.mapper.AdMapper;
import com.automobilebavaria.backend.repository.AdRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @NonNull
    public AdDTO createAd(@NonNull CreateAdRequest request, @NonNull User user) {
        Ad ad = adMapper.toAd(request);
        ad.setUser(user);
        ad.setCreatedAt(Instant.now());

        adRepository.save(ad);
        return adMapper.toAdDTO(ad);
    }

    @NonNull
    public AdDTO getAd(@NonNull Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundAlertException("Ad not found for id: " + id, "error"));
        return adMapper.toAdDTO(ad);
    }

    @NonNull
    public List<AdDTO> getUserAds(@NonNull User user) {
        List<Ad> ads = adRepository.findAllByUserId(user.getId());
        return adMapper.toAdDTOs(ads);
    }

    @NonNull
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return adMapper.toAdDTOs(ads);
    }

    @NonNull
    public void deleteAd(@NonNull Long id) {
        adRepository.deleteById(id);
    }


}
