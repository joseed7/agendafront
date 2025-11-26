package com.agendafront.repository;


import com.agendafront.entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    // Spring Data JPA crea automáticamente la implementación
    List<Contacto> findByNombreContainingIgnoreCase(String nombre);
    List<Contacto> findByApellidoContainingIgnoreCase(String apellido);
}
