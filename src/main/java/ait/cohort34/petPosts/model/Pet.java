package ait.cohort34.petPosts.model;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  id;
    String caption;
    String author;
    String petType;
    String category;
    String gender;
    String age;
    Boolean disability;
    @ElementCollection
    Set<String> photo;
    String country;
    String city;
    String description;
    LocalDate dateCreate  = LocalDate.now();
    LocalDate deadline = LocalDate.now().plusMonths(3);

    public Pet(String caption, String type, String description, String city, String country, Set<String> photo, Boolean disability, String age, String gender, String category) {
        this.caption = caption;
        this.petType = type;
        this.description = description;
        this.city = city;
        this.country = country;
        this.photo = photo;
        this.disability = disability;
        this.age = age;
        this.gender = gender;
        this.category = category;
    }
}
