<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Tratamientos</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Tablas.css">
</head>

<body>

<div class="layout-admin">

    <jsp:include page="/Vista/Componentes/SidebarAdmin.jsp" />

    <main class="contenido-admin">

        <div class="contenedor">

            <h2>Gestión de Tratamientos</h2>

            <p class="contador">
                Total tratamientos: ${totalTratamientos}
            </p>


            <details class="panel-formulario"
                     ${registroEditar != null ? 'open' : ''}>

                <summary class="boton-desplegar">

                    ${registroEditar != null
                        ? 'Editar tratamiento'
                        : 'Crear nuevo tratamiento'}

                </summary>


                <form action="${pageContext.request.contextPath}/Tabla_Tratamiento"
                      method="post"
                      class="formulario">


                    <c:choose>

                        <c:when test="${registroEditar != null}">

                            <input type="hidden"
                                   name="accion"
                                   value="actualizar">

                            <input type="hidden"
                                   name="id"
                                   value="${registroEditar.id_Tratamiento}">

                        </c:when>


                        <c:otherwise>

                            <input type="hidden"
                                   name="accion"
                                   value="insertar">

                        </c:otherwise>

                    </c:choose>


                    <!-- PACIENTE -->

                    <label>
                        Paciente

                        <select name="paciente_id" required>

                            <option value="">
                                Seleccione un paciente
                            </option>


                            <c:forEach var="p"
                                       items="${listaPacientes}">

                                <option value="${p.idPaciente}"
                                        ${registroEditar.pacienteId == p.idPaciente ? 'selected' : ''}>

                                    ${p.nombreCompleto}

                                </option>

                            </c:forEach>

                        </select>

                    </label>


                    <!-- DESCRIPCIÓN -->

                    <input type="text"
                           name="descripcion"
                           placeholder="Nombre o diagnóstico del tratamiento"
                           value="${registroEditar.descripcion}"
                           required>


                    <!-- FECHAS -->

                    <div class="fila-campos">

                        <label>
                            Inicio

                            <input type="date"
                                   name="fecha_inicio"
                                   value="${registroEditar.fechaInicio}"
                                   required>

                        </label>


                        <label>
                            Finalización

                            <input type="date"
                                   name="fecha_fin"
                                   value="${registroEditar.fechaFin}"
                                   required>

                        </label>

                    </div>


                    <!-- ESTADO -->

                    <label>
                        Estado

                        <select name="estado" required>

                            <option value="Activo"
                                    ${registroEditar.estado == 'Activo'
                                        ? 'selected'
                                        : ''}>

                                Activo

                            </option>


                            <option value="Suspendido"
                                    ${registroEditar.estado == 'Suspendido'
                                        ? 'selected'
                                        : ''}>

                                Suspendido

                            </option>


                            <option value="Finalizado"
                                    ${registroEditar.estado == 'Finalizado'
                                        ? 'selected'
                                        : ''}>

                                Finalizado

                            </option>

                        </select>

                    </label>


                    <!-- INDICACIONES -->

                    <label>
                        Indicaciones para enfermería/paciente

                        <textarea name="indicaciones"
                                  placeholder="Dosis, frecuencia, vía de administración y observaciones"
                                  required>${registroEditar.indicaciones}</textarea>

                    </label>


                    <!-- MEDICAMENTOS -->

                    <fieldset class="selector-medicamentos">

                        <legend>
                            Medicamentos del tratamiento
                        </legend>


                        <c:forEach var="m"
                                   items="${listaMedicamentos}">

                            <label class="opcion-medicamento">

                                <input type="checkbox"
                                       name="medicamento_id"
                                       value="${m.id_Medicamento}"
                                       ${registroEditar.tieneMedicamento(m.id_Medicamento)
                                           ? 'checked'
                                           : ''}>


                                <span>

                                    <strong>
                                        ${m.nombre}
                                    </strong>

                                    · ${m.presentacion}

                                    · vence ${m.fechaVencimiento}

                                </span>

                            </label>

                        </c:forEach>

                    </fieldset>


                    <!-- BOTÓN -->

                    <button type="submit">

                        ${registroEditar != null
                            ? 'Actualizar'
                            : 'Guardar'}

                    </button>


                    <c:if test="${registroEditar != null}">

                        <a class="boton-secundario"
                           href="${pageContext.request.contextPath}/Tabla_Tratamiento">

                            Cancelar

                        </a>

                    </c:if>

                </form>

            </details>


            <!-- TABLA -->

            <table class="tabla">

                <thead>

                    <tr>

                        <th>Paciente</th>
                        <th>Tratamiento</th>
                        <th>Periodo</th>
                        <th>Medicamentos</th>
                        <th>Estado</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="t"
                               items="${listaTratamientos}">

                        <tr>


                            <td>
                                ${t.pacienteNombre}
                            </td>


                            <td>

                                <strong>
                                    ${t.descripcion}
                                </strong>

                                <br>

                                <small>
                                    ${t.indicaciones}
                                </small>

                            </td>


                            <td>
                                ${t.fechaInicio} a ${t.fechaFin}
                            </td>


                            <td>

                                <c:choose>

                                    <c:when test="${empty t.medicamentosNombres}">

                                        Sin medicamentos

                                    </c:when>


                                    <c:otherwise>

                                        ${t.medicamentosNombres}

                                    </c:otherwise>

                                </c:choose>

                            </td>


                            <td>

                                <span class="estado estado-${t.estado}">

                                    ${t.estado}

                                </span>

                            </td>


                            <td class="acciones-tabla">


                                <form action="${pageContext.request.contextPath}/Tabla_Tratamiento"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${t.id_Tratamiento}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <form action="${pageContext.request.contextPath}/Tabla_Tratamiento"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${t.id_Tratamiento}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar este tratamiento?')">

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