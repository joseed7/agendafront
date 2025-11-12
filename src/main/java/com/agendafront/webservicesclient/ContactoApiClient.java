package com.agendafront.webservicesclient;

import com.agendafront.DTos.ContactoDTO;
import com.agendafront.DTos.CrearContactoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "contacto-api-client", url = "${api.contactos.base-url:http://localhost:8080/api}")
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