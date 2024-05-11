package ait.cohort34.security.service;

import ait.cohort34.petPosts.dao.PetRepository;
import ait.cohort34.petPosts.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class CustomWebSecurity {

        final PetRepository petRepository;

        public boolean checkPostAuthor(Long  postId, String userName){
            Pet pet = petRepository.findById(postId).orElse(null);//нет смысла бросать здесь ошибку - она будет 500-ой
            return pet !=null && pet.getAuthor().equals(userName);
        }
    }

