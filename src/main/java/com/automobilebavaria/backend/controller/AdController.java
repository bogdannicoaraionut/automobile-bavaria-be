package com.automobilebavaria.backend.controller;

import com.automobilebavaria.backend.dto.AdDTO;
import com.automobilebavaria.backend.dto.CreateAdRequest;
import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.security.AuthenticatedUser;
import com.automobilebavaria.backend.service.AdService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AdController {

    private final AdService adService;

    @PostMapping("/ads")
    public ResponseEntity<AdDTO> createAd(@Valid @RequestBody CreateAdRequest request,
                                          @Parameter(hidden = true) @AuthenticatedUser User user) {
        return ResponseEntity.ok().body(adService.createAd(request, user));
    }

    @GetMapping("/ads")
    public ResponseEntity<List<AdDTO>> getAllAds() {
        return ResponseEntity.ok().body(adService.getAllAds());
    }

    @GetMapping("users/ads")
    public ResponseEntity<List<AdDTO>> getUserAds(@Parameter(hidden = true) @AuthenticatedUser User user) {
        return ResponseEntity.ok().body(adService.getUserAds(user));
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<AdDTO> getAd(@PathVariable Long id) {
        return ResponseEntity.ok().body(adService.getAd(id));
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

}
