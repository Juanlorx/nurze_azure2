package Eliminar;

import java.util.Scanner;
import Controlador.RolesDAO;

public class PruebasEliminarRoles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        RolesDAO dao = new RolesDAO();

        System.out.print("ID Rol: ");
        int id = sc.nextInt();

        dao.eliminarRol(id);

        sc.close();
    }
}