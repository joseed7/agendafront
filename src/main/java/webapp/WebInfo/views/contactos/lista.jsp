<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agenda Web</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>📱 Agenda de Contactos</h1>

    <!-- Formulario para agregar contacto -->
    <form action="contactos" method="POST" class="mb-4">
        <div class="row">
            <div class="col">
                <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
            </div>
            <div class="col">
                <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
            </div>
            <div class="col">
                <input type="text" name="telefono" class="form-control" placeholder="Teléfono" required>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-success">Agregar</button>
            </div>
        </div>
    </form>

    <!-- Tabla de contactos -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Teléfono</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <!-- JSTL: Iterar sobre la lista de contactos -->
        <c:forEach var="contacto" items="${listaContactos}">
            <tr>
                <td>${contacto.idContacto}</td>
                <td>${contacto.nombre}</td>
                <td>${contacto.apellido}</td>
                <td>${contacto.telefono}</td>
                <td>
                    <a href="contactos/editar/${contacto.idContacto}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="contactos/eliminar/${contacto.idContacto}" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>