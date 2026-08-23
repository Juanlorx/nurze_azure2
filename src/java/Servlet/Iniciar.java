package Servlet;

import Controlador.UsuariosDAO;
import Modelo.Usuarios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Iniciar")
public class Iniciar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("txtUsuario");
        String password = request.getParameter("txtPassword");
        String rethus = request.getParameter("txtRethus");

        UsuariosDAO dao = new UsuariosDAO();

        Usuarios usuario = dao.consultarUsuarioCorreo(correo);

        // Usuario no existe
       if (usuario == null) {

    request.setAttribute("mensaje",
            "Usuario no registrado");

    request.getRequestDispatcher("/Index.jsp")
           .forward(request, response);

    return;
}
        if (!usuario.getRethus().equals(rethus)) {
            request.setAttribute("mensaje", "RETHUS incorrecto");
            request.getRequestDispatcher("/Index.jsp")
                    .forward(request, response);
            return;
        }

        // Contraseña incorrecta
       if (!usuario.getClave().equals(password)) {

    request.setAttribute("mensaje",
            "Contraseña incorrecta");

    request.getRequestDispatcher("/Index.jsp")
           .forward(request, response);

    return;
}

        request.getSession().setAttribute("usuario", usuario);

        // El rol se obtiene de la cuenta registrada en la base de datos.
        if (usuario.getRoles_idRoles() == 1) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/Dashboard");
        } else if (usuario.getRoles_idRoles() == 2) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/DashboardEnfermeria");
        } else {
            request.setAttribute("mensaje",
                    "El usuario no tiene un rol de acceso válido");
            request.getRequestDispatcher("/Index.jsp")
                    .forward(request, response);
        }
    }
}
