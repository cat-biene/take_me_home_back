package de.ait.cohort34.petPosts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewPetDto {
    String id;
    String caption;
    String author;
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
}
