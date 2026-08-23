package Actualizar;

import java.util.Scanner;
import Modelo.Medicamento;
import Controlador.MedicamentoDAO;

public class PruebasActualizarMedicamento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Medicamento medicamento = new Medicamento();
        MedicamentoDAO dao = new MedicamentoDAO();

        System.out.println("=== ACTUALIZAR MEDICAMENTO ===");

        System.out.print("ID Medicamento: ");
        medicamento.setid_Medicamento(sc.nextInt());
        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        medicamento.setNombre(sc.nextLine());

        if (dao.actualizarMedicamento(medicamento)) {
            System.out.println("✅ Medicamento actualizado.");
        } else {
            System.out.println("❌ Error al actualizar.");
        }

        sc.close();
    }
}