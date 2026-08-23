package Insertar;

import java.util.Scanner;
import Modelo.Tratamiento;
import Controlador.TratamientoDAO;

public class PruebasInsertarTratamiento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Tratamiento t = new Tratamiento();
        TratamientoDAO dao = new TratamientoDAO();

        System.out.print("ID Tratamiento: ");
        t.setid_Tratamiento(sc.nextInt());
        sc.nextLine();

        System.out.print("Descripción: ");
        t.setDescripcion(sc.nextLine());

        dao.insertarTratamiento(t);

        sc.close();
    }
}