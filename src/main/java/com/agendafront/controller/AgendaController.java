package com.agendafront.controller;

import com.agendafront.DTos.ContactoDTO;
import com.agendafront.DTos.CrearContactoRequest;
import com.agendafront.webservicesclient.ContactoApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local")
public class AgendaController {

    @Autowired
    private ContactoApiClient contactoApiClient;

    @GetMapping("/contactos")
    public List<ContactoDTO> obtenerTodosLosContactos() {
        List<ContactoDTO> contactos = contactoApiClient.getContactos();
        contactos.forEach(System.out::println);
        return contactos;
    }

    @GetMapping("/contactos/{id}")
    public ContactoDTO obtenerContacto(@PathVariable Integer id) {
        return contactoApiClient.getContacto(id);
    }

    @PostMapping("/contactos")
    public ContactoDTO crearContacto(@RequestBody CrearContactoRequest request) {
        return contactoApiClient.crearContacto(request);
    }

    @PutMapping("/contactos/{id}")
    public ContactoDTO actualizarContacto(@PathVariable Integer id, @RequestBody CrearContactoRequest request) {
        return contactoApiClient.actualizarContacto(id, request);
    }

    @DeleteMapping("/contactos/{id}")
    public void eliminarContacto(@PathVariable Integer id) {
        contactoApiClient.eliminarContacto(id);
    }
}