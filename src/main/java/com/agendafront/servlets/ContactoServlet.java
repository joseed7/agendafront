package com.agendafront.servlets;

import com.agendafront.entities.Contacto;
import com.agendafront.service.ContactoService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

// JAKARTA EE imports (Spring Boot 3)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/contactos")
public class ContactoServlet extends HttpServlet {

    private ContactoService contactoService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        contactoService = context.getBean(ContactoService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Contacto> contactos = contactoService.findAll();
        request.setAttribute("listaContactos", contactos);
        request.getRequestDispatcher("/webapp/WebInfo/views/contactos/lista.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setTelefono(telefono);
        contacto.setEmail(email);

        contactoService.save(contacto);
        response.sendRedirect(request.getContextPath() + "/contactos");
    }
}
