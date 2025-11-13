package com.agendafront.controller;

import com.agendafront.DTos.ContactoDTO;
import com.agendafront.DTos.CrearContactoRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class MockContactoController {

    private final List<ContactoDTO> contactos = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public MockContactoController() {
        // Datos de ejemplo
        contactos.add(ContactoDTO.builder()
                .id(1)
                .nombre("Juan")
                .apellido("Pérez")
                .telefono("123456789")
                .email("juan@email.com")
                .direccion("Calle Principal 123")
                .fechaCreacion(LocalDateTime.now())
                .build());

        contactos.add(ContactoDTO.builder()
                .id(2)
                .nombre("María")
                .apellido("Gómez")
                .telefono("987654321")
                .email("maria@email.com")
                .direccion("Avenida Central 456")
                .fechaCreacion(LocalDateTime.now())
                .build());
    }

    @GetMapping("/contactos")
    public List<ContactoDTO> getContactos() {
        return contactos;
    }

    @GetMapping("/contactos/{id}")
    public ContactoDTO getContacto(@PathVariable Integer id) {
        return contactos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/contactos")
    public ContactoDTO crearContacto(@RequestBody CrearContactoRequest request) {
        ContactoDTO nuevoContacto = ContactoDTO.builder()
                .id(idCounter.getAndIncrement())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .fechaCreacion(LocalDateTime.now())
                .build();

        contactos.add(nuevoContacto);
        return nuevoContacto;
    }

    @PutMapping("/contactos/{id}")
    public ContactoDTO actualizarContacto(@PathVariable Integer id, @RequestBody CrearContactoRequest request) {
        ContactoDTO contactoExistente = contactos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (contactoExistente != null) {
            contactoExistente.setNombre(request.getNombre());
            contactoExistente.setApellido(request.getApellido());
            contactoExistente.setTelefono(request.getTelefono());
            contactoExistente.setEmail(request.getEmail());
            contactoExistente.setDireccion(request.getDireccion());
        }

        return contactoExistente;
    }

    @DeleteMapping("/contactos/{id}")
    public void eliminarContacto(@PathVariable Integer id) {
        contactos.removeIf(c -> c.getId().equals(id));
    }
}