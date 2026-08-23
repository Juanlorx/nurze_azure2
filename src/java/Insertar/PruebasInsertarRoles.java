package Insertar;

import java.util.Scanner;
import Modelo.Roles;
import Controlador.RolesDAO;
public class PruebasInsertarRoles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Roles rol = new Roles();
        RolesDAO dao = new RolesDAO();

        System.out.print("ID Rol: ");
        rol.setIdRoles(sc.nextInt());
        sc.nextLine();

        System.out.print("Descripciónatencion: ");
        rol.setDescripcionatencion(sc.nextLine());

        dao.insertarRol(rol);

        sc.close();
    }
}