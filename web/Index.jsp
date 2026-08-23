<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>NURSE - Iniciar Sesión</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Login.css">

</head> 

<body>

<div class="login-container">

    <img src="${pageContext.request.contextPath}/Img/Nurse Logo.png"
         alt="Logo Nurse"
         class="logo">

    <h2>Iniciar Sesión</h2>
    
    <%
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
%>

    <div class="mensaje-error">
        <%= mensaje %>
    </div>

<%
    }
%>

    <form action="${pageContext.request.contextPath}/Iniciar"
          method="POST"
          class="login-form">

        <div class="input-group">
            <label for="correo">Correo electrónico</label>
            <input type="email"
                   id="correo"
                   name="txtUsuario"
                   autocomplete="email"
                   required>
        </div>

        <div class="input-group">
            <label for="rethus">RETHUS</label>
            <input type="text"
                   id="rethus"
                   name="txtRethus"
                   autocomplete="off"
                   required>
        </div>

        <div class="input-group">
            <label for="password">Contraseña</label>
            <input type="password"
                   id="password"
                   name="txtPassword"
                   autocomplete="current-password"
                   required>
        </div>

     <button type="submit"
        class="btn-login">
    Ingresar
</button>

<div class="opciones-login">

    <a href="${pageContext.request.contextPath}/Vista/Recuperacion.jsp"
       class="btn-login">
        ¿Olvidaste tu contraseña?
    </a>

    <a href="${pageContext.request.contextPath}/Vista/Registro.jsp"
       class="btn-login">
        Crear Cuenta
    </a>

</div>
</form>
</div>
</body>
</html>
