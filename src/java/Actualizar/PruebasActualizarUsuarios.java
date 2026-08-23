package Actualizar;

import java.util.Scanner;
import Modelo.Usuarios;
import Controlador.UsuariosDAO;

public class PruebasActualizarUsuarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Usuarios usuario = new Usuarios();
        UsuariosDAO dao = new UsuariosDAO();
        usuario.setRoles_idRoles(
        rs.getInt("Roles_idRoles"));
        System.out.println("=== ACTUALIZAR USUARIO ===");

        System.out.print("ID Usuario a actualizar: ");
        usuario.setid_Usuarios(sc.nextInt());
        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        usuario.setNombres(sc.nextLine());

        System.out.print("Nuevo apellido: ");
        usuario.setApellidos(sc.nextLine());

        System.out.print("Nueva identificación: ");
        usuario.setIdentificacion(sc.nextLine());

        System.out.print("Nuevo teléfono: ");
        usuario.setTelefono(sc.nextLine());

        System.out.print("Nueva dirección: ");
        usuario.setDireccion(sc.nextLine());

        System.out.print("Nuevo correo: ");
        usuario.setCorreo(sc.nextLine());

        System.out.print("Nueva clave: ");
        usuario.setClave(sc.nextLine());

        System.out.print("Nuevo Rethus: ");
        usuario.setRethus(sc.nextLine());

        boolean resultado = dao.actualizarUsuario(usuario);

        if (resultado) {
            System.out.println("✅ Usuario actualizado correctamente.");
        } else {
            System.out.println("❌ Error al actualizar usuario.");
        }

        sc.close();
    }

    private static class rs {

        private static int getInt(String roles_idRoles) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public rs() {
        }
    }
}