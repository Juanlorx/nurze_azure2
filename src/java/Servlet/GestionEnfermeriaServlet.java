package Servlet;

import Controlador.MedicamentoDAO;
import Controlador.PacienteDAO;
import Controlador.TratamientoDAO;
import Controlador.UsuariosDAO;
import Modelo.Medicamento;
import Modelo.Paciente;
import Modelo.Tratamiento;
import Modelo.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/** Operaciones clínicas disponibles exclusivamente para el personal de enfermería. */
@WebServlet({"/GestionPacientes", "/GestionTratamientos", "/GestionMedicamentos", "/GestionPacientesFormulario", "/GestionTratamientosFormulario", "/GestionMedicamentosFormulario"})
public class GestionEnfermeriaServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!esEnfermera(req, resp)) return;
        String modulo = modulo(req);
        if (entero(req, "id") > 0) cargarEdicion(req, modulo, entero(req, "id"));
        cargarListas(req);
        String sufijo = req.getServletPath().endsWith("Formulario") ? "Formulario" : "";
        req.getRequestDispatcher("/Vista/Enfermeria" + nombreVista(modulo) + sufijo + ".jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!esEnfermera(req, resp)) return;
        req.setCharacterEncoding("UTF-8");
        String modulo = modulo(req);
        String accion = texto(req, "accion");
        boolean exito = false;
        try {
            if ("pacientes".equals(modulo)) exito = guardarPaciente(req, accion);
            if ("tratamientos".equals(modulo)) exito = guardarTratamiento(req, accion);
            if ("medicamentos".equals(modulo)) exito = guardarMedicamento(req, accion);
        } catch (RuntimeException e) { exito = false; }
        req.getSession().setAttribute("mensajeGestion", exito ? "Información guardada correctamente." : "No fue posible guardar. Verifica los datos enviados.");
        resp.sendRedirect(req.getContextPath() + "/Gestion" + nombreVista(modulo));
    }

    private boolean guardarPaciente(HttpServletRequest r, String accion) {
        Paciente p = paciente(r); Usuarios u = usuarioPaciente(r);
        PacienteDAO dao = new PacienteDAO();
        return "actualizar".equals(accion) ? dao.actualizarPacienteCompleto(p, u) : dao.insertarPacienteCompleto(p, u);
    }
    private boolean guardarTratamiento(HttpServletRequest r, String accion) {
        Tratamiento t = new Tratamiento(); t.setid_Tratamiento(entero(r,"id")); t.setPacienteId(entero(r,"paciente_id"));
        t.setDescripcion(texto(r,"descripcion")); t.setFechaInicio(Date.valueOf(texto(r,"fecha_inicio"))); t.setFechaFin(Date.valueOf(texto(r,"fecha_fin")));
        t.setEstado(texto(r,"estado")); t.setIndicaciones(texto(r,"indicaciones")); t.setMedicamentosIds(ids(r,"medicamento_id"));
        TratamientoDAO dao = new TratamientoDAO(); return "actualizar".equals(accion) ? dao.actualizarTratamiento(t) : dao.insertarTratamiento(t);
    }
    private boolean guardarMedicamento(HttpServletRequest r, String accion) {
        Medicamento m = new Medicamento(); m.setid_Medicamento(entero(r,"id")); m.setNombre(texto(r,"nombre")); m.setFuncion(texto(r,"funcion"));
        m.setPresentacion(texto(r,"presentacion")); m.setLote(texto(r,"lote")); m.setFechaVencimiento(Date.valueOf(texto(r,"fecha_vencimiento"))); m.setExistencias(entero(r,"existencias"));
        MedicamentoDAO dao = new MedicamentoDAO(); return "actualizar".equals(accion) ? dao.actualizarMedicamento(m) : dao.insertarMedicamento(m);
    }
    private void cargarEdicion(HttpServletRequest r, String m, int id) {
        if ("pacientes".equals(m)) { Paciente p = new PacienteDAO().consultarPaciente(id); r.setAttribute("registroEditar", p); if (p != null) r.setAttribute("usuarioEditar", new UsuariosDAO().consultarUsuario(p.getUsuariosIdUsuarios())); }
        if ("tratamientos".equals(m)) r.setAttribute("registroEditar", new TratamientoDAO().consultarTratamiento(id));
        if ("medicamentos".equals(m)) r.setAttribute("registroEditar", new MedicamentoDAO().consultarMedicamento(id));
    }
    private void cargarListas(HttpServletRequest r) { r.setAttribute("listaPacientes", new PacienteDAO().listarPacientes()); r.setAttribute("listaMedicamentos", new MedicamentoDAO().listarMedicamentos()); r.setAttribute("listaTratamientos", new TratamientoDAO().listarTratamientos()); }
    private Paciente paciente(HttpServletRequest r) { Paciente p = new Paciente(); p.setIdPaciente(entero(r,"id")); p.setUsuariosIdUsuarios(entero(r,"usuario_id")); p.setFecha_nacimiento(Date.valueOf(texto(r,"fecha_nacimiento"))); p.setDiagnostico(texto(r,"historial")); return p; }
    private Usuarios usuarioPaciente(HttpServletRequest r) { Usuarios u = new Usuarios(); u.setid_Usuarios(entero(r,"usuario_id")); u.setNombres(texto(r,"nombres")); u.setApellidos(texto(r,"apellidos")); u.setIdentificacion(texto(r,"identificacion")); u.setTelefono(texto(r,"telefono")); u.setDireccion(texto(r,"direccion")); u.setCorreo(texto(r,"correo")); return u; }
    private boolean esEnfermera(HttpServletRequest r, HttpServletResponse s) throws IOException {
        Object o = r.getSession(false) == null ? null : r.getSession(false).getAttribute("usuario");
        if (!(o instanceof Usuarios)) {
            s.sendRedirect(r.getContextPath() + "/Index.jsp");
            return false;
        }
        String rol = ((Usuarios) o).getRolDescripcion();
        if (rol == null || !rol.toLowerCase().contains("enfermer")) {
            s.sendRedirect(r.getContextPath() + "/Index.jsp?acceso=denegado");
            return false;
        }
        return true;
    }
    private String modulo(HttpServletRequest r) { String p=r.getServletPath(); return p.contains("Pacientes") ? "pacientes" : p.contains("Tratamientos") ? "tratamientos" : "medicamentos"; }
    private String nombreVista(String m) { return "pacientes".equals(m) ? "Pacientes" : "tratamientos".equals(m) ? "Tratamientos" : "Medicamentos"; }
    private String texto(HttpServletRequest r,String n) { String v=r.getParameter(n); return v == null ? "" : v.trim(); }
    private int entero(HttpServletRequest r,String n) { try { return Integer.parseInt(texto(r,n)); } catch(Exception e) { return 0; } }
    private List<Integer> ids(HttpServletRequest r,String n) { List<Integer> out=new ArrayList<>(); String[] vs=r.getParameterValues(n); if(vs != null) for(String v:vs) try { out.add(Integer.valueOf(v)); } catch(NumberFormatException e) {} return out; }
}
