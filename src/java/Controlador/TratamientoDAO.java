package Controlador;

import Conexion.Conexion;
import Modelo.Tratamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TratamientoDAO {

    private static final String LISTADO = "SELECT t.*, CONCAT(u.nombres, ' ', u.apellidos) AS paciente_nombre, "
            + "(SELECT GROUP_CONCAT(m.nombre ORDER BY m.nombre SEPARATOR ', ') FROM tratamiento_medicamento tm "
            + "INNER JOIN medicamento m ON m.idMedicamento = tm.medicamento_id WHERE tm.tratamiento_id = t.idTratamiento) AS medicamentos_nombres, "
            + "(SELECT GROUP_CONCAT(tm.medicamento_id) FROM tratamiento_medicamento tm "
            + "WHERE tm.tratamiento_id = t.idTratamiento) AS medicamentos_ids "
            + "FROM tratamiento t "
            + "INNER JOIN paciente p ON p.idPaciente = t.Paciente_idPaciente "
            + "INNER JOIN usuarios u ON u.idUsuarios = p.Usuarios_idUsuarios "
            + "ORDER BY t.fecha_inicio DESC, t.idTratamiento DESC";

    public List<Tratamiento> listarTratamientos() {
        List<Tratamiento> lista = new ArrayList<>();
        try (Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(LISTADO);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(desdeResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tratamientos: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertarTratamiento(Tratamiento tratamiento) {
        String sql = "INSERT INTO tratamiento (descripcion, Paciente_idPaciente, fecha_inicio, fecha_fin, estado, indicaciones, Medicamento_idMedicamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            asignarDatos(ps, tratamiento);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) {
                    con.rollback();
                    return false;
                }
                guardarMedicamentos(con, keys.getInt(1), tratamiento.getMedicamentosIds());
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar tratamiento: " + e.getMessage());
            return false;
        }
    }

    public Tratamiento consultarTratamiento(int idTratamiento) {
        String sql = LISTADO.replace("ORDER BY t.fecha_inicio DESC, t.idTratamiento DESC", "WHERE t.idTratamiento = ?");
        try (Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTratamiento);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? desdeResultSet(rs) : null;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar tratamiento: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarTratamiento(Tratamiento tratamiento) {
        String sql = "UPDATE tratamiento SET descripcion = ?, Paciente_idPaciente = ?, fecha_inicio = ?, fecha_fin = ?, estado = ?, indicaciones = ?, Medicamento_idMedicamento = ? WHERE idTratamiento = ?";
        try (Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            asignarDatos(ps, tratamiento);
            ps.setObject(7, primerMedicamento(tratamiento));
            ps.setInt(8, tratamiento.getid_Tratamiento());
            if (ps.executeUpdate() == 0) {
                con.rollback();
                return false;
            }
            try (PreparedStatement eliminar = con.prepareStatement("DELETE FROM tratamiento_medicamento WHERE tratamiento_id = ?")) {
                eliminar.setInt(1, tratamiento.getid_Tratamiento());
                eliminar.executeUpdate();
            }
            guardarMedicamentos(con, tratamiento.getid_Tratamiento(), tratamiento.getMedicamentosIds());
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar tratamiento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarTratamiento(int idTratamiento) {
        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false);
            try (PreparedStatement relacion = con.prepareStatement("DELETE FROM tratamiento_medicamento WHERE tratamiento_id = ?");
                PreparedStatement tratamiento = con.prepareStatement("DELETE FROM tratamiento WHERE idTratamiento = ?")) {
                relacion.setInt(1, idTratamiento);
                relacion.executeUpdate();
                tratamiento.setInt(1, idTratamiento);
                boolean eliminado = tratamiento.executeUpdate() > 0;
                con.commit();
                return eliminado;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar tratamiento: " + e.getMessage());
            return false;
        }
    }

    public int contarTratamientos() {
        try (Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tratamiento");
                ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al contar tratamientos: " + e.getMessage());
            return 0;
        }
    }

    private Tratamiento desdeResultSet(ResultSet rs) throws SQLException {
        Tratamiento t = new Tratamiento();
        t.setid_Tratamiento(rs.getInt("idTratamiento"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setPacienteId(rs.getInt("Paciente_idPaciente"));
        t.setPacienteNombre(rs.getString("paciente_nombre"));
        t.setFechaInicio(rs.getDate("fecha_inicio"));
        t.setFechaFin(rs.getDate("fecha_fin"));
        t.setEstado(rs.getString("estado"));
        t.setIndicaciones(rs.getString("indicaciones"));
        t.setMedicamentosIds(convertirIds(rs.getString("medicamentos_ids")));
        String nombres = rs.getString("medicamentos_nombres");
        t.setMedicamentosNombres(nombres == null || nombres.isEmpty() ? Collections.emptyList() : Arrays.asList(nombres.split(", ")));
        return t;
    }

    private void asignarDatos(PreparedStatement ps, Tratamiento t) throws SQLException {
        ps.setString(1, t.getDescripcion());
        ps.setInt(2, t.getPacienteId());
        ps.setDate(3, t.getFechaInicio());
        ps.setDate(4, t.getFechaFin());
        ps.setString(5, t.getEstado());
        ps.setString(6, t.getIndicaciones());
        ps.setObject(7, primerMedicamento(t));
    }

    private void guardarMedicamentos(Connection con, int tratamientoId, List<Integer> medicamentosIds) throws SQLException {
        if (medicamentosIds == null || medicamentosIds.isEmpty()) return;
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO tratamiento_medicamento (tratamiento_id, medicamento_id) VALUES (?, ?)")) {
            for (Integer medicamentoId : medicamentosIds) {
                ps.setInt(1, tratamientoId);
                ps.setInt(2, medicamentoId);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private List<Integer> convertirIds(String ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        return Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private Integer primerMedicamento(Tratamiento tratamiento) {
        return tratamiento.getMedicamentosIds() == null || tratamiento.getMedicamentosIds().isEmpty()
                ? null : tratamiento.getMedicamentosIds().get(0);
    }
}
