<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Formulario de Registro Nurse</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Ccs/Style_Registro.css">
</head>

<body>
    <section class="hero">
        <div class="form-container">
            <div class="register-container">

                <div class="logo-container">
                    <img src="${pageContext.request.contextPath}/Img/Nurse Logo.png"
                         alt="Nurse Logo"
                         class="logo">
                </div>

                <div class="title-container">
                    <h1>REGISTRARSE</h1>
                </div>

                <div class="form-content">
                    <form action="${pageContext.request.contextPath}/RegistroServlet"
                          method="POST">

                        <div class="form-group">
                            <label for="nombre">Nombre</label>

                            <input type="text"
                                   id="nombre"
                                   name="txtNombre"
                                   placeholder="Ingrese su nombre"
                                   autocomplete="given-name"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="apellido">Apellido</label>

                            <input type="text"
                                   id="apellido"
                                   name="txtApellido"
                                   placeholder="Ingrese su apellido"
                                   autocomplete="family-name"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="tipoDocumento">
                                Tipo de documento
                            </label>

                            <select id="tipoDocumento"
                                    name="txtTipoDocumento"
                                    required>
                                <option value="" selected disabled>
                                    Seleccione
                                </option>

                                <option value="CC">
                                    Cédula de ciudadanía
                                </option>

                                <option value="TI">
                                    Tarjeta de identidad
                                </option>

                                <option value="PAS">
                                    Pasaporte
                                </option>

                                <option value="CE">
                                    Cédula de extranjería
                                </option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="documento">
                                Número de documento
                            </label>

                            <input type="text"
                                   id="documento"
                                   name="txtDocumento"
                                   placeholder="Ingrese su documento"
                                   inputmode="numeric"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="telefono">Teléfono</label>

                            <input type="tel"
                                   id="telefono"
                                   name="txtTelefono"
                                   placeholder="Ingrese su teléfono"
                                   autocomplete="tel"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="direccion">Dirección</label>

                            <input type="text"
                                   id="direccion"
                                   name="txtDireccion"
                                   placeholder="Ingrese su dirección"
                                   autocomplete="street-address"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="rethus">RETHUS</label>

                            <input type="text"
                                   id="rethus"
                                   name="txtRethus"
                                   placeholder="Ingrese su código RETHUS"
                                   inputmode="numeric"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="correo">Correo</label>

                            <input type="email"
                                   id="correo"
                                   name="txtCorreo"
                                   placeholder="Ingrese su correo"
                                   autocomplete="email"
                                   required>
                        </div>

                        <div class="form-group">
                            <label for="password">Contraseña</label>

                            <input type="password"
                                   id="password"
                                   name="txtPassword"
                                   placeholder="Ingrese su contraseña"
                                   autocomplete="new-password"
                                   required>
                        </div>

                        <div class="checkbox-container">
                            <div class="checkbox">
                                <input type="checkbox"
                                       id="politica"
                                       name="aceptaPolitica"
                                       value="true"
                                       required>

                                <label for="politica">
                                    Acepto la política de tratamiento de
                                    datos personales
                                </label>
                            </div>
                        </div>

                        <div class="button-container">
                            <button type="submit"
                                    class="btn-register">
                                Registrarse
                            </button>
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
        </div>
    </section>
</body>

</html>
