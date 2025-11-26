package com.agendafront.service;


import com.agendafront.entities.Contacto;
import com.agendafront.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    public List<Contacto> findAll() {
        return contactoRepository.findAll();
    }

    public Contacto findById(Integer id) {
        Optional<Contacto> contacto = contactoRepository.findById(id);
        return contacto.orElse(null);
    }

    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public void deleteById(Integer id) {
        contactoRepository.deleteById(id);
    }

    public List<Contacto> findByNombre(String nombre) {
        return contactoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
