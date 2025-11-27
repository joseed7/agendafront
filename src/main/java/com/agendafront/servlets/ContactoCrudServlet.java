package com.agendafront.servlets;

import com.agendafront.entities.Contacto;
import com.agendafront.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contactos/*")  // URL: /contactos/editar/1, /contactos/eliminar/1
public class ContactoCrudServlet extends HttpServlet {

    private ContactoService contactoService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        contactoService = context.getBean(ContactoService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // Ej: "/editar/1"

        if (pathInfo.startsWith("/editar")) {
            mostrarFormularioEditar(request, response);
        } else if (pathInfo.startsWith("/eliminar")) {
            eliminarContacto(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Extraer ID de la URL: /editar/1
        String[] pathParts = request.getPathInfo().split("/");
        Integer id = Integer.parseInt(pathParts[2]);

        // 2. Buscar contacto
        Contacto contacto = contactoService.findById(id);

        // 3. Pasar a JSP
        request.setAttribute("contacto", contacto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebInfo/views/contactos/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminarContacto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Extraer ID y eliminar
        String[] pathParts = request.getPathInfo().split("/");
        Integer id = Integer.parseInt(pathParts[2]);

        contactoService.deleteById(id);

        // Redirigir a lista
        response.sendRedirect(request.getContextPath() + "/contactos");
    }
}