package Servlet;

import Controlador.UsuariosDAO;
import Modelo.Usuarios;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistroServlet")
public class Registro extends HttpServlet {

   @Override
protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    System.out.println("Entró al servlet Registro");

    UsuariosDAO dao = new UsuariosDAO();


        String correo =
                request.getParameter("txtCorreo");

        // Verificar si ya existe
        if (dao.existeCorreo(correo)) {

            request.setAttribute(
                    "mensaje",
                    "El correo ya se encuentra registrado");

            request.getRequestDispatcher(
                    "/Vista/Registro.jsp")
                    .forward(request, response);

            return;
        }

        Usuarios usuario = new Usuarios();

        usuario.setNombres(
                request.getParameter("txtNombre"));

        usuario.setApellidos(
                request.getParameter("txtApellido"));

        usuario.setIdentificacion(
                request.getParameter("txtDocumento"));

        usuario.setCorreo(
                request.getParameter("txtCorreo"));

        usuario.setClave(
                request.getParameter("txtPassword"));

        usuario.setRethus(
                request.getParameter("txtRethus"));

        usuario.setTelefono(
        request.getParameter("txtTelefono"));

        usuario.setDireccion(
        request.getParameter("txtDireccion"));

        // Rol por defecto
        usuario.setRoles_idRoles(1);

        boolean registrado =
                dao.insertarUsuario(usuario);

        if (registrado) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/Index.jsp");

        } else {

            request.setAttribute(
                    "mensaje",
                    "Error al registrar usuario");

            request.getRequestDispatcher(
                    "/Vista/Registro.jsp")
                    .forward(request, response);
        }
    }
}