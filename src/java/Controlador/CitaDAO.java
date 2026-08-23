package Controlador;

import Conexion.Conexion;
import Modelo.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    private static final String BASE = "SELECT a.idAtencion, a.descripcion, a.estado, a.notas, "
            + "p.idPaciente, CONCAT(up.nombres, ' ', up.apellidos) AS paciente_nombre, "
            + "e.idenfermeras, CONCAT(ue.nombres, ' ', ue.apellidos) AS enfermera_nombre, "
            + "h.fecha, h.hora_inicial, h.hora_final "
            + "FROM atencion a INNER JOIN horarios h ON h.idHorarios = a.Horarios_idHorarios "
            + "INNER JOIN paciente p ON p.idPaciente = a.Paciente_idPaciente "
            + "INNER JOIN usuarios up ON up.idUsuarios = p.Usuarios_idUsuarios "
            + "INNER JOIN enfermeras e ON e.idenfermeras = a.enfermeras_idenfermeras "
            + "INNER JOIN usuarios ue ON ue.idUsuarios = e.Usuarios_idUsuarios ";

    public List<Cita> listarPorMes(int anio, int mes) {
        List<Cita> citas = new ArrayList<>();
        String sql = BASE + "WHERE YEAR(h.fecha) = ? AND MONTH(h.fecha) = ? ORDER BY h.fecha, h.hora_inicial";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, anio); ps.setInt(2, mes);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) citas.add(desdeRs(rs)); }
        } catch (SQLException e) { System.out.println("Error al listar citas: " + e.getMessage()); }
        return citas;
    }

    public boolean crear(Cita cita) {
        if (!horarioDisponible(cita)) return false;
        String horario = "INSERT INTO horarios (fecha, hora_inicial, hora_final) VALUES (?, ?, ?)";
        String atencion = "INSERT INTO atencion (descripcion, enfermeras_idenfermeras, Paciente_idPaciente, Horarios_idHorarios, estado, notas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = new Conexion().getConexion(); PreparedStatement psHorario = con.prepareStatement(horario, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            psHorario.setDate(1, cita.getFecha()); psHorario.setTime(2, cita.getHoraInicio()); psHorario.setTime(3, cita.getHoraFin());
            psHorario.executeUpdate();
            try (ResultSet keys = psHorario.getGeneratedKeys()) {
                if (!keys.next()) { con.rollback(); return false; }
                try (PreparedStatement psAtencion = con.prepareStatement(atencion)) {
                    psAtencion.setString(1, cita.getTipo()); psAtencion.setInt(2, cita.getEnfermeraId());
                    psAtencion.setInt(3, cita.getPacienteId()); psAtencion.setInt(4, keys.getInt(1));
                    psAtencion.setString(5, "Programada"); psAtencion.setString(6, cita.getNotas());
                    psAtencion.executeUpdate();
                }
            }
            con.commit(); return true;
        } catch (SQLException e) { System.out.println("Error al agendar cita: " + e.getMessage()); return false; }
    }

    public boolean cancelar(int idCita) {
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement("UPDATE atencion SET estado = 'Cancelada' WHERE idAtencion = ?")) {
            ps.setInt(1, idCita); return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.out.println("Error al cancelar cita: " + e.getMessage()); return false; }
    }

    private boolean horarioDisponible(Cita cita) {
        String sql = "SELECT 1 FROM atencion a INNER JOIN horarios h ON h.idHorarios = a.Horarios_idHorarios "
                + "WHERE a.enfermeras_idenfermeras = ? AND h.fecha = ? AND a.estado <> 'Cancelada' "
                + "AND h.hora_inicial < ? AND h.hora_final > ? LIMIT 1";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cita.getEnfermeraId()); ps.setDate(2, cita.getFecha()); ps.setTime(3, cita.getHoraFin()); ps.setTime(4, cita.getHoraInicio());
            try (ResultSet rs = ps.executeQuery()) { return !rs.next(); }
        } catch (SQLException e) { System.out.println("Error validando horario: " + e.getMessage()); return false; }
    }

    private Cita desdeRs(ResultSet rs) throws SQLException {
        Cita c = new Cita(); c.setIdCita(rs.getInt("idAtencion")); c.setTipo(rs.getString("descripcion")); c.setEstado(rs.getString("estado")); c.setNotas(rs.getString("notas"));
        c.setPacienteId(rs.getInt("idPaciente")); c.setPacienteNombre(rs.getString("paciente_nombre")); c.setEnfermeraId(rs.getInt("idenfermeras")); c.setEnfermeraNombre(rs.getString("enfermera_nombre"));
        c.setFecha(rs.getDate("fecha")); c.setHoraInicio(rs.getTime("hora_inicial")); c.setHoraFin(rs.getTime("hora_final")); return c;
    }
}
