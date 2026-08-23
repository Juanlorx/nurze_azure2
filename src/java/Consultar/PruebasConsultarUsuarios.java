package Consultar;

import java.util.Scanner;
import Modelo.Usuarios;
import Controlador.UsuariosDAO;

public class PruebasConsultarUsuarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuariosDAO dao = new UsuariosDAO();

        System.out.println("=== CONSULTAR USUARIO ===");

        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = sc.nextInt();

        Usuarios usuario = dao.consultarUsuario(idUsuario);

        if (usuario != null) {

            System.out.println("\n=== DATOS DEL USUARIO ===");
            System.out.println("ID: " + usuario.getid_Usuarios());
            System.out.println("Nombres: " + usuario.getNombres());
            System.out.println("Apellidos: " + usuario.getApellidos());
            System.out.println("Identificación: " + usuario.getIdentificacion());
            System.out.println("Teléfono: " + usuario.getTelefono());
            System.out.println("Dirección: " + usuario.getDireccion());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Clave: " + usuario.getClave());
            System.out.println("Rethus: " + usuario.getRethus());
            System.out.println("ID Rol: " + usuario.getRoles_idRoles());

        } else {

            System.out.println("❌ No se encontró un usuario con ese ID.");

        }

        sc.close();
    }
}