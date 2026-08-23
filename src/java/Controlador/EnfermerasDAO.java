package Controlador;

import Conexion.Conexion;
import Modelo.Enfermeras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnfermerasDAO {
    public List<Enfermeras> listarEnfermeras() {

    List<Enfermeras> lista = new ArrayList<>();

    String sql = "SELECT e.*, CONCAT(u.nombres, ' ', u.apellidos) AS nombreCompleto "
            + "FROM enfermeras e INNER JOIN usuarios u ON u.idUsuarios = e.Usuarios_idUsuarios "
            + "ORDER BY u.nombres, u.apellidos";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Enfermeras e = new Enfermeras();

            e.setid_Enfermeras(
                    rs.getInt("idenfermeras"));

            e.setRethus_Enfermeras(
                    rs.getString("rethus"));
            e.setNombreCompleto(rs.getString("nombreCompleto"));

            lista.add(e);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar enfermeras: "
                + e.getMessage());
    }

    return lista;
}
    // INSERTAR
    public boolean insertarEnfermeras(Enfermeras enfermera) {

        String sql = "INSERT INTO enfermeras (rethus_enfermeras) VALUES (?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, enfermera.getRethus_Enfermeras());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar enfermera: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public Enfermeras consultarEnfermeras(int id_enfermeras) {

        String sql = "SELECT * FROM enfermeras WHERE id_enfermeras = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_enfermeras);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Enfermeras enfermera = new Enfermeras();

                enfermera.setid_Enfermeras(rs.getInt("id_enfermeras"));
                enfermera.setRethus_Enfermeras(rs.getString("rethus_enfermeras"));

                return enfermera;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar enfermera: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizarEnfermeras(Enfermeras enfermera) {

        String sql = "UPDATE enfermeras SET rethus_enfermeras = ? WHERE id_enfermeras = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, enfermera.getRethus_Enfermeras());
            ps.setInt(2, enfermera.getid_Enfermeras());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar enfermera: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarEnfermeras(int id_enfermeras) {

        String sql = "DELETE FROM enfermeras WHERE id_enfermeras = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_enfermeras);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar enfermera: " + e.getMessage());
            return false;
        }
    }
    public int contarEnfermeras() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM enfermeras";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar enfermeras: " + e.getMessage());
    }

    return total;
}
}
