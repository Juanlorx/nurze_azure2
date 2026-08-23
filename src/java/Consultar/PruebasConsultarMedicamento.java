package Consultar;

import java.util.Scanner;
import Modelo.Medicamento;
import Controlador.MedicamentoDAO;

public class PruebasConsultarMedicamento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MedicamentoDAO dao = new MedicamentoDAO();

        System.out.print("Ingrese ID del medicamento: ");
        int id = sc.nextInt();

        Medicamento medicamento = dao.consultarMedicamento(id);

        if (medicamento != null) {

            System.out.println("\n=== DATOS DEL MEDICAMENTO ===");
            System.out.println("ID: " + medicamento.getid_Medicamento());
            System.out.println("Nombre: " + medicamento.getNombre());

        } else {
            System.out.println("❌ Medicamento no encontrado.");
        }

        sc.close();
    }
}