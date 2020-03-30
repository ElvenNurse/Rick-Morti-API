package com.example.rickmortiapi.repository;

import com.example.rickmortiapi.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Integer countByIdIsNotNull();

    List<Character> getCharactersByNameContains(String namePart);
}
