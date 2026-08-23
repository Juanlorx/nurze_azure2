package Consultar;

import java.util.Scanner;
import Modelo.Tratamiento;
import Controlador.TratamientoDAO;

public class PruebasConsultarTratamiento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TratamientoDAO dao = new TratamientoDAO();

        System.out.print("ID Tratamiento: ");
        int id = sc.nextInt();

        Tratamiento t = dao.consultarTratamiento(id);

        if(t != null){

            System.out.println("ID: " + t.getid_Tratamiento());
            System.out.println("Descripción: " + t.getDescripcion());

        }

        sc.close();
    }
}