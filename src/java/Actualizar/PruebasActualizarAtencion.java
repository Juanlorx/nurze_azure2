package Actualizar;

import java.util.Scanner;
import Modelo.Atencion;
import Controlador.AtencionDAO;

public class PruebasActualizarAtencion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AtencionDAO dao = new AtencionDAO();
        Atencion atencion = new Atencion();

        System.out.println("=== ACTUALIZAR ATENCION ===");

        System.out.print("Ingrese el ID de la atención: ");
        atencion.setid_atencion(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva descripción: ");
        atencion.setDescripcion(sc.nextLine());

        boolean resultado = dao.actualizarAtencion(atencion);

        if (resultado) {
            System.out.println("✅ Atención actualizada correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar la atención.");
        }

        sc.close();
    }
}