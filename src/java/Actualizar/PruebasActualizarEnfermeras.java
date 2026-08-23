package Actualizar;

import java.util.Scanner;
import Modelo.Enfermeras;
import Controlador.EnfermerasDAO;

public class PruebasActualizarEnfermeras {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EnfermerasDAO dao = new EnfermerasDAO();
        Enfermeras enfermera = new Enfermeras();

        System.out.println("=== ACTUALIZAR ENFERMERA ===");

        System.out.print("Ingrese el ID de la enfermera: ");
        enfermera.setid_Enfermeras(sc.nextInt());
        sc.nextLine();

        System.out.print("Ingrese el nuevo RETHUS: ");
        enfermera.setRethus_Enfermeras(sc.nextLine());

        boolean resultado = dao.actualizarEnfermeras(enfermera);

        if (resultado) {
            System.out.println("✅ Enfermera actualizada correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar la enfermera.");
        }

        sc.close();
    }
}