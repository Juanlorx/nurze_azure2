package Insertar;

import java.util.Scanner;
import Modelo.Notificacion;
import Controlador.NotificacionDAO;

public class PruebasInsertarNotificacion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Notificacion notificacion = new Notificacion();
        NotificacionDAO dao = new NotificacionDAO();

        System.out.print("ID Notificación: ");
        notificacion.setid_Notificacion(sc.nextInt());
        sc.nextLine();

        System.out.print("Información: ");
        notificacion.setInformacion(sc.nextLine());

        dao.insertarNotificacion(notificacion);

        sc.close();
    }
}