<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Pacientes</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Tablas.css">
</head>

<body>

<div class="layout-admin">

    <jsp:include page="/Vista/Componentes/SidebarAdmin.jsp" />

    <main class="contenido-admin">

        <div class="contenedor">

            <h2>Gestión de Pacientes</h2>

            <p class="contador">
                Total pacientes: ${totalPacientes}
            </p>


            <details class="panel-formulario"
                     ${registroEditar != null ? 'open' : ''}>

                <summary class="boton-desplegar">

                    ${registroEditar != null
                        ? 'Completar o editar ficha del paciente'
                        : 'Completar ficha de un paciente'}

                </summary>


                <form action="${pageContext.request.contextPath}/Tabla_Pacientes"
                      method="post"
                      class="formulario">


                    <c:choose>

                        <c:when test="${registroEditar != null}">

                            <input type="hidden"
                                   name="accion"
                                   value="actualizar">

                            <input type="hidden"
                                   name="id"
                                   value="${registroEditar.idPaciente}">

                        </c:when>


                        <c:otherwise>

                            <input type="hidden"
                                   name="accion"
                                   value="insertar">

                        </c:otherwise>

                    </c:choose>


                    <input type="date"
                           name="fecha_nacimiento"
                           value="${registroEditar.fecha_nacimiento}"
                           required>


                    <input type="text"
                           name="diagnostico"
                           placeholder="Diagnóstico"
                           value="${registroEditar.diagnostico}"
                           required>


                    <label>
                        Usuario registrado como paciente

                        <select name="usuario" required>

                            <option value="">
                                Seleccione un usuario
                            </option>


                            <c:forEach var="u"
                                       items="${listaUsuariosPacientes}">

                                <option value="${u.id_Usuarios}"
                                        ${registroEditar.usuariosIdUsuarios == u.id_Usuarios ? 'selected' : ''}>

                                    ${u.nombres} ${u.apellidos}
                                    — ${u.identificacion}

                                </option>

                            </c:forEach>

                        </select>

                    </label>


                    <button type="submit">

                        ${registroEditar != null
                            ? 'Actualizar'
                            : 'Guardar'}

                    </button>


                    <c:if test="${registroEditar != null}">

                        <a class="boton-secundario"
                           href="${pageContext.request.contextPath}/Tabla_Pacientes">

                            Cancelar

                        </a>

                    </c:if>

                </form>

            </details>


            <table class="tabla">

                <thead>

                    <tr>

                        <th>Fecha de nacimiento</th>
                        <th>Diagnóstico</th>
                        <th>Paciente</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="p"
                               items="${listaPacientes}">

                        <tr>

                            <td>

                                ${empty p.fecha_nacimiento
                                    ? 'Pendiente'
                                    : p.fecha_nacimiento}

                            </td>


                            <td>

                                ${empty p.diagnostico
                                    ? 'Pendiente de completar'
                                    : p.diagnostico}

                            </td>


                            <td>
                                ${p.nombreCompleto}
                            </td>


                            <td class="acciones-tabla">

                                <form action="${pageContext.request.contextPath}/Tabla_Pacientes"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${p.idPaciente}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <form action="${pageContext.request.contextPath}/Tabla_Pacientes"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${p.idPaciente}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar este paciente?')">

                                        Eliminar

                                    </button>

                                </form>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

    </main>

</div>

</body>
</html>