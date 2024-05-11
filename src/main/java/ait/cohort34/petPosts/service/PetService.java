package ait.cohort34.petPosts.service;

import ait.cohort34.petPosts.dto.NewPetDto;
import ait.cohort34.petPosts.dto.PetDto;
import ait.cohort34.petPosts.dto.UpdatePetDto;

public interface PetService {
    PetDto addNewPet(String login,NewPetDto newPetDto);
    Iterable<PetDto> findPetByType(String type);
    Iterable<PetDto> findAllPets();
    PetDto updatePet(Long  id, UpdatePetDto updatePetDto);
    PetDto removePetById(Long  id);

    Iterable<PetDto> findPetsByFilter(String petType, String age, String gender, String country, String category, Boolean disability, String author);

    PetDto findPetById(Long id);
}
