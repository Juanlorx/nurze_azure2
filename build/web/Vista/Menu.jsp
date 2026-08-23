```jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Usuarios"%>
<% if (!(session.getAttribute("usuario") instanceof Usuarios)) { response.sendRedirect(request.getContextPath() + "/Index.jsp"); return; } %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Nurse | Área de enfermería</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Menu.css">

</head>

<body>

<!-- =====================================================
     HEADER
===================================================== -->

<header class="header">

    <!-- LOGO -->
    <div class="logo">

        <img src="${pageContext.request.contextPath}/Img/Nurse Logo.png"
             alt="Nurse">

    </div>


    <!-- MENÚ -->
    <div class="header-menu">

        <nav>

            <ul>

                <li>
                    <a href="${pageContext.request.contextPath}/Vista/Menu.jsp">
                        Inicio
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/GestionPacientes">
                        Pacientes
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/GestionTratamientos">
                        Tratamientos
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/GestionMedicamentos">
                        Medicamentos
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/Calendario">
                        Agenda
                    </a>
                </li>

            </ul>

        </nav>

    </div>


    <!-- SESIÓN -->
    <div class="session-container">

        <a href="${pageContext.request.contextPath}/Index.jsp"
           class="Sesion">

            Cerrar sesión

        </a>

    </div>

</header>


<!-- =====================================================
     CONTENIDO PRINCIPAL
===================================================== -->

<main class="contenido-principal">


    <!-- =================================================
         SECCIÓN VISUAL
    ================================================== -->

    <section class="seccion-visual">


        <!-- =============================================
             CARRUSEL PACIENTES
        ============================================== -->

        <div class="carrusel-lateral">

            <h3>Gestión de Pacientes</h3>

            <div class="slider pacientes">

                <img src="${pageContext.request.contextPath}/Img/Guantes y tapa bocas.jpg"
                     class="active"
                     alt="Guantes y tapabocas">

                <img src="${pageContext.request.contextPath}/Img/Atencion.jpg"
                     alt="Atención de pacientes">

                <img src="${pageContext.request.contextPath}/Img/Pulso.jpg"
                     alt="Control del pulso">

                <img src="${pageContext.request.contextPath}/Img/Imagen de Inicio.jpg"
                     alt="Atención domiciliaria">

            </div>

        </div>


        <!-- =============================================
             VIDEO CENTRAL
        ============================================== -->

        <div class="video-destacado">

            <video autoplay
                   muted
                   loop
                   playsinline
                   controls>

                <source src="${pageContext.request.contextPath}/Img/Logo Nurse con movimiento.mp4"
                        type="video/mp4">

                Tu navegador no soporta videos HTML5.

            </video>

        </div>


        <!-- =============================================
             CARRUSEL MEDICAMENTOS
        ============================================== -->

        <div class="carrusel-lateral">

            <h3>Medicamentos</h3>

            <div class="slider medicamentos">

                <img src="${pageContext.request.contextPath}/Img/Pastillas.jpg"
                     class="active"
                     alt="Pastillas">

                <img src="${pageContext.request.contextPath}/Img/Medicamento.jpg"
                     alt="Medicamentos">

                <img src="${pageContext.request.contextPath}/Img/Pastas.jpg"
                     alt="Pastas">

                <img src="${pageContext.request.contextPath}/Img/Jeringa.jpg"
                     alt="Jeringa">

            </div>

        </div>


    </section>


    <!-- =================================================
         TEXTO INFORMATIVO
    ================================================== -->

    <section class="texto-informativo">

        <h1>NURSE</h1>

        <p>

            Nurse es una plataforma digital desarrollada para optimizar
            la gestión de la atención médica domiciliaria.

            Su propósito es apoyar el trabajo del personal de enfermería
            mediante herramientas que facilitan el registro, seguimiento
            y control de los pacientes.

        </p>


        <p>

            La plataforma permite organizar citas, consultar información
            clínica y gestionar medicamentos de manera eficiente,
            contribuyendo a una atención más segura, ordenada y de calidad.

        </p>


        <p>

            Desde esta área puedes consultar pacientes, programar visitas
            y mantener el seguimiento de cada atención realizada.

        </p>

    </section>


    <!-- =================================================
         ACCESOS RÁPIDOS
    ================================================== -->

    <section class="panel-informativo">


        <!-- AGENDA -->

        <a class="recomendacion"
           href="${pageContext.request.contextPath}/Calendario">

            <h2>Agenda de citas</h2>

            <p>

                Programa citas, revisa la hora y evita cruces
                de horarios entre enfermeras.

            </p>

            <strong>
                Ver agenda →
            </strong>

        </a>


        <!-- PACIENTES -->

        <a class="recomendacion"
           href="${pageContext.request.contextPath}/GestionPacientes">

            <h2>Pacientes</h2>

            <p>

                Consulta y completa las fichas clínicas
                de los pacientes asignados.

            </p>

            <strong>
                Ver pacientes →
            </strong>

        </a>


    </section>


    <!-- =================================================
         PANEL DE INFORMACIÓN
    ================================================== -->

    <section class="panel-informativo">


        <!-- =============================================
             NOTICIAS
        ============================================== -->

        <div class="noticias">

            <h2>Noticias de Salud</h2>


            <div class="noticia">

                <h4>OMS recomienda nuevas medidas</h4>

                <p>

                    Nuevas recomendaciones para prevenir
                    enfermedades respiratorias.

                </p>

            </div>


            <div class="noticia">

                <h4>Campaña de Vacunación 2026</h4>

                <p>

                    Se amplían las jornadas de vacunación
                    para adultos mayores.

                </p>

            </div>


            <div class="noticia">

                <h4>Atención Domiciliaria</h4>

                <p>

                    La atención en casa puede mejorar la calidad
                    de vida de pacientes que requieren seguimiento.

                </p>

            </div>

        </div>


        <!-- =============================================
             INFORMACIÓN LATERAL
        ============================================== -->

        <div class="lateral-info">


            <!-- RECOMENDACIÓN -->

            <div class="recomendacion">

                <h2>Recomendación del Día</h2>

                <p>

                    Verifique siempre los signos vitales
                    antes de cada procedimiento.

                </p>

            </div>


            <!-- PRÓXIMAS CITAS -->

            <div class="citas">

                <h2>Próximas Citas</h2>

                <ul>

                    <li>
                        Juan Pérez - 08:00 AM
                    </li>

                    <li>
                        María Gómez - 10:00 AM
                    </li>

                    <li>
                        Carlos Ruiz - 02:00 PM
                    </li>

                </ul>

            </div>

        </div>


    </section>


    <!-- =================================================
         BOTONES DE ACCESO
    ================================================== -->

    <section class="panel-informativo">


        <a class="recomendacion"
           href="${pageContext.request.contextPath}/GestionPacientes">

            <h2>Consultar pacientes</h2>

            <p>
                Accede al listado completo de pacientes registrados.
            </p>

            <strong>
                Ver pacientes →
            </strong>

        </a>


        <a class="recomendacion"
           href="${pageContext.request.contextPath}/Calendario">

            <h2>Consultar agenda</h2>

            <p>
                Revisa las citas y organiza las visitas domiciliarias.
            </p>

            <strong>
                Ver agenda →
            </strong>

        </a>


    </section>


    <section class="panel-informativo">
        <a class="recomendacion" href="${pageContext.request.contextPath}/GestionTratamientos">
            <h2>Tratamientos</h2><p>Asocia tratamientos y medicamentos a cada paciente.</p><strong>Gestionar tratamientos →</strong>
        </a>
        <a class="recomendacion" href="${pageContext.request.contextPath}/GestionMedicamentos">
            <h2>Medicamentos</h2><p>Registra y actualiza el inventario disponible.</p><strong>Gestionar medicamentos →</strong>
        </a>
    </section>
</main>


<!-- =====================================================
     JAVASCRIPT DEL CARRUSEL
===================================================== -->

<script src="${pageContext.request.contextPath}/JavaScript/Script_Menu.js"></script>


<!-- =====================================================
     FOOTER
===================================================== -->

<footer>

    <p>
        NURSE © 2026
    </p>

    <p>
        Sistema de gestión de pacientes y medicamentos
    </p>

</footer>


</body>

</html>
```
