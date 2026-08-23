<header class="nav-enfermeria">
    <a class="marca-enfermeria" href="${pageContext.request.contextPath}/DashboardEnfermeria"><img src="${pageContext.request.contextPath}/Img/Nurse Logo.png" alt="Nurse"><span>NURSE<small>&Aacute;rea de enfermer&iacute;a</small></span></a>
    <nav>
        <a class="${pageContext.request.servletPath == '/DashboardEnfermeria' ? 'activo' : ''}" href="${pageContext.request.contextPath}/DashboardEnfermeria">Inicio</a>
        <a class="${pageContext.request.servletPath == '/GestionPacientes' ? 'activo' : ''}" href="${pageContext.request.contextPath}/GestionPacientes">Pacientes</a>
        <a class="${pageContext.request.servletPath == '/GestionTratamientos' ? 'activo' : ''}" href="${pageContext.request.contextPath}/GestionTratamientos">Tratamientos</a>
        <a class="${pageContext.request.servletPath == '/GestionMedicamentos' ? 'activo' : ''}" href="${pageContext.request.contextPath}/GestionMedicamentos">Medicamentos</a>
        <a class="${pageContext.request.servletPath == '/Calendario' ? 'activo' : ''}" href="${pageContext.request.contextPath}/Calendario">Agenda</a>
    </nav>
    <a class="salir-enfermeria" href="${pageContext.request.contextPath}/CerrarSesion">Cerrar sesi&oacute;n</a>
</header>
