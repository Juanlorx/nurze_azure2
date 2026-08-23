<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        NURSE - Recuperar Contraseña
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Recuperacion.css">

</head>

<body>

    <div class="recover-container">

        <div class="logo-container">

            <img src="${pageContext.request.contextPath}/Img/Nurse Logo.png"
                 alt="Nurse Logo"
                 class="logo">

        </div>

        <div class="title-container">

            <h2>
                Recuperar Contraseña
            </h2>

            <p class="subtitle">

                Ingresa tu correo electrónico y te enviaremos instrucciones para recuperar tu contraseña.

            </p>

        </div>

        <div class="form-container">

            <form id="recoverForm"
                  action="RecuperacionServlet"
                  method="POST">

                <div class="input-group">

                    <label for="email">
                        Correo electrónico
                    </label>

                    <input type="email"
                           id="email"
                           name="txtCorreo"
                           placeholder="Ingrese su correo"
                           required>

                </div>

                <div class="button-container">

                    <button type="submit"
                            class="btn-recover">

                        Enviar recuperación

                    </button>

                </div>

                <div class="message-container">

                    <%
                        String mensaje = (String) request.getAttribute("mensaje");

                        if (mensaje != null) {
                    %>

                        <p class="success">

                            <%= mensaje %>

                        </p>

                    <%
                        }
                    %>

                </div>

            </form>

        </div>

        <div class="footer-text">

            <a href="${pageContext.request.contextPath}/Index.jsp"
               class="back-login">

                Volver al inicio de sesión

            </a>

        </div>

    </div>

    <script src="${pageContext.request.contextPath}/JavaScript/Script_Recuperacion.js"></script>

</body>

</html>