package com.agendafront;

import com.agendafront.window.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
@EnableFeignClients
public class AgendafrontApplication {

    @Autowired
    private MainFrame mainFrame;

    public static void main(String[] args) {
        // Configurar para modo no headless (importante para Swing)
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(AgendafrontApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Ejecutar en el Event Dispatch Thread de Swing
            SwingUtilities.invokeLater(() -> {
                mainFrame.initializeUI();
                mainFrame.showFrame();
            });
        };
    }
}