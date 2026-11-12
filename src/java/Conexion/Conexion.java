
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static String obtenerVariable(String nombre) {
        String valor = System.getenv(nombre);

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException(
                "Falta configurar la variable de entorno: " + nombre
            );
        }

        return valor.trim();
    }

    private static String obtenerUrl() {
        String url = System.getenv("DB_URL");

        if (url != null && !url.trim().isEmpty()) {
            return url.trim();
        }

        String host = obtenerVariable("DB_HOST");
        String puerto = obtenerVariable("DB_PORT");
        String baseDatos = obtenerVariable("DB_NAME");

        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos
                + "?sslMode=REQUIRED"
                + "&serverTimezone=UTC"
                + "&connectTimeout=10000"
                + "&socketTimeout=30000";
    }

    public Connection getConexion() {

        try {
            Class.forName(DRIVER);

            String url = obtenerUrl();
            String usuario = obtenerVariable("DB_USER");
            String password = obtenerVariable("DB_PASSWORD");

            Connection con = DriverManager.getConnection(
                url, usuario, password
            );

            System.out.println("Conexión establecida correctamente.");

            return con;

        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver de MySQL.");

        } catch (SQLException e) {
            System.err.println("Error al conectar con MySQL.");

        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
