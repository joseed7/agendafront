package com.agendafront.DTos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContactoDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private LocalDateTime fechaCreacion;
}