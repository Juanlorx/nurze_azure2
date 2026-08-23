package Eliminar;

import java.util.Scanner;
import Controlador.TratamientoDAO;

public class PruebasEliminarTratamiento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TratamientoDAO dao = new TratamientoDAO();

        System.out.print("ID Tratamiento: ");
        int id = sc.nextInt();

        dao.eliminarTratamiento(id);

        sc.close();
    }
}