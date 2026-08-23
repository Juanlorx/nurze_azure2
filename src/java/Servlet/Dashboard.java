package Servlet;

import Controlador.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import Modelo.Usuarios;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Object sesionUsuario = request.getSession(false) == null ? null
                : request.getSession(false).getAttribute("usuario");
        if (!(sesionUsuario instanceof Usuarios)
                || ((Usuarios) sesionUsuario).getRoles_idRoles() != 1) {
            response.sendRedirect(request.getContextPath() + "/Index.jsp");
            return;
        }

        // CONTADORES

        request.setAttribute(
                "totalUsuarios",
                new UsuariosDAO().contarUsuarios());

        request.setAttribute(
                "totalPacientes",
                new PacienteDAO().contarPacientes());

        request.setAttribute(
                "totalEnfermeras",
                new UsuariosDAO().contarUsuariosPorRol("enfermer"));

        request.setAttribute(
                "totalMedicamentos",
                new MedicamentoDAO().contarMedicamentos());

        request.setAttribute(
                "totalTratamientos",
                new TratamientoDAO().contarTratamientos());

        request.setAttribute(
                "totalRoles",
                new RolesDAO().contarRoles());

        // LISTAS

        request.setAttribute(
                "listaUsuarios",
                new UsuariosDAO().listarUsuarios());

        request.setAttribute(
                "listaPacientes",
                new PacienteDAO().listarPacientes());

        request.setAttribute(
                "listaEnfermeras",
                new UsuariosDAO().listarUsuariosPorRol("enfermer"));

        request.setAttribute(
                "listaMedicamentos",
                new MedicamentoDAO().listarMedicamentos());

        request.setAttribute(
                "listaTratamientos",
                new TratamientoDAO().listarTratamientos());

        request.setAttribute(
                "listaRoles",
                new RolesDAO().listarRoles());

        request.getRequestDispatcher(
                "/Vista/Panel.jsp")
                .forward(request, response);
    }
}
