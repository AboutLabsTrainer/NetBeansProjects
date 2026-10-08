package com.tutego.date4u.interfaces.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfileDto(
    Long id, String nickname, LocalDate birthdate, int manelength, int gender,
    Integer attractedToGender, String description, LocalDateTime lastseen
) { }