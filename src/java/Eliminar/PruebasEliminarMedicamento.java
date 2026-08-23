package Eliminar;

import java.util.Scanner;
import Controlador.MedicamentoDAO;

public class PruebasEliminarMedicamento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MedicamentoDAO dao = new MedicamentoDAO();

        System.out.print("ID Medicamento a eliminar: ");
        int id = sc.nextInt();

        if (dao.eliminarMedicamento(id)) {
            System.out.println("✅ Medicamento eliminado.");
        } else {
            System.out.println("❌ Error al eliminar.");
        }

        sc.close();
    }
}