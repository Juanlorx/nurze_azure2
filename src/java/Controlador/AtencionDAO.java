package Controlador;

import Conexion.Conexion;
import Modelo.Atencion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtencionDAO {
public List<Atencion> listarAtenciones() {

    List<Atencion> lista = new ArrayList<>();

    String sql = "SELECT * FROM atencion";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Atencion a = new Atencion();

            a.setid_atencion(
                    rs.getInt("id_atencion"));

            a.setDescripcion(
                    rs.getString("descripcion"));

            lista.add(a);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar atenciones: "
                + e.getMessage());
    }

    return lista;
}
    // INSERTAR
    public boolean insertarAtencion(Atencion atencion) {

        String sql = "INSERT INTO atencion (descripcion) VALUES (?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, atencion.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar atención: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public Atencion consultarAtencion(int id_atencion) {

        String sql = "SELECT * FROM atencion WHERE id_atencion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_atencion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Atencion atencion = new Atencion();

                atencion.setid_atencion(rs.getInt("id_atencion"));
                atencion.setDescripcion(rs.getString("descripcion"));

                return atencion;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar atención: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizarAtencion(Atencion atencion) {

        String sql = "UPDATE atencion SET descripcion = ? WHERE id_atencion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, atencion.getDescripcion());
            ps.setInt(2, atencion.getid_atencion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar atención: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarAtencion(int id_atencion) {

        String sql = "DELETE FROM atencion WHERE id_atencion = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_atencion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar atención: " + e.getMessage());
            return false;
        }
    }
    public int contarAtenciones() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM atencion";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar atenciones: " + e.getMessage());
    }

    return total;
}
}
