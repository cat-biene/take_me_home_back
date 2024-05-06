package de.ait.cohort34.petPosts.dto.exseption;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String message) {
        super(message);
    }
}