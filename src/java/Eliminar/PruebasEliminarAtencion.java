package Eliminar;

import java.util.Scanner;
import Controlador.AtencionDAO;

public class PruebasEliminarAtencion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AtencionDAO dao = new AtencionDAO();

        System.out.print("Ingrese el ID de la atención a eliminar: ");
        int id = sc.nextInt();

        boolean resultado = dao.eliminarAtencion(id);

        if (resultado) {
            System.out.println("✅ Atención eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la atención.");
        }

        sc.close();
    }
}