package Insertar;

import java.util.Scanner;
import Modelo.Enfermeras;
import Controlador.EnfermerasDAO;

public class PruebasInsertarEnfermeras {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Enfermeras enfermera = new Enfermeras();
        EnfermerasDAO dao = new EnfermerasDAO();

        System.out.println("=== INSERTAR NUEVA ENFERMERA ===");

        System.out.print("Ingrese el ID de la enfermera: ");
        enfermera.setid_Enfermeras(sc.nextInt());
        sc.nextLine();

        System.out.print("Ingrese el RETHUS de la enfermera: ");
        enfermera.setRethus_Enfermeras(sc.nextLine());

        boolean resultado = dao.insertarEnfermeras(enfermera);

        if (resultado) {
            System.out.println("✅ Enfermera registrada correctamente.");
        } else {
            System.out.println("❌ Error al registrar enfermera.");
        }

        sc.close();
    }
}