package com.agendafront;

import jakarta.servlet.http.HttpServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan  // ← Para detectar @WebServlet
public class AgendafrontApplication extends HttpServlet {
    public static void main(String[] args) {
        SpringApplication.run(AgendafrontApplication.class, args);
    }
}