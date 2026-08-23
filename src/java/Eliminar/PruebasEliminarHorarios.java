package Eliminar;

import java.util.Scanner;
import Controlador.HorariosDAO;

public class PruebasEliminarHorarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HorariosDAO dao = new HorariosDAO();

        System.out.print("ID Horario a eliminar: ");
        int id = sc.nextInt();

        if (dao.eliminarHorario(id)) {
            System.out.println("✅ Horario eliminado.");
        } else {
            System.out.println("❌ Error al eliminar.");
        }

        sc.close();
    }
}