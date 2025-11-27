<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Contacto - Agenda Web</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 2rem auto;
            padding: 2rem;
            border: 1px solid #dee2e6;
            border-radius: 0.375rem;
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
        }
        .page-title {
            color: #2c3e50;
            border-bottom: 3px solid #3498db;
            padding-bottom: 0.5rem;
            margin-bottom: 2rem;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/contactos">
                📱 Agenda Web
            </a>
            <span class="navbar-text">
                    Editando contacto
                </span>
        </div>
    </nav>

    <!-- Mensajes de alerta -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Formulario de edición -->
    <div class="form-container">
        <h2 class="page-title text-center">✏️ Editar Contacto</h2>

        <form action="${pageContext.request.contextPath}/contactos/actualizar" method="POST">
            <!-- Campo oculto para el ID -->
            <input type="hidden" name="id" value="${contacto.idContacto}">

            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               value="${contacto.nombre}" required maxlength="100"
                               placeholder="Ingrese el nombre">
                        <div class="form-text">Campo obligatorio</div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="apellido" class="form-label">Apellido <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="apellido" name="apellido"
                               value="${contacto.apellido}" required maxlength="100"
                               placeholder="Ingrese el apellido">
                        <div class="form-text">Campo obligatorio</div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="telefono" class="form-label">Teléfono <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="telefono" name="telefono"
                               value="${contacto.telefono}" required maxlength="20"
                               placeholder="Ej: 123456789">
                        <div class="form-text">Campo obligatorio</div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               value="${contacto.email}" maxlength="150"
                               placeholder="ejemplo@correo.com">
                        <div class="form-text">Opcional</div>
                    </div>
                </div>
            </div>

            <!-- Información de auditoría -->
            <div class="card bg-light mb-4">
                <div class="card-body">
                    <h6 class="card-title">📊 Información del Sistema</h6>
                    <div class="row">
                        <div class="col-md-6">
                            <small class="text-muted">
                                <strong>Fecha creación:</strong><br>
                                ${contacto.fechaCreacion}
                            </small>
                        </div>
                        <div class="col-md-6">
                            <small class="text-muted">
                                <strong>Última actualización:</strong><br>
                                ${contacto.fechaActualizacion}
                            </small>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Botones de acción -->
            <div class="d-flex justify-content-between">
                <div>
                    <button type="submit" class="btn btn-primary">
                        💾 Actualizar Contacto
                    </button>
                    <a href="${pageContext.request.contextPath}/contactos" class="btn btn-secondary">
                        ↩️ Cancelar
                    </a>
                </div>
                <a href="${pageContext.request.contextPath}/contactos/eliminar/${contacto.idContacto}"
                   class="btn btn-outline-danger"
                   onclick="return confirm('¿Está seguro de eliminar este contacto? Esta acción no se puede deshacer.')">
                    🗑️ Eliminar
                </a>
            </div>
        </form>
    </div>

    <!-- Footer -->
    <footer class="mt-5 text-center text-muted">
        <hr>
        <p>Sistema de Agenda Web - Desarrollado con Spring Boot & JSP</p>
    </footer>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script para validación -->
<script>
    // Validación básica del formulario
    document.querySelector('form').addEventListener('submit', function(e) {
        const nombre = document.getElementById('nombre').value.trim();
        const apellido = document.getElementById('apellido').value.trim();
        const telefono = document.getElementById('telefono').value.trim();

        if (!nombre || !apellido || !telefono) {
            e.preventDefault();
            alert('Por favor, complete todos los campos obligatorios (*)');
            return false;
        }

        // Validación básica de teléfono (solo números)
        const telefonoRegex = /^[0-9+\-\s()]+$/;
        if (!telefonoRegex.test(telefono)) {
            e.preventDefault();
            alert('Por favor, ingrese un número de teléfono válido');
            return false;
        }
    });

    // Confirmación antes de eliminar
    document.querySelector('.btn-outline-danger').addEventListener('click', function(e) {
        if (!confirm('¿Está seguro de que desea eliminar este contacto permanentemente?')) {
            e.preventDefault();
        }
    });
</script>
</body>
</html>