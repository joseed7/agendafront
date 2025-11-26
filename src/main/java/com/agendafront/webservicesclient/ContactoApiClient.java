package com.agendafront.webservicesclient;

import com.agendafront.DTos.ContactoDTO;
import com.agendafront.DTos.CrearContactoRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ContactoApiClient {

    @GetMapping("/contactos")
    List<ContactoDTO> getContactos();

    @GetMapping("/contactos/{id}")
    ContactoDTO getContacto(@PathVariable Integer id);

    @PostMapping("/contactos")
    ContactoDTO crearContacto(@RequestBody CrearContactoRequest request);

    @PutMapping("/contactos/{id}")
    ContactoDTO actualizarContacto(@PathVariable Integer id, @RequestBody CrearContactoRequest request);

    @DeleteMapping("/contactos/{id}")
    void eliminarContacto(@PathVariable Integer id);
}