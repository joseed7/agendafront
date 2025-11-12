package com.agendafront.DTos;

import lombok.Data;

@Data
public class CrearContactoRequest {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
}