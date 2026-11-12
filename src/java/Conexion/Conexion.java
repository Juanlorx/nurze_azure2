
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static String obtenerVariable(String nombre) {

        String valor = System.getenv(nombre);

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException(
                "Falta configurar una variable necesaria: " + nombre
            );
        }

        return valor.trim();
    }

    public Connection getConexion() throws SQLException {

        String host = obtenerVariable("DB_HOST");
        String puerto = obtenerVariable("DB_PORT");
        String baseDatos = obtenerVariable("DB_NAME");

        String url = "jdbc:mysql://" + host + ":" + puerto + "/"
                + baseDatos;

        Properties propiedades = new Properties();

        propiedades.setProperty("user", obtenerVariable("DB_USER"));
        propiedades.setProperty("password", obtenerVariable("DB_PASSWORD"));

        // Seguridad de conexión
        propiedades.setProperty("sslMode", "VERIFY_IDENTITY");
        propiedades.setProperty("serverTimezone", "UTC");
        propiedades.setProperty("connectTimeout", "10000");
        propiedades.setProperty("socketTimeout", "30000");

        // Crear conexión
        return DriverManager.getConnection(url, propiedades);
    }
}
