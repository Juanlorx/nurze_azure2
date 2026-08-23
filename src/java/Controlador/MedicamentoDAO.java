package Controlador;

import Conexion.Conexion;
import Modelo.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    public List<Medicamento> listarMedicamentos() {

    List<Medicamento> lista = new ArrayList<>();

    String sql = "SELECT * FROM medicamento";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Medicamento m = new Medicamento();

            m.setid_Medicamento(
                    rs.getInt("idMedicamento"));

            cargarDatos(m, rs);

            lista.add(m);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al listar medicamentos: "
                + e.getMessage());
    }

    return lista;
}
    // INSERTAR
    public boolean insertarMedicamento(Medicamento medicamento) {

        String sql = "INSERT INTO medicamento (Nombre, funcion, presentacion, lote, fecha_vencimiento, existencias) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            asignarDatos(ps, medicamento, false);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar medicamento: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public Medicamento consultarMedicamento(int id_medicamento) {

        String sql = "SELECT * FROM medicamento WHERE idMedicamento = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_medicamento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Medicamento medicamento = new Medicamento();

                medicamento.setid_Medicamento(rs.getInt("idMedicamento"));
                cargarDatos(medicamento, rs);

                return medicamento;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar medicamento: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizarMedicamento(Medicamento medicamento) {

        String sql = "UPDATE medicamento SET Nombre = ?, funcion = ?, presentacion = ?, lote = ?, fecha_vencimiento = ?, existencias = ? WHERE idMedicamento = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            asignarDatos(ps, medicamento, true);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarMedicamento(int id_medicamento) {

        String sql = "DELETE FROM medicamento WHERE idMedicamento = ?";

        try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id_medicamento);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar medicamento: " + e.getMessage());
            return false;
        }
    }
    public int contarMedicamentos() {

    int total = 0;

    String sql = "SELECT COUNT(*) FROM medicamento";

    try (
            Connection con = new Conexion().getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.out.println("Error al contar medicamentos: " + e.getMessage());
    }

    return total;
}

    private void cargarDatos(Medicamento medicamento, ResultSet rs) throws SQLException {
        medicamento.setNombre(rs.getString("Nombre"));
        medicamento.setFuncion(rs.getString("funcion"));
        medicamento.setPresentacion(rs.getString("presentacion"));
        medicamento.setLote(rs.getString("lote"));
        medicamento.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
        medicamento.setExistencias(rs.getInt("existencias"));
    }

    private void asignarDatos(PreparedStatement ps, Medicamento medicamento, boolean incluirId) throws SQLException {
        ps.setString(1, medicamento.getNombre());
        ps.setString(2, medicamento.getFuncion());
        ps.setString(3, medicamento.getPresentacion());
        ps.setString(4, medicamento.getLote());
        ps.setDate(5, medicamento.getFechaVencimiento());
        ps.setInt(6, medicamento.getExistencias());
        if (incluirId) {
            ps.setInt(7, medicamento.getid_Medicamento());
        }
    }
}
