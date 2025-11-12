package com.agendafront.window;

import com.agendafront.DTos.ContactoDTO;
import com.agendafront.DTos.CrearContactoRequest;
import com.agendafront.webservicesclient.ContactoApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MainFrame extends JFrame {

    @Autowired
    private ContactoApiClient contactoService;

    // Componentes de la UI
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion;
    private JButton btnAgregar, btnEditar, btnEliminar, btnActualizar;
    private final Map<Integer, Integer> idMap = new HashMap<>();
    private int filaSeleccionada = -1;

    public MainFrame() {
        setTitle("Agenda de Contactos - Spring Boot + Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    public void initializeUI() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de formulario
        JPanel panelFormulario = crearPanelFormulario();
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        // Panel de tabla
        JPanel panelTabla = crearPanelTabla();
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = crearPanelBotones();
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        cargarContactos();
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Contacto"));

        // Campos de texto
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtDireccion = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Contactos"));

        // Modelo de tabla
        String[] columnas = {"ID", "Nombre", "Apellido", "Teléfono", "Email", "Dirección", "Fecha Creación"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Listener para selección de fila
        tablaContactos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaContactos.getSelectedRow() != -1) {
                seleccionarFila(tablaContactos.getSelectedRow());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaContactos);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar Lista");

        // Acciones de los botones
        btnAgregar.addActionListener(e -> agregarContacto());
        btnEditar.addActionListener(e -> editarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnActualizar.addActionListener(e -> cargarContactos());

        panel.add(btnAgregar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);

        return panel;
    }

    private void cargarContactos() {
        try {
            List<ContactoDTO> contactos = contactoService.getContactos();
            actualizarTabla(contactos);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Contactos cargados exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar contactos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(List<ContactoDTO> contactos) {
        modeloTabla.setRowCount(0);
        idMap.clear();

        for (int i = 0; i < contactos.size(); i++) {
            ContactoDTO contacto = contactos.get(i);
            idMap.put(i, contacto.getId());

            Object[] fila = {
                    contacto.getId(),
                    contacto.getNombre(),
                    contacto.getApellido(),
                    contacto.getTelefono(),
                    contacto.getEmail(),
                    contacto.getDireccion(),
                    contacto.getFechaCreacion()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void seleccionarFila(int fila) {
        this.filaSeleccionada = fila;
        Integer id = idMap.get(fila);

        if (id != null) {
            try {
                ContactoDTO contacto = contactoService.getContacto(id);
                llenarFormulario(contacto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar contacto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void llenarFormulario(ContactoDTO contacto) {
        txtNombre.setText(contacto.getNombre());
        txtApellido.setText(contacto.getApellido());
        txtTelefono.setText(contacto.getTelefono());
        txtEmail.setText(contacto.getEmail());
        txtDireccion.setText(contacto.getDireccion());
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        filaSeleccionada = -1;
        tablaContactos.clearSelection();
    }

    private void agregarContacto() {
        if (!validarFormulario()) return;

        try {
            CrearContactoRequest request = new CrearContactoRequest();
            request.setNombre(txtNombre.getText());
            request.setApellido(txtApellido.getText());
            request.setTelefono(txtTelefono.getText());
            request.setEmail(txtEmail.getText());
            request.setDireccion(txtDireccion.getText());

            contactoService.crearContacto(request);
            cargarContactos();
            JOptionPane.showMessageDialog(this, "Contacto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar contacto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarContacto() {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarFormulario()) return;

        try {
            Integer id = idMap.get(filaSeleccionada);
            CrearContactoRequest request = new CrearContactoRequest();
            request.setNombre(txtNombre.getText());
            request.setApellido(txtApellido.getText());
            request.setTelefono(txtTelefono.getText());
            request.setEmail(txtEmail.getText());
            request.setDireccion(txtDireccion.getText());

            contactoService.actualizarContacto(id, request);
            cargarContactos();
            JOptionPane.showMessageDialog(this, "Contacto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar contacto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarContacto() {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este contacto?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                Integer id = idMap.get(filaSeleccionada);
                contactoService.eliminarContacto(id);
                cargarContactos();
                JOptionPane.showMessageDialog(this, "Contacto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar contacto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validarFormulario() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellido.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Nombre, apellido y teléfono son campos obligatorios", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void showFrame() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}
