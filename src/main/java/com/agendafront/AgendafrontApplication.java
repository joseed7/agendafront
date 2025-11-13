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
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(AgendafrontApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Pequeño delay para asegurar que el servidor esté listo
            Thread.sleep(1000);

            SwingUtilities.invokeLater(() -> {
                mainFrame.initializeUI();
                mainFrame.showFrame();
            });
        };
    }
}