package Consultar;

import java.util.Scanner;
import Modelo.Roles;
import Controlador.RolesDAO;

public class PruebasConsultarRoles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        RolesDAO dao = new RolesDAO();

        System.out.print("ID Rol: ");
        int id = sc.nextInt();

        Roles rol = dao.consultarRol(id);

        if(rol != null){

            System.out.println("ID: " + rol.getIdRoles());
            System.out.println("Descripción: " + rol.getDescripcionatencion());

        }

        sc.close();
    }
}