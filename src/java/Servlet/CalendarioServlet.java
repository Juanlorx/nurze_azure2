package Servlet;

import Controlador.CitaDAO;
import Controlador.EnfermerasDAO;
import Controlador.PacienteDAO;
import Modelo.AgendaMensual;
import Modelo.Cita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

@WebServlet("/Calendario")
public class CalendarioServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate hoy = LocalDate.now();
        int anio = entero(request, "anio", hoy.getYear());
        int mes = entero(request, "mes", hoy.getMonthValue());
        YearMonth periodo;
        try { periodo = YearMonth.of(anio, mes); } catch (RuntimeException e) { periodo = YearMonth.from(hoy); }
        CitaDAO citas = new CitaDAO();
        request.setAttribute("anio", periodo.getYear()); request.setAttribute("mes", periodo.getMonthValue());
        request.setAttribute("nombreMes", periodo.getMonth().getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("es", "CO")));
        request.setAttribute("diasMes", periodo.lengthOfMonth());
        request.setAttribute("espaciosIniciales", periodo.atDay(1).getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        request.setAttribute("hoyDia", hoy.getMonthValue() == periodo.getMonthValue() && hoy.getYear() == periodo.getYear() ? hoy.getDayOfMonth() : 0);
        request.setAttribute("agenda", new AgendaMensual(citas.listarPorMes(periodo.getYear(), periodo.getMonthValue()), periodo.getYear(), periodo.getMonthValue()));
        request.setAttribute("listaPacientes", new PacienteDAO().listarPacientes());
        request.setAttribute("listaEnfermeras", new EnfermerasDAO().listarEnfermeras());
        request.getRequestDispatcher("/Vista/Calendario.jsp").forward(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int anio = entero(request, "anio", LocalDate.now().getYear()); int mes = entero(request, "mes", LocalDate.now().getMonthValue());
        CitaDAO dao = new CitaDAO();
        String mensaje;
        if ("cancelar".equals(request.getParameter("accion"))) {
            mensaje = dao.cancelar(entero(request, "id", 0)) ? "Cita cancelada." : "No fue posible cancelar la cita.";
        } else {
            try {
                Cita cita = new Cita(); cita.setPacienteId(entero(request, "paciente_id", 0)); cita.setEnfermeraId(entero(request, "enfermera_id", 0));
                cita.setFecha(Date.valueOf(request.getParameter("fecha"))); cita.setHoraInicio(Time.valueOf(request.getParameter("hora_inicio") + ":00"));
                cita.setHoraFin(Time.valueOf(request.getParameter("hora_fin") + ":00")); cita.setTipo(limpiar(request, "tipo")); cita.setNotas(limpiar(request, "notas"));
                if (cita.getHoraFin().after(cita.getHoraInicio()) && dao.crear(cita)) mensaje = "Cita programada correctamente.";
                else mensaje = "No se pudo agendar: la enfermera ya tiene una cita en ese horario o la hora final es inválida.";
            } catch (RuntimeException e) { mensaje = "Completa los datos de la cita correctamente."; }
        }
        request.getSession().setAttribute("mensajeCita", mensaje);
        response.sendRedirect(request.getContextPath() + "/Calendario?anio=" + anio + "&mes=" + mes);
    }
    private int entero(HttpServletRequest r, String n, int defecto) { try { return Integer.parseInt(r.getParameter(n)); } catch (Exception e) { return defecto; } }
    private String limpiar(HttpServletRequest r, String n) { String v = r.getParameter(n); return v == null ? "" : v.trim(); }
}
