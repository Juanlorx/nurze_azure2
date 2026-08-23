package Actualizar;

import java.util.Scanner;
import Modelo.Roles;
import Controlador.RolesDAO;

public class PruebasActualizarRoles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Roles rol = new Roles();
        RolesDAO dao = new RolesDAO();

        System.out.print("ID Rol: ");
        rol.setIdRoles(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva descripción: ");
        rol.setDescripcionatencion(sc.nextLine());

        dao.actualizarRol(rol);

        sc.close();
    }
}