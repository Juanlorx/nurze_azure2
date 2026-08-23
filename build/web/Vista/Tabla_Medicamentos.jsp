<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>CRUD Medicamentos</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Tablas.css">
</head>

<body>

<div class="layout-admin">

    <jsp:include page="/Vista/Componentes/SidebarAdmin.jsp" />

    <main class="contenido-admin">

        <div class="contenedor">

            <h2>Gestión de Medicamentos</h2>

            <p class="contador">
                Total medicamentos: ${totalMedicamentos}
            </p>


            <details class="panel-formulario"
                     ${registroEditar != null ? 'open' : ''}>

                <summary class="boton-desplegar">

                    ${registroEditar != null
                        ? 'Editar medicamento'
                        : 'Crear nuevo medicamento'}

                </summary>


                <form action="${pageContext.request.contextPath}/Tabla_Medicamentos"
                      method="post"
                      class="formulario">


                    <c:choose>

                        <c:when test="${registroEditar != null}">

                            <input type="hidden"
                                   name="accion"
                                   value="actualizar">

                            <input type="hidden"
                                   name="id"
                                   value="${registroEditar.id_Medicamento}">

                        </c:when>


                        <c:otherwise>

                            <input type="hidden"
                                   name="accion"
                                   value="insertar">

                        </c:otherwise>

                    </c:choose>


                    <input type="text"
                           name="nombre"
                           placeholder="Nombre del medicamento"
                           value="${registroEditar.nombre}"
                           required>


                    <input type="text"
                           name="funcion"
                           placeholder="Función o uso terapéutico"
                           value="${registroEditar.funcion}"
                           required>


                    <input type="text"
                           name="presentacion"
                           placeholder="Presentación (ej. tableta 500 mg)"
                           value="${registroEditar.presentacion}"
                           required>


                    <input type="text"
                           name="lote"
                           placeholder="Número de lote"
                           value="${registroEditar.lote}"
                           required>


                    <label>
                        Fecha de vencimiento

                        <input type="date"
                               name="fecha_vencimiento"
                               value="${registroEditar.fechaVencimiento}"
                               required>

                    </label>


                    <input type="number"
                           name="existencias"
                           min="0"
                           placeholder="Existencias disponibles"
                           value="${registroEditar.existencias}"
                           required>


                    <button type="submit">

                        ${registroEditar != null
                            ? 'Actualizar'
                            : 'Guardar'}

                    </button>


                    <c:if test="${registroEditar != null}">

                        <a class="boton-secundario"
                           href="${pageContext.request.contextPath}/Tabla_Medicamentos">

                            Cancelar

                        </a>

                    </c:if>

                </form>

            </details>


            <table class="tabla">

                <thead>

                    <tr>

                        <th>Nombre</th>
                        <th>Función</th>
                        <th>Presentación</th>
                        <th>Lote</th>
                        <th>Vencimiento</th>
                        <th>Existencias</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach var="m"
                               items="${listaMedicamentos}">

                        <tr>

                            <td>${m.nombre}</td>

                            <td>${m.funcion}</td>

                            <td>${m.presentacion}</td>

                            <td>${m.lote}</td>

                            <td>${m.fechaVencimiento}</td>

                            <td>${m.existencias}</td>


                            <td class="acciones-tabla">

                                <form action="${pageContext.request.contextPath}/Tabla_Medicamentos"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="editar">

                                    <input type="hidden"
                                           name="id"
                                           value="${m.id_Medicamento}">

                                    <button class="editar"
                                            type="submit">

                                        Editar

                                    </button>

                                </form>


                                <form action="${pageContext.request.contextPath}/Tabla_Medicamentos"
                                      method="post">

                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="${m.id_Medicamento}">

                                    <button class="eliminar"
                                            type="submit"
                                            onclick="return confirm('¿Desea eliminar este medicamento?')">

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