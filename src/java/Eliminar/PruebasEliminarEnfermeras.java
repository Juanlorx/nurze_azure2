package Eliminar;

import java.util.Scanner;
import Controlador.EnfermerasDAO;

public class PruebasEliminarEnfermeras {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EnfermerasDAO dao = new EnfermerasDAO();

        System.out.print("Ingrese el ID de la enfermera a eliminar: ");
        int id = sc.nextInt();

        boolean resultado = dao.eliminarEnfermeras(id);

        if (resultado) {
            System.out.println("✅ Enfermera eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la enfermera.");
        }

        sc.close();
    }
}