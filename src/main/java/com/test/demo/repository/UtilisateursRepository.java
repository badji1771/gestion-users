package com.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.model.Utilisateurs;


@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long>{

}
