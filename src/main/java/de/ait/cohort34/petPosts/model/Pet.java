package de.ait.cohort34.petPosts.model;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    String id;
    String caption;
    String author;
    String type;
    String category;
    String breed;
    String gender;
    String age;//String
    Boolean disability;
    @ElementCollection
    Set<String> photo;
    String country;
    String city;
    String description;
    LocalDate dateCreate  = LocalDate.now();
    LocalDate deadline = LocalDate.now().plusMonths(3);

    public Pet(String id,String caption, String type, String description, String city, String country, Set<String> photo, Boolean disability, String age, String gender, String breed, String category) {
        this.id = id;
        this.caption = caption;
        this.type = type;
        this.description = description;
        this.city = city;
        this.country = country;
        this.photo = photo;
        this.disability = disability;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.category = category;
    }
    //добавить метод по продлению дедлайна
    public LocalDate plusDeadline() {
        return deadline.plusMonths(3);
    }
}
