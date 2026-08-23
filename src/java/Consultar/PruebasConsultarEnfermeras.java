package Consultar;

import java.util.Scanner;
import Modelo.Enfermeras;
import Controlador.EnfermerasDAO;

public class PruebasConsultarEnfermeras {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EnfermerasDAO dao = new EnfermerasDAO();

        System.out.print("Ingrese el ID de la enfermera a consultar: ");
        int id = sc.nextInt();

        Enfermeras enfermera = dao.consultarEnfermeras(id);

        if (enfermera != null) {

            System.out.println("\n=== DATOS DE LA ENFERMERA ===");
            System.out.println("ID: " + enfermera.getid_Enfermeras());
            System.out.println("RETHUS: " + enfermera.getRethus_Enfermeras());

        } else {
            System.out.println("❌ Enfermera no encontrada.");
        }

        sc.close();
    }
}