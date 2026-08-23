package Controlador;

import Conexion.Conexion;
import Modelo.Notificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDAO {
public List<Notificacion> listarNotificaciones() {

    List<Notificacion> lista = new ArrayList<>();

    String sql = "SELECT * FROM notificacion";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Notificacion n = new Notificacion();

            n.setid_Notificacion(
                    rs.getInt("id_notificacion"));

            n.setInformacion(
                    rs.getString("informacion"));

            lista.add(n);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar notificaciones: "
                + e.getMessage());
    }

    return lista;
}
    // INSERTAR
    public boolean insertarNotificacion(Notificacion notificacion) {

        String sql = "INSERT INTO notificacion (informacion) VALUES (?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, notificacion.getInformacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar notificación: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public Notificacion consultarNotificacion(int id_notificacion) {

        String sql = "SELECT * FROM notificacion WHERE id_notificacion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_notificacion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Notificacion notificacion = new Notificacion();

                notificacion.setid_Notificacion(rs.getInt("id_notificacion"));
                notificacion.setInformacion(rs.getString("informacion"));

                return notificacion;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar notificación: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizarNotificacion(Notificacion notificacion) {

        String sql = "UPDATE notificacion SET informacion = ? WHERE id_notificacion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, notificacion.getInformacion());
            ps.setInt(2, notificacion.getid_Notificacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar notificación: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarNotificacion(int id_notificacion) {

        String sql = "DELETE FROM notificacion WHERE id_notificacion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_notificacion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar notificación: " + e.getMessage());
            return false;
        }
    }
    public int contarNotificaciones() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM notificacion";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar notificaciones: " + e.getMessage());
    }

    return total;
}
}
