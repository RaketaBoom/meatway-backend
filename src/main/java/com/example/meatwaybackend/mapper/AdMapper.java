package com.example.meatwaybackend.mapper;

import com.example.meatwaybackend.dto.ad.ShortAdResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.bird.BirdAdResponse;
import com.example.meatwaybackend.dto.ad.bird.BirdAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdResponse;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdSaveRequest;
import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.ad.Beef;
import com.example.meatwaybackend.model.ad.Bird;
import com.example.meatwaybackend.model.ad.Pork;
import com.example.meatwaybackend.model.ad.Sheepmeat;
import com.example.meatwaybackend.model.ad.Specialmeat;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {
    ShortAdResponse toShortAdvertisementResponse(Advertisement ad);

    List<ShortAdResponse> toListShortAdvertisementResponse(List<Advertisement> ads);

    ShortAdResponse toShortBeefAdResponse(Beef ad);

    List<ShortAdResponse> toListShortBeefAdResponse(List<Beef> ads);
    BeefAdResponse toBeefAdResponse(Beef ad);
    Beef toBeef(BeefAdSaveRequest request);
    Beef toBeef(BeefAdResponse request);

    ShortAdResponse toShortSheepmeatAdResponse(Sheepmeat ad);

    List<ShortAdResponse> toListShortSheepmeatAdResponse(List<Sheepmeat> ads);

    ShortAdResponse toShortBirdAdResponse(Bird ad);

    List<ShortAdResponse> toListShortBirdAdResponse(List<Bird> ads);

    ShortAdResponse toShortSpecialmeatAdResponse(Specialmeat ad);

    List<ShortAdResponse> toListShortSpecialmeatAdResponse(List<Specialmeat> ads);

    ShortAdResponse toShortPorkAdResponse(Pork ad);

    List<ShortAdResponse> toListShortPorkAdResponse(List<Pork> ads);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Beef updateBeefFromBeefAdSaveRequest(@MappingTarget Beef beef, BeefAdSaveRequest request);

    BirdAdResponse toBirdAdResponse(Bird bird);

    Bird toBird(BirdAdSaveRequest request);
    Bird toBird(BirdAdResponse request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Bird updateBirdFromBirdAdSaveRequest(@MappingTarget Bird bird, BirdAdSaveRequest request);

    PorkAdResponse toPorkAdResponse(Pork pork);

    Pork toPork(PorkAdSaveRequest request);
    Pork toPork(PorkAdResponse request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pork updatePorkFromPorkAdSaveRequest(@MappingTarget Pork pork, PorkAdSaveRequest request);

    SheepmeatAdResponse toSheepmeatAdResponse(Sheepmeat sheepmeat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sheepmeat updateSheepmeatFromSheepmeatAdSaveRequest(@MappingTarget Sheepmeat sheepmeat, SheepmeatAdSaveRequest request);


    Sheepmeat toSheepmeat(SheepmeatAdSaveRequest request);
    Sheepmeat toSheepmeat(SheepmeatAdResponse request);

    SpecialmeatAdResponse toSpecialmeatAdResponse(Specialmeat specialmeat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Specialmeat updateSpecialmeatFromSpecialmeatAdSaveRequest(@MappingTarget Specialmeat specialmeat, SpecialmeatAdSaveRequest request);

    Specialmeat toSpecialmeat(SpecialmeatAdSaveRequest request);
    Specialmeat toSpecialmeat(SpecialmeatAdResponse response);
}

