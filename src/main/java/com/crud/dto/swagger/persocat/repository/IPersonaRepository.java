package com.crud.dto.swagger.persocat.repository;

import com.crud.dto.swagger.persocat.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {
}
