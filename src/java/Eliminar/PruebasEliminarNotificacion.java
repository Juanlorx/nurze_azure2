package Eliminar;

import java.util.Scanner;
import Controlador.NotificacionDAO;

public class PruebasEliminarNotificacion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NotificacionDAO dao = new NotificacionDAO();

        System.out.print("ID Notificación: ");
        int id = sc.nextInt();

        dao.eliminarNotificacion(id);

        sc.close();
    }
}