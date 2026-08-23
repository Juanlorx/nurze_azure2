<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Nurse - Gestión de Pacientes</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Tablas.css">

</head>

<body>

<div class="contenedor">

    <div class="acciones-superiores">

        <a href="${pageContext.request.contextPath}Vista/Menu.jsp">

            ← Volver al Menu

        </a>

    </div>

    <h2>

        Gestión de Pacientes

    </h2>

    <p class="contador">

        Total Pacientes: ${totalPacientes}

    </p>

    <!-- ========================= -->
    <!-- FORMULARIO -->
    <!-- ========================= -->

    <form action="${pageContext.request.contextPath}/Tabla_Pacientes"
          method="post"
          class="formulario">

        <c:choose>

            <c:when test="${registroEditar != null}">

                <input type="hidden"
                       name="accion"
                       value="actualizar"/>

                <input type="hidden"
                       name="id"
                       value="${registroEditar.idPaciente}"/>

            </c:when>

            <c:otherwise>

                <input type="hidden"
                       name="accion"
                       value="insertar"/>

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

        <input type="number"
               name="usuario"
               placeholder="ID Usuario"
               value="${registroEditar.usuariosIdUsuarios}"
               required>

        <c:choose>

            <c:when test="${registroEditar != null}">

                <button type="submit">

                    Actualizar

                </button>

                <a class="boton-secundario"
                   href="${pageContext.request.contextPath}/Tabla_Pacientes">

                    Cancelar

                </a>

            </c:when>

            <c:otherwise>

                <button type="submit">

                    Guardar

                </button>

            </c:otherwise>

        </c:choose>

    </form>

    <br>

    <!-- ========================= -->
    <!-- TABLA -->
    <!-- ========================= -->

    <table class="tabla">

        <thead>

            <tr>

                <th>ID</th>

                <th>Fecha Nacimiento</th>

                <th>Diagnóstico</th>

                <th>ID Usuario</th>

                <th>Acciones</th>

            </tr>

        </thead>

        <tbody>

            <c:forEach var="p"
                       items="${listaPacientes}">

                <tr>

                    <td>

                        ${p.idPaciente}

                    </td>

                    <td>

                        ${p.fecha_nacimiento}

                    </td>

                    <td>

                        ${p.diagnostico}

                    </td>

                    <td>

                        ${p.usuariosIdUsuarios}

                    </td>

                    <td>

                        <a href="${pageContext.request.contextPath}/Tabla_Pacientes?accion=editar&id=${p.idPaciente}">

                            Editar

                        </a>

                        |

                        <a href="${pageContext.request.contextPath}/Tabla_Pacientes?accion=eliminar&id=${p.idPaciente}"
                           onclick="return confirm('¿Desea eliminar este paciente?');">

                            Eliminar

                        </a>

                    </td>

                </tr>

            </c:forEach>

            <c:if test="${empty listaPacientes}">

                <tr>

                    <td colspan="5">

                        No hay pacientes registrados.

                    </td>

                </tr>

            </c:if>

        </tbody>

    </table>

</div>

</body>

</html>