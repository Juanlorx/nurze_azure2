<aside class="sidebar">
    <div class="logo-section">
        <img src="${pageContext.request.contextPath}/Img/Nurse Logo.png" alt="Logo">
        <h2>NURSE</h2>
    </div>

    <ul class="menu">
        <li class="${pageContext.request.servletPath == '/Dashboard' ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/Dashboard">Dashboard</a>
        </li>
        <li class="${pageContext.request.servletPath == '/Tabla_Usuarios' ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/Tabla_Usuarios">Usuarios</a>
        </li>
        <li class="${pageContext.request.servletPath == '/Tabla_Enfermeras' ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/Tabla_Enfermeras">Enfermeras</a>
        </li>
        <li class="${pageContext.request.servletPath == '/Tabla_Roles' ? 'active' : ''}">
            <a href="${pageContext.request.contextPath}/Tabla_Roles">Roles</a>
        </li>
    </ul>
</aside>
