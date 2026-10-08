package com.tutego.date4u.interfaces.rest;

import com.tutego.date4u.core.profile.Profile;

class ProfileMapper {
  ProfileDto convert( Profile p ) {
    return new ProfileDto(
        p.getId(), p.getNickname(), p.getBirthdate(), p.getManelength(),
        p.getGender(), p.getAttractedToGender(), p.getDescription(),
        p.getLastseen()
    );
  }
}

//import com.tutego.date4u.core.profile.Profile;
//import org.mapstruct.*;
//import org.springframework.core.convert.converter.Converter;
//
//@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
//public interface ProfileMapper extends Converter<Profile, ProfileDto> {
//  // @Mapping(target = "ABC", source = "RST")
//  // @Mapping(target = "DEF", source = "XYZ")
//  ProfileDto convert( Profile profile );
//}