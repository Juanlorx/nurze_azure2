package Consultar;

import java.util.Scanner;
import Modelo.Atencion;
import Controlador.AtencionDAO;

public class PruebasConsultarAtencion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AtencionDAO dao = new AtencionDAO();

        System.out.print("Ingrese el ID de la atención a consultar: ");
        int id = sc.nextInt();

        Atencion atencion = dao.consultarAtencion(id);

        if (atencion != null) {

            System.out.println("\n=== DATOS DE LA ATENCION ===");
            System.out.println("ID: " + atencion.getid_atencion());
            System.out.println("Descripción: " + atencion.getDescripcion());

        } else {
            System.out.println("❌ Atención no encontrada.");
        }

        sc.close();
    }
}