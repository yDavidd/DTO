package com.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
