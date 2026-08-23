package Eliminar;

import Controlador.UsuariosDAO;
import java.util.Scanner;

public class PruebasEliminarUsuarios {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int idUsuario = leer.nextInt();

        UsuariosDAO dao = new UsuariosDAO();

        boolean eliminado = dao.eliminarUsuario(idUsuario);

        if (eliminado) {

            System.out.println(
                    "Usuario eliminado correctamente");

        } else {

            System.out.println(
                    "No existe el usuario o no se pudo eliminar");
        }

        leer.close();
    }
}