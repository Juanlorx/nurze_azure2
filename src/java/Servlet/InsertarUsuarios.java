package Servlet;

import Modelo.Usuarios;
import Controlador.UsuariosDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InsertarUsuarios", urlPatterns = {"/InsertarUsuarios"})
public class InsertarUsuarios extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            Usuarios usuario = new Usuarios();

            usuario.setNombres(request.getParameter("nombres"));
            usuario.setApellidos(request.getParameter("apellidos"));
            usuario.setIdentificacion(request.getParameter("identificacion"));
            usuario.setTelefono(request.getParameter("telefono"));
            usuario.setDireccion(request.getParameter("direccion"));
            usuario.setCorreo(request.getParameter("correo"));
            usuario.setClave(request.getParameter("clave"));
            usuario.setRethus(request.getParameter("Rethus"));

            // Validar rol antes de convertirlo
            String rolStr = request.getParameter("Roles_idRoles");

            if (rolStr == null || rolStr.trim().isEmpty()) {

                request.setAttribute(
                        "mensaje",
                        "Debe seleccionar un rol."
                );

                request.getRequestDispatcher("registro.jsp")
                        .forward(request, response);
                return;
            }

            usuario.setRoles_idRoles(Integer.parseInt(rolStr));

            UsuariosDAO dao = new UsuariosDAO();

            // Verificar correo repetido
            if (dao.existeCorreo(usuario.getCorreo())) {

                request.setAttribute(
                        "mensaje",
                        "El correo ya está registrado."
                );

                request.getRequestDispatcher("registro.jsp")
                        .forward(request, response);
                return;
            }

            boolean resultado = dao.insertarUsuario(usuario);

            if (resultado) {

                request.setAttribute(
                        "mensaje",
                        "Usuario registrado correctamente."
                );

            } else {

                request.setAttribute(
                        "mensaje",
                        "Error al registrar usuario."
                );
            }

            request.getRequestDispatcher("registro.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "mensaje",
                    "El rol seleccionado no es válido."
            );

            request.getRequestDispatcher("registro.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "mensaje",
                    "Error interno: " + e.getMessage()
            );

            request.getRequestDispatcher("registro.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("registro.jsp");
    }
} 