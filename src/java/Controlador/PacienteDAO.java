package Controlador;

import Conexion.Conexion;
import Modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Usuarios;

public class PacienteDAO {

    /** Crea la cuenta de paciente y su ficha clínica en una única transacción. */
    public boolean insertarPacienteCompleto(Paciente paciente, Usuarios usuario) {
        String usuarioSql = "INSERT INTO usuarios (nombres, apellidos, identificacion, telefono, direccion, correo, clave, Rethus, Roles_idRoles) "
                + "SELECT ?, ?, ?, ?, ?, ?, '', '', idRoles FROM roles WHERE LOWER(descripcionatencion) LIKE '%paciente%' LIMIT 1";
        String fichaSql = "INSERT INTO paciente (fecha_nacimiento, diagnostico, Usuarios_idUsuarios) VALUES (?, ?, ?)";
        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false);
            try (PreparedStatement us = con.prepareStatement(usuarioSql, Statement.RETURN_GENERATED_KEYS)) {
                us.setString(1, usuario.getNombres()); us.setString(2, usuario.getApellidos()); us.setString(3, usuario.getIdentificacion());
                us.setString(4, usuario.getTelefono()); us.setString(5, usuario.getDireccion()); us.setString(6, usuario.getCorreo());
                if (us.executeUpdate() == 0) { con.rollback(); return false; }
                try (ResultSet keys = us.getGeneratedKeys()) {
                    if (!keys.next()) { con.rollback(); return false; }
                    try (PreparedStatement ps = con.prepareStatement(fichaSql)) {
                        ps.setDate(1, paciente.getFecha_nacimiento()); ps.setString(2, paciente.getDiagnostico()); ps.setInt(3, keys.getInt(1));
                        if (ps.executeUpdate() == 0) { con.rollback(); return false; }
                    }
                }
            }
            con.commit(); return true;
        } catch (SQLException e) { System.out.println("Error crear paciente: " + e.getMessage()); return false; }
    }

    /** Actualiza la ficha y los datos identificadores del paciente asociado. */
    public boolean actualizarPacienteCompleto(Paciente paciente, Usuarios usuario) {
        String fichaSql = "UPDATE paciente SET fecha_nacimiento=?, diagnostico=? WHERE idPaciente=? AND Usuarios_idUsuarios=?";
        String usuarioSql = "UPDATE usuarios SET nombres=?, apellidos=?, identificacion=?, telefono=?, direccion=?, correo=? WHERE idUsuarios=?";
        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(fichaSql); PreparedStatement us = con.prepareStatement(usuarioSql)) {
                ps.setDate(1,paciente.getFecha_nacimiento()); ps.setString(2,paciente.getDiagnostico()); ps.setInt(3,paciente.getIdPaciente()); ps.setInt(4,paciente.getUsuariosIdUsuarios());
                if (ps.executeUpdate() == 0) { con.rollback(); return false; }
                us.setString(1,usuario.getNombres()); us.setString(2,usuario.getApellidos()); us.setString(3,usuario.getIdentificacion()); us.setString(4,usuario.getTelefono()); us.setString(5,usuario.getDireccion()); us.setString(6,usuario.getCorreo()); us.setInt(7,paciente.getUsuariosIdUsuarios());
                if (us.executeUpdate() == 0) { con.rollback(); return false; }
            }
            con.commit(); return true;
        } catch (SQLException e) { System.out.println("Error actualizar paciente: " + e.getMessage()); return false; }
    }

    // INSERTAR
    public boolean insertarPaciente(Paciente paciente) {

        String sql = "INSERT INTO paciente "
                + "(fecha_nacimiento, diagnostico, Usuarios_idUsuarios)"
                + " VALUES (?,?,?)";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, paciente.getFecha_nacimiento());
            ps.setString(2, paciente.getDiagnostico());
            ps.setInt(3, paciente.getUsuariosIdUsuarios());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error insertar paciente: " + e.getMessage());

        }

        return false;

    }

    // CONSULTAR
    public Paciente consultarPaciente(int idPaciente) {

        String sql = "SELECT p.*, u.identificacion, CONCAT(u.nombres, ' ', u.apellidos) AS nombreCompleto "
                + "FROM paciente p INNER JOIN usuarios u ON u.idUsuarios = p.Usuarios_idUsuarios WHERE p.idPaciente=?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Paciente p = new Paciente();

                p.setIdPaciente(rs.getInt("idPaciente"));
                p.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                p.setDiagnostico(rs.getString("diagnostico"));
                p.setUsuariosIdUsuarios(rs.getInt("Usuarios_idUsuarios"));
                p.setNombreCompleto(rs.getString("nombreCompleto"));
                p.setIdentificacion(rs.getString("identificacion"));

                return p;

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return null;

    }

    // ACTUALIZAR
    public boolean actualizarPaciente(Paciente paciente) {

        String sql = "UPDATE paciente SET "
                + "fecha_nacimiento=?,"
                + "diagnostico=?,"
                + "Usuarios_idUsuarios=? "
                + "WHERE idPaciente=?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, paciente.getFecha_nacimiento());
            ps.setString(2, paciente.getDiagnostico());
            ps.setInt(3, paciente.getUsuariosIdUsuarios());
            ps.setInt(4, paciente.getIdPaciente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return false;

    }

    // ELIMINAR
    public boolean eliminarPaciente(int idPaciente) {

        String sql = "DELETE FROM paciente WHERE idPaciente=?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return false;

    }

    // CONTAR
    public int contarPacientes() {

        int total = 0;

        String sql = "SELECT COUNT(*) FROM paciente";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {

                total = rs.getInt(1);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return total;

    }

    // LISTAR
    public List<Paciente> listarPacientes() {

        List<Paciente> lista = new ArrayList<>();

        String sql = "SELECT p.*, u.identificacion, CONCAT(u.nombres, ' ', u.apellidos) AS nombreCompleto "
                + "FROM paciente p INNER JOIN usuarios u ON u.idUsuarios = p.Usuarios_idUsuarios "
                + "ORDER BY u.nombres, u.apellidos";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Paciente p = new Paciente();

                p.setIdPaciente(rs.getInt("idPaciente"));
                p.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                p.setDiagnostico(rs.getString("diagnostico"));
                p.setUsuariosIdUsuarios(rs.getInt("Usuarios_idUsuarios"));
                p.setNombreCompleto(rs.getString("nombreCompleto"));
                p.setIdentificacion(rs.getString("identificacion"));

                lista.add(p);

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

}
