package com.crud.dto.swagger.persocat.repository;

import com.crud.dto.swagger.persocat.model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGatoRepository extends JpaRepository<Gato, Long> {

}
