<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Roles</title>

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

            <h2>Gestión de Roles</h2>

            <p class="contador">
                Total roles: ${totalRoles}
            </p>


            <details class="panel-formulario"
                     ${registroEditar != null ? 'open' : ''}>

                <summary class="boton-desplegar">

                    ${registroEditar != null
                        ? 'Editar rol'
                        : 'Crear nuevo rol'}

                </summary>


                <form action="${pageContext.request.contextPath}/Tabla_Roles"
                      method="post"
                      class="formulario">


                    <c:choose>

                        <c:when test="${registroEditar != null}">

                            <input type="hidden"
                                   name="accion"
                                   value="actualizar">

                            <input type="hidden"
                                   name="id"
                                   value="${registroEditar.idRoles}">

                        </c:when>


                        <c:otherwise>

                            <input type="hidden"
                                   name="accion"
                                   value="insertar">

                        </c:otherwise>

                    </c:choose>


                    <input type="text"
                           name="descripcionatencion"
                           placeholder="Descripción"
                           value="${registroEditar.descripcionatencion}"
                           required>


                    <button type="submit">

                        ${registroEditar != null
                            ? 'Actualizar'
                            : 'Guardar'}

                    </button>


                    <c:if test="${registroEditar != null}">

                        <a class="boton-secundario"
                           href="${pageContext.request.contextPath}/Tabla_Roles">

                            Cancelar

                        </a>

                    </c:if>

                </form>

            </details>


            <table class="tabla">

                <thead>

                    <tr>

                        <th>Descripción</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="r"
                               items="${listaRoles}">

                        <tr>

                            <td>
                                ${r.descripcionatencion}
                            </td>


                            <td class="acciones-tabla">

                                <form action="${pageContext.request.contextPath}/Tabla_Roles"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${r.idRoles}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <form action="${pageContext.request.contextPath}/Tabla_Roles"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${r.idRoles}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar este rol?')">

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
