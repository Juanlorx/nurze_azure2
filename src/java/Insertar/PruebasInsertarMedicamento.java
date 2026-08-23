package Insertar;

import java.util.Scanner;
import Modelo.Medicamento;
import Controlador.MedicamentoDAO;

public class PruebasInsertarMedicamento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Medicamento medicamento = new Medicamento();
        MedicamentoDAO dao = new MedicamentoDAO();

        System.out.println("=== INSERTAR MEDICAMENTO ===");

        System.out.print("ID Medicamento: ");
        medicamento.setid_Medicamento(sc.nextInt());
        sc.nextLine();

        System.out.print("Nombre: ");
        medicamento.setNombre(sc.nextLine());

        if (dao.insertarMedicamento(medicamento)) {
            System.out.println("✅ Medicamento registrado.");
        } else {
            System.out.println("❌ Error al registrar medicamento.");
        }

        sc.close();
    }
}