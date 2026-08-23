<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Usuarios</title>

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

            <h2>Gestión de Usuarios</h2>

            <p class="contador">
                Total usuarios: ${totalUsuarios}
            </p>


            <details class="panel-formulario"
                     ${registroEditar != null ? 'open' : ''}>

                <summary class="boton-desplegar">

                    ${registroEditar != null
                        ? 'Editar usuario'
                        : 'Crear nuevo usuario'}

                </summary>


                <form action="${pageContext.request.contextPath}/Tabla_Usuarios"
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


                    <!-- ROL -->

                    <select name="roles_idRoles"
                            required>

                        <option value="">
                            Seleccione un rol
                        </option>


                        <c:forEach var="rol"
                                   items="${listaRoles}">

                            <option value="${rol.idRoles}"
                                    ${registroEditar.roles_idRoles == rol.idRoles
                                        ? 'selected'
                                        : ''}>

                                ${rol.descripcionatencion}

                            </option>

                        </c:forEach>

                    </select>


                    <button type="submit">

                        ${registroEditar != null
                            ? 'Actualizar'
                            : 'Guardar'}

                    </button>


                    <c:if test="${registroEditar != null}">

                        <a class="boton-secundario"
                           href="${pageContext.request.contextPath}/Tabla_Usuarios">

                            Cancelar

                        </a>

                    </c:if>

                </form>

            </details>


            <!-- TABLA -->

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
                        <th>Rol</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="u"
                               items="${listaUsuarios}">

                        <tr>

                            <td>${u.nombres}</td>

                            <td>${u.apellidos}</td>

                            <td>${u.identificacion}</td>

                            <td>${u.telefono}</td>

                            <td>${u.direccion}</td>

                            <td>${u.correo}</td>

                            <td>${u.rethus}</td>


                            <td>

                                <c:choose>

                                    <c:when test="${not empty u.rolDescripcion}">

                                        ${u.rolDescripcion}

                                    </c:when>


                                    <c:otherwise>

                                        Sin rol

                                    </c:otherwise>

                                </c:choose>

                            </td>


                            <td class="acciones-tabla">


                                <!-- EDITAR -->

                                <form action="${pageContext.request.contextPath}/Tabla_Usuarios"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${u.id_Usuarios}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <!-- ELIMINAR -->

                                <form action="${pageContext.request.contextPath}/Tabla_Usuarios"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${u.id_Usuarios}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar este usuario?')">

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
