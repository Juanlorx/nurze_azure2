package Actualizar;

import java.util.Scanner;
import Modelo.Notificacion;
import Controlador.NotificacionDAO;

public class PruebasActualizarNotificacion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Notificacion n = new Notificacion();
        NotificacionDAO dao = new NotificacionDAO();

        System.out.print("ID Notificación: ");
        n.setid_Notificacion(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva información: ");
        n.setInformacion(sc.nextLine());

        dao.actualizarNotificacion(n);

        sc.close();
    }
}