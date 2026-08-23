<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Enfermeras</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Tablas.css?v=20260823-2">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Panel.css?v=20260823-2">
</head>

<body>

<div class="layout-admin">

    <jsp:include page="/Vista/Componentes/SidebarAdmin.jsp" />

    <main class="contenido-admin">

        <div class="contenedor">

            <h2>Gestión de Enfermeras</h2>

            <p class="contador">
                Total enfermeras: ${totalEnfermeras}
            </p>


            <c:choose>

                <c:when test="${rolEnfermeraId > 0}">

                    <details class="panel-formulario"
                             ${registroEditar != null ? 'open' : ''}>

                        <summary class="boton-desplegar">

                            ${registroEditar != null
                                ? 'Editar enfermera'
                                : 'Crear nueva enfermera'}

                        </summary>


                        <form action="${pageContext.request.contextPath}/Tabla_Enfermeras"
                              method="post"
                              class="formulario">


                            <c:choose>

                                <c:when test="${registroEditar != null}">

                                    <input type="hidden"
                                           name="accion"
                                           value="actualizar">

                                    <input type="hidden"
                                           name="id"
                                           value="${registroEditar.id_Usuarios}">

                                </c:when>


                                <c:otherwise>

                                    <input type="hidden"
                                           name="accion"
                                           value="insertar">

                                </c:otherwise>

                            </c:choose>


                            <input type="hidden"
                                   name="roles_idRoles"
                                   value="${rolEnfermeraId}">


                            <input type="text"
                                   name="nombres"
                                   placeholder="Nombres"
                                   value="${registroEditar.nombres}"
                                   required>


                            <input type="text"
                                   name="apellidos"
                                   placeholder="Apellidos"
                                   value="${registroEditar.apellidos}"
                                   required>


                            <input type="text"
                                   name="identificacion"
                                   placeholder="Identificación"
                                   value="${registroEditar.identificacion}"
                                   required>


                            <input type="text"
                                   name="telefono"
                                   placeholder="Teléfono"
                                   value="${registroEditar.telefono}"
                                   required>


                            <input type="text"
                                   name="direccion"
                                   placeholder="Dirección"
                                   value="${registroEditar.direccion}"
                                   required>


                            <input type="email"
                                   name="correo"
                                   placeholder="Correo"
                                   value="${registroEditar.correo}"
                                   required>


                            <input type="password"
                                   name="clave"
                                   placeholder="Clave"
                                   value="${registroEditar.clave}"
                                   required>


                            <input type="text"
                                   name="rethus"
                                   placeholder="Rethus"
                                   value="${registroEditar.rethus}"
                                   required>


                            <button type="submit">

                                ${registroEditar != null
                                    ? 'Actualizar'
                                    : 'Guardar'}

                            </button>


                            <c:if test="${registroEditar != null}">

                                <a class="boton-secundario"
                                   href="${pageContext.request.contextPath}/Tabla_Enfermeras">

                                    Cancelar

                                </a>

                            </c:if>

                        </form>

                    </details>

                </c:when>


                <c:otherwise>

                    <div class="aviso-admin">

                        Crea primero un rol llamado Enfermera en Roles.
                        Luego asigna ese rol a los usuarios que deben
                        aparecer aquí.

                    </div>

                </c:otherwise>

            </c:choose>


            <table class="tabla">

                <thead>

                    <tr>

                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>Identificación</th>
                        <th>Teléfono</th>
                        <th>Dirección</th>
                        <th>Correo</th>
                        <th>Rethus</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="e"
                               items="${listaEnfermeras}">

                        <tr>

                            <td>${e.nombres}</td>

                            <td>${e.apellidos}</td>

                            <td>${e.identificacion}</td>

                            <td>${e.telefono}</td>

                            <td>${e.direccion}</td>

                            <td>${e.correo}</td>

                            <td>${e.rethus}</td>


                            <td class="acciones-tabla">

                                <form action="${pageContext.request.contextPath}/Tabla_Enfermeras"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${e.id_Usuarios}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <form action="${pageContext.request.contextPath}/Tabla_Enfermeras"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${e.id_Usuarios}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar esta enfermera?')">

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
