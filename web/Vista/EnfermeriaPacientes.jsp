
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:set var="pendientes" value="0"/>

<c:forEach var="p" items="${listaPacientes}">
    <c:if test="${empty p.diagnostico or p.diagnostico == 'Pendiente de completar'}">
        <c:set var="pendientes" value="${pendientes + 1}"/>
    </c:if>
</c:forEach>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Pacientes | Nurse</title>

    <link
        id="estilos-enfermeria"
        rel="stylesheet"
        href="${pageContext.request.contextPath}/Ccs/Style_GestionEnfermeria.css"
    >
</head>

<body>

    <div class="app" id="app">

        <!-- Barra lateral -->
        <jsp:include page="/Vista/Componentes/SidebarEnfermeria.jsp"/>

        <!-- Contenido principal -->
        <div class="contenido" id="contenido">

            <!-- Encabezado -->
            <header class="barra" id="barra">

                <div class="saludo" id="saludo">
                    <b>Área de enfermería</b>
                    <span>Gestión clínica y seguimiento</span>
                </div>

                <div class="perfil" id="perfil">
                    ● Sesión de enfermería
                </div>

            </header>

            <!-- Página principal -->
            <main class="pagina" id="pagina">

                <!-- Título y botón de registro -->
                <section class="encabezado" id="encabezado">

                    <div>
                        <span class="eyebrow">REGISTRO CLÍNICO</span>

                        <h1>Gestión de pacientes</h1>

                        <p>
                            Administra el registro y seguimiento de tus pacientes.
                        </p>
                    </div>

                    <a
                        class="btn-principal"
                        id="btn-registrar"
                        href="${pageContext.request.contextPath}/GestionPacientesFormulario"
                    >
                        ＋ Registrar nuevo
                    </a>

                </section>

                <!-- Mensajes del sistema -->
                <c:if test="${not empty sessionScope.mensajeGestion}">

                    <div class="mensaje" id="mensaje-gestion">
                        ${sessionScope.mensajeGestion}
                    </div>

                    <c:remove var="mensajeGestion" scope="session"/>

                </c:if>

                <!-- Métricas -->
                <section class="metricas" id="metricas">

                    <article class="metrica" id="metrica-pacientes">

                        <div class="icono">♟</div>

                        <div>
                            <small>Pacientes registrados</small>
                            <strong>${listaPacientes.size()}</strong>
                        </div>

                    </article>

                    <article class="metrica" id="metrica-pendientes">

                        <div class="icono rojo">◷</div>

                        <div>
                            <small>Fichas por completar</small>
                            <strong>${pendientes}</strong>
                        </div>

                    </article>

                    <article class="metrica" id="metrica-tratamientos">

                        <div class="icono verde">▥</div>

                        <div>
                            <small>Tratamientos registrados</small>
                            <strong>${listaTratamientos.size()}</strong>
                        </div>

                    </article>

                </section>

                <!-- Directorio de pacientes -->
                <section class="panel" id="panel-pacientes">

                    <div class="panel-head">

                        <h2>Directorio de pacientes</h2>

                        <input
                            type="search"
                            id="buscador-pacientes"
                            class="buscador"
                            data-filtro="tabla-pacientes"
                            placeholder="⌕ Buscar por nombre o documento"
                        >

                    </div>

                    <!-- Tabla de pacientes -->
                    <div class="tabla-wrap" id="contenedor-tabla">

                        <table class="tabla" id="tabla-pacientes">

                            <thead>
                                <tr>
                                    <th>Paciente</th>
                                    <th>Documento</th>
                                    <th>Fecha de nacimiento</th>
                                    <th>Historial clínico</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="p" items="${listaPacientes}">

                                    <tr>

                                        <!-- Información del paciente -->
                                        <td>

                                            <div class="persona">

                                                <span class="avatar">
                                                    ${p.nombreCompleto.substring(0,1)}
                                                </span>

                                                <div>

                                                    <a
                                                        class="nombre-paciente"
                                                        id="paciente-${p.idPaciente}"
                                                        href="${pageContext.request.contextPath}/FichaPaciente?id=${p.idPaciente}"
                                                    >
                                                        ${p.nombreCompleto}
                                                    </a>

                                                    <small>
                                                        Ver ficha clínica #${p.idPaciente}
                                                    </small>

                                                </div>

                                            </div>

                                        </td>

                                        <!-- Documento -->
                                        <td>
                                            ${p.identificacion}
                                        </td>

                                        <!-- Fecha de nacimiento -->
                                        <td>
                                            ${p.fecha_nacimiento}
                                        </td>

                                        <!-- Historial clínico -->
                                        <td>
                                            ${p.diagnostico}
                                        </td>

                                        <!-- Estado del paciente -->
                                        <td>

                                            <c:choose>

                                                <c:when test="${empty p.diagnostico or p.diagnostico == 'Pendiente de completar'}">

                                                    <span class="estado estado-pendiente">
                                                        Pendiente
                                                    </span>

                                                </c:when>

                                                <c:otherwise>

                                                    <span class="estado estado-activo">
                                                        Completo
                                                    </span>

                                                </c:otherwise>

                                            </c:choose>

                                        </td>

                                        <!-- Acciones -->
                                        <td>

                                            <a
                                                class="editar"
                                                id="editar-${p.idPaciente}"
                                                href="${pageContext.request.contextPath}/GestionPacientesFormulario?id=${p.idPaciente}"
                                            >
                                                Editar
                                            </a>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </section>

            </main>

        </div>

    </div>

    <!-- Archivo JavaScript -->
    <script
        id="script-enfermeria"
        src="${pageContext.request.contextPath}/JavaScript/GestionEnfermeria.js"
    ></script>

</body>
</html>
