package com.ulloalibros.repositorio;

import com.ulloalibros.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {


}
