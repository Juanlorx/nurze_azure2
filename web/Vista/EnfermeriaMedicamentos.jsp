
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!-- Contar medicamentos con stock bajo -->
<c:set var="bajo" value="0"/>

<c:forEach var="m" items="${listaMedicamentos}">
    <c:if test="${m.existencias <= 10}">
        <c:set var="bajo" value="${bajo + 1}"/>
    </c:if>
</c:forEach>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Medicamentos | Nurse</title>

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

                <!-- Título y registro -->
                <section class="encabezado" id="encabezado">

                    <div>
                        <span class="eyebrow">INVENTARIO CLÍNICO</span>

                        <h1>Gestión de medicamentos</h1>

                        <p>
                            Registra y controla el inventario disponible
                            para los tratamientos.
                        </p>
                    </div>

                    <a
                        class="btn-principal"
                        id="btn-registrar"
                        href="${pageContext.request.contextPath}/GestionMedicamentosFormulario"
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

                <!-- Métricas del inventario -->
                <section class="metricas" id="metricas">

                    <!-- Medicamentos registrados -->
                    <article class="metrica" id="metrica-medicamentos">

                        <div class="icono">✚</div>

                        <div>
                            <small>Medicamentos registrados</small>
                            <strong>${listaMedicamentos.size()}</strong>
                        </div>

                    </article>

                    <!-- Medicamentos con stock bajo -->
                    <article class="metrica" id="metrica-stock-bajo">

                        <div class="icono rojo">!</div>

                        <div>
                            <small>Stock bajo (≤ 10)</small>
                            <strong>${bajo}</strong>
                        </div>

                    </article>

                    <!-- Tratamientos vinculados -->
                    <article class="metrica" id="metrica-tratamientos">

                        <div class="icono verde">▤</div>

                        <div>
                            <small>Tratamientos vinculados</small>
                            <strong>${listaTratamientos.size()}</strong>
                        </div>

                    </article>

                </section>

                <!-- Inventario de medicamentos -->
                <section class="panel" id="panel-medicamentos">

                    <div class="panel-head">

                        <h2>Inventario de medicamentos</h2>

                        <!-- Buscador -->
                        <input
                            type="search"
                            id="buscador-medicamentos"
                            class="buscador"
                            data-filtro="tabla-medicamentos"
                            placeholder="⌕ Buscar medicamento o lote"
                        >

                    </div>

                    <!-- Tabla de medicamentos -->
                    <div class="tabla-wrap" id="contenedor-tabla">

                        <table class="tabla" id="tabla-medicamentos">

                            <thead>

                                <tr>
                                    <th>Medicamento</th>
                                    <th>Uso terapéutico</th>
                                    <th>Presentación</th>
                                    <th>Lote</th>
                                    <th>Vencimiento</th>
                                    <th>Stock</th>
                                    <th>Acciones</th>
                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="m" items="${listaMedicamentos}">

                                    <tr>

                                        <!-- Nombre del medicamento -->
                                        <td>

                                            <div class="persona">

                                                <span class="avatar">
                                                    ${m.nombre.substring(0,1)}
                                                </span>

                                                <div>

                                                    <strong>
                                                        ${m.nombre}
                                                    </strong>

                                                    <small>
                                                        Inventario clínico
                                                    </small>

                                                </div>

                                            </div>

                                        </td>

                                        <!-- Uso terapéutico -->
                                        <td>
                                            ${m.funcion}
                                        </td>

                                        <!-- Presentación -->
                                        <td>
                                            ${m.presentacion}
                                        </td>

                                        <!-- Lote -->
                                        <td>
                                            ${m.lote}
                                        </td>

                                        <!-- Fecha de vencimiento -->
                                        <td>
                                            ${m.fechaVencimiento}
                                        </td>

                                        <!-- Existencias -->
                                        <td>

                                            <span class="estado ${m.existencias <= 10 ? 'stock-bajo' : 'stock-ok'}">
                                                ${m.existencias} unidades
                                            </span>

                                        </td>

                                        <!-- Acciones -->
                                        <td>

                                            <a
                                                class="editar"
                                                id="editar-${m.id_Medicamento}"
                                                href="${pageContext.request.contextPath}/GestionMedicamentosFormulario?id=${m.id_Medicamento}"
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
