package Insertar;

import java.util.Scanner;
import Modelo.Atencion;
import Controlador.AtencionDAO;

public class PruebasInsertarAtencion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Atencion atencion = new Atencion();
        AtencionDAO dao = new AtencionDAO();

        System.out.println("=== INSERTAR NUEVA ATENCION ===");

        System.out.print("Ingrese el ID de la atención: ");
        atencion.setid_atencion(sc.nextInt());
        sc.nextLine();

        System.out.print("Ingrese la descripción: ");
        atencion.setDescripcion(sc.nextLine());

        boolean resultado = dao.insertarAtencion(atencion);

        if (resultado) {
            System.out.println("✅ Atención registrada correctamente.");
        } else {
            System.out.println("❌ Error al registrar atención.");
        }

        sc.close();
    }
}