package Controlador;

import Conexion.Conexion;
import Modelo.Horarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HorariosDAO {
public List<Horarios> listarHorarios() {

    List<Horarios> lista = new ArrayList<>();

    String sql = "SELECT * FROM horarios";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Horarios h = new Horarios();

            h.setid_Horarios(
                    rs.getInt("id_horarios"));

            h.setFecha(
                    rs.getDate("fecha"));

            h.setHora_inicial(
                    rs.getTime("hora_inicial"));

            h.setHora_final(
                    rs.getTime("hora_final"));

            lista.add(h);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar horarios: "
                + e.getMessage());
    }

    return lista;
}
    public boolean insertarHorario(Horarios horario) {

        String sql = "INSERT INTO horarios(fecha, hora_inicial, hora_final) VALUES(?,?,?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, horario.getFecha());
            ps.setTime(2, horario.getHora_inicial());
            ps.setTime(3, horario.getHora_final());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar horario: " + e.getMessage());
            return false;
        }
    }

    public Horarios consultarHorario(int id_horarios) {

        String sql = "SELECT * FROM horarios WHERE id_horarios=?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_horarios);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Horarios horario = new Horarios();

                horario.setid_Horarios(rs.getInt("id_horarios"));
                horario.setFecha(rs.getDate("fecha"));
                horario.setHora_inicial(rs.getTime("hora_inicial"));
                horario.setHora_final(rs.getTime("hora_final"));

                return horario;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar horario: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarHorario(Horarios horario) {

        String sql = "UPDATE horarios SET fecha=?, hora_inicial=?, hora_final=? WHERE id_horarios=?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, horario.getFecha());
            ps.setTime(2, horario.getHora_inicial());
            ps.setTime(3, horario.getHora_final());
            ps.setInt(4, horario.getid_Horarios());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar horario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarHorario(int id_horarios) {

        String sql = "DELETE FROM horarios WHERE id_horarios=?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_horarios);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar horario: " + e.getMessage());
            return false;
        }
    }
    public int contarHorarios() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM horarios";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar horarios: " + e.getMessage());
    }

    return total;
}
}
