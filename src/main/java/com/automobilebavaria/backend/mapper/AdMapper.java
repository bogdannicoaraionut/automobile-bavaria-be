package com.automobilebavaria.backend.mapper;

import com.automobilebavaria.backend.dto.AdDTO;
import com.automobilebavaria.backend.dto.CreateAdRequest;
import com.automobilebavaria.backend.entity.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdMapper {

    public abstract Ad toAd(CreateAdRequest createAdRequest);

    public abstract List<AdDTO> toAdDTOs(List<Ad> ads);

    public abstract AdDTO toAdDTO(Ad ad);

}
