package com.automobilebavaria.backend.mapper;

import com.automobilebavaria.backend.dto.CarMakerDTO;
import com.automobilebavaria.backend.dto.CarModelDTO;
import com.automobilebavaria.backend.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CarMapper {

    public abstract List<CarMakerDTO> toCarMakerDTOs(List<Car> cars);

    public abstract CarMakerDTO toCarMakerDTO(Car car);

    public abstract List<CarModelDTO> toCarModelDTOs(List<Car> cars);

    public abstract CarModelDTO toCarModelDTO(Car car);
}
