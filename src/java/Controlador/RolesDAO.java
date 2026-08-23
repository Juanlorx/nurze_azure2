package Controlador;

import Conexion.Conexion;
import Modelo.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RolesDAO {
public List<Roles> listarRoles() {

    List<Roles> lista = new ArrayList<>();

    String sql = "SELECT * FROM roles";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Roles r = new Roles();

            r.setIdRoles(
                    rs.getInt("idRoles"));

            r.setDescripcionatencion(
                    rs.getString("descripcionatencion"));

            lista.add(r);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar roles: "
                + e.getMessage());
    }

    return lista;
}
    // INSERTAR
    public boolean insertarRol(Roles rol) {
 
        String sql = "INSERT INTO roles (descripcionatencion) VALUES (?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, rol.getDescripcionatencion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar rol: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public Roles consultarRol(int idRoles) {

        String sql = "SELECT * FROM roles WHERE idRoles = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idRoles);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Roles rol = new Roles();

                rol.setIdRoles(rs.getInt("idRoles"));
                rol.setDescripcionatencion(rs.getString("descripcionatencion"));

                return rol;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar rol: " + e.getMessage());
        }

        return null;
    }
    
    // ACTUALIZAR
    public boolean actualizarRol(Roles rol) {

        String sql = "UPDATE roles SET descripcionatencion = ? WHERE idRoles = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, rol.getDescripcionatencion());
            ps.setInt(2, rol.getIdRoles());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarRol(int idRoles) {

        String sql = "DELETE FROM roles WHERE idRoles = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idRoles);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
            return false;
        }
        
    }
    public int contarRoles() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM roles";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar roles: " + e.getMessage());
    }

    return total;
}
}
