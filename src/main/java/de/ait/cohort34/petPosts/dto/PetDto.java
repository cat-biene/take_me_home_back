package de.ait.cohort34.petPosts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    String id;
    String caption;
    String type;
    String category;
    String breed;
    String gender;
    String age;
    Boolean disability;
    Set<String> photo;
    String country;
    String city;
    String description;
    LocalDate dateCreate;
    LocalDate dateDeadline;
}
