package Servlet;

import Controlador.RolesDAO;

import Modelo.Roles;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CargarRegistro")
public class CargarRegistro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        RolesDAO dao =
                new RolesDAO();

        request.getRequestDispatcher(
                "/Vista/Registrarse.jsp")
                .forward(request,
                        response);
    }
}