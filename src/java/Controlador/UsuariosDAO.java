package Controlador;

import Conexion.Conexion;
import Modelo.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    // INSERTAR
    public boolean insertarUsuario(Usuarios usuario) {

        String sql = "INSERT INTO usuarios "
        + "(nombres,apellidos,identificacion,telefono,direccion,correo,clave,Rethus,Roles_idRoles) "
        + "VALUES (?,?,?,?,?,?,?,?,?)";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            con.setAutoCommit(false);
            ps.setString(1, usuario.getNombres());
ps.setString(2, usuario.getApellidos());
ps.setString(3, usuario.getIdentificacion());
ps.setString(4, usuario.getTelefono());
ps.setString(5, usuario.getDireccion());
ps.setString(6, usuario.getCorreo());
ps.setString(7, usuario.getClave());
ps.setString(8, usuario.getRethus());
ps.setInt(9, usuario.getRoles_idRoles());

            if (ps.executeUpdate() == 0) {
                con.rollback();
                return false;
            }

            if (esRolEnfermera(con, usuario.getRoles_idRoles())) {
                try (ResultSet claves = ps.getGeneratedKeys()) {
                    if (!claves.next()) {
                        con.rollback();
                        return false;
                    }
                    crearFichaEnfermera(con, claves.getInt(1), usuario.getRethus());
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    private boolean esRolEnfermera(Connection con, int idRol) throws SQLException {
        String sql = "SELECT 1 FROM roles WHERE idRoles = ? AND LOWER(descripcionatencion) LIKE '%enfermer%'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        }
    }

    private void crearFichaEnfermera(Connection con, int idUsuario, String rethus) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO enfermeras (rethus, Usuarios_idUsuarios) VALUES (?, ?)")) {
            ps.setString(1, rethus == null || rethus.isEmpty() ? "Pendiente de completar" : rethus);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }

    // CONSULTAR POR CORREO
    public Usuarios consultarUsuarioCorreo(String correo) {

        String sql = "SELECT u.*, r.descripcionatencion AS rolDescripcion "
                + "FROM usuarios u "
                + "LEFT JOIN roles r ON u.Roles_idRoles = r.idRoles "
                + "WHERE u.correo = ?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuarios usuario = new Usuarios();

                usuario.setid_Usuarios(rs.getInt("idUsuarios"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
                usuario.setRethus(rs.getString("Rethus"));
                usuario.setRoles_idRoles(rs.getInt("Roles_idRoles"));
                usuario.setRolDescripcion(rs.getString("rolDescripcion"));

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario por correo: " + e.getMessage());
        }

        return null;
    }

    // CONSULTAR POR ID
    public Usuarios consultarUsuario(int idUsuarios) {

        String sql = "SELECT u.*, r.descripcionatencion AS rolDescripcion "
                + "FROM usuarios u "
                + "LEFT JOIN roles r ON u.Roles_idRoles = r.idRoles "
                + "WHERE u.idUsuarios = ?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idUsuarios);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuarios usuario = new Usuarios();

                usuario.setid_Usuarios(rs.getInt("idUsuarios"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
                usuario.setRethus(rs.getString("Rethus"));
                usuario.setRoles_idRoles(rs.getInt("Roles_idRoles"));
                usuario.setRolDescripcion(rs.getString("rolDescripcion"));

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        }

        return null;
    }

    // VALIDAR LOGIN
    public boolean validarIndex(String correo, String clave) {

        String sql = "SELECT * FROM usuarios WHERE correo=? AND clave=?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, correo);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println("Error login: " + e.getMessage());

            return false;
        }
    }

    // ACTUALIZAR
    public boolean actualizarUsuario(Usuarios usuario) {

        String sql = "UPDATE usuarios SET "
                + "nombres=?, "
                + "apellidos=?, "
                + "identificacion=?, "
                + "telefono=?, "
                + "direccion=?, "
                + "correo=?, "
                + "clave=?, "
                + "Rethus=?, "
                + "Roles_idRoles=? "
                + "WHERE idUsuarios=?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getIdentificacion());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getDireccion());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getClave());
            ps.setString(8, usuario.getRethus());
            ps.setInt(9, usuario.getRoles_idRoles());
            ps.setInt(10, usuario.getid_Usuarios());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarUsuario(int idUsuarios) {

        String sql = "DELETE FROM usuarios WHERE idUsuarios = ?";

        try (
                Connection con = new Conexion().getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idUsuarios);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
        
    }
    // VERIFICAR SI EL CORREO YA EXISTE
public boolean existeCorreo(String correo) {

    String sql = "SELECT correo FROM usuarios WHERE correo = ?";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, correo);

        ResultSet rs = ps.executeQuery();

        return rs.next();

    } catch (SQLException e) {

        System.out.println("Error verificando correo: "
                + e.getMessage());

        return false;
    }
}
    // LISTAR TODOS LOS USUARIOS
public List<Usuarios> listarUsuarios() {

    List<Usuarios> lista = new ArrayList<>();

    String sql = "SELECT u.*, r.descripcionatencion AS rolDescripcion "
            + "FROM usuarios u "
            + "LEFT JOIN roles r ON u.Roles_idRoles = r.idRoles";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Usuarios usuario = new Usuarios();

            usuario.setid_Usuarios(rs.getInt("idUsuarios"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setApellidos(rs.getString("apellidos"));
            usuario.setIdentificacion(rs.getString("identificacion"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setDireccion(rs.getString("direccion"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setClave(rs.getString("clave"));
            usuario.setRethus(rs.getString("Rethus"));
            usuario.setRoles_idRoles(rs.getInt("Roles_idRoles"));
            usuario.setRolDescripcion(rs.getString("rolDescripcion"));

            lista.add(usuario);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar usuarios: "
                + e.getMessage());
    }

    return lista;

}// CONTAR USUARIOS
public int contarUsuarios() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM usuarios";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al contar usuarios: "
                + e.getMessage());
    }

    return total;
}

    public List<Usuarios> listar() {
        return listarUsuarios();
    }

public List<Usuarios> listarUsuariosPorRol(String filtroRol) {

    List<Usuarios> lista = new ArrayList<>();

    String sql = "SELECT u.*, r.descripcionatencion AS rolDescripcion "
            + "FROM usuarios u "
            + "INNER JOIN roles r ON u.Roles_idRoles = r.idRoles "
            + "WHERE LOWER(r.descripcionatencion) LIKE ? "
            + "ORDER BY u.nombres, u.apellidos";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, "%" + filtroRol.toLowerCase() + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setid_Usuarios(rs.getInt("idUsuarios"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
                usuario.setRethus(rs.getString("Rethus"));
                usuario.setRoles_idRoles(rs.getInt("Roles_idRoles"));
                usuario.setRolDescripcion(rs.getString("rolDescripcion"));
                lista.add(usuario);
            }
        }

    } catch (SQLException e) {

        System.out.println("Error al listar usuarios por rol: " + e.getMessage());
    }

    return lista;
}

public int contarUsuariosPorRol(String filtroRol) {

    int total = 0;

    String sql = "SELECT COUNT(*) "
            + "FROM usuarios u "
            + "INNER JOIN roles r ON u.Roles_idRoles = r.idRoles "
            + "WHERE LOWER(r.descripcionatencion) LIKE ?";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, "%" + filtroRol.toLowerCase() + "%");

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        }

    } catch (SQLException e) {

        System.out.println("Error al contar usuarios por rol: " + e.getMessage());
    }

    return total;
}

public int obtenerIdRolPorDescripcion(String filtroRol) {

    String sql = "SELECT idRoles FROM roles WHERE LOWER(descripcionatencion) LIKE ? LIMIT 1";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, "%" + filtroRol.toLowerCase() + "%");

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idRoles");
            }
        }

    } catch (SQLException e) {

        System.out.println("Error al consultar rol por descripcion: " + e.getMessage());
    }

    return 0;
}
    
}
