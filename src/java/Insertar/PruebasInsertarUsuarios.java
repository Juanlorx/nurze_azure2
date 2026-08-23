package Insertar;

import java.util.Scanner;
import Modelo.Usuarios;
import Controlador.UsuariosDAO;

public class PruebasInsertarUsuarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuarios usuario = new Usuarios();
        UsuariosDAO dao = new UsuariosDAO();

        System.out.println("=== INSERTAR USUARIO ===");
        
        System.out.print("ID Usuario: ");
        usuario.setid_Usuarios(sc.nextInt());
        sc.nextLine();

        System.out.print("Nombres: ");
        usuario.setNombres(sc.nextLine());

        System.out.print("Apellidos: ");
        usuario.setApellidos(sc.nextLine());

        System.out.print("Identificación: ");
        usuario.setIdentificacion(sc.nextLine());

        System.out.print("Teléfono: ");
        usuario.setTelefono(sc.nextLine());

        System.out.print("Dirección: ");
        usuario.setDireccion(sc.nextLine());

        System.out.print("Correo: ");
        usuario.setCorreo(sc.nextLine());

        System.out.print("Clave: ");
        usuario.setClave(sc.nextLine());

        System.out.print("Rethus: ");
        usuario.setRethus(sc.nextLine());
        
        System.out.print("ID Rol: ");
        usuario.setRoles_idRoles(sc.nextInt());

        boolean resultado = dao.insertarUsuario(usuario);

        if (resultado) {
            System.out.println("✅ Usuario insertado correctamente.");
        } else {
            System.out.println("❌ Error al insertar usuario.");
        }

        sc.close();
    }
} 