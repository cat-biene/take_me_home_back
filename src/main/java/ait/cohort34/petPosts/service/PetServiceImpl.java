package ait.cohort34.petPosts.service;

import ait.cohort34.petPosts.dao.PetRepository;
import ait.cohort34.petPosts.dto.NewPetDto;
import ait.cohort34.petPosts.dto.PetDto;
import ait.cohort34.petPosts.dto.UpdatePetDto;
import ait.cohort34.petPosts.dto.exseption.PetNotFoundException;
import ait.cohort34.petPosts.model.Pet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    final ModelMapper modelMapper;
    final PetRepository petRepository;


    @Override
    public PetDto addNewPet(String login,NewPetDto NewPetDto) {
        Pet pet = modelMapper.map(NewPetDto, Pet.class);
        pet.setAuthor(login);
        petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }
    @Transactional(readOnly = true)
    @Override
    public Iterable<PetDto> findPetByType(String type) {
        return petRepository.findByPetTypeIgnoreCase(type)
                .map(s->modelMapper.map(s,PetDto.class))
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public Iterable<PetDto> findPetsByFilter(String petType, String age, String gender, String country, String category, Boolean disability, String author) {
        return petRepository.findPetsByFilter(petType, age, gender, country, category,  disability, author)
                .map(pet -> modelMapper.map(pet, PetDto.class))
                .toList();
    }

    @Override
    public PetDto findPetById(Long id) {
        Pet pet= petRepository.findById(id).orElseThrow(PetNotFoundException::new);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public Iterable<PetDto> findAllPets() {

        return petRepository.findAll().stream().map(s->modelMapper.map(s,PetDto.class)).toList();
    }
    @Transactional
    @Override
    public PetDto updatePet(Long  id, UpdatePetDto updatePetDto) {
        Pet pet = petRepository.findById(id).orElseThrow(PetNotFoundException::new);
        pet.setCaption(updatePetDto.getCaption());
        pet.setCategory(updatePetDto.getCategory());
        pet.setGender(updatePetDto.getGender());
        pet.setAge(updatePetDto.getAge());
        pet.setCountry(updatePetDto.getCountry());
        pet.setCity(updatePetDto.getCity());
        pet.setDescription(updatePetDto.getDescription());
        pet.setPhoto(updatePetDto.getPhoto());
        petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }
    @Transactional
    @Override
    public PetDto removePetById(Long  id) {
        Pet pet=petRepository.findById(id).orElseThrow(PetNotFoundException::new);
        petRepository.delete(pet);
        return modelMapper.map(pet, PetDto.class);
    }
}
