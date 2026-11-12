
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConexion() {

        try {
            Class.forName(DRIVER);

            Properties propiedades = new Properties();

            propiedades.load(
                Conexion.class.getResourceAsStream("/database.properties")
            );

            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");

            if (url == null || usuario == null || password == null) {
                throw new IllegalStateException(
                    "Configuración de base de datos incompleta."
                );
            }

            return DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión con la base de datos.");
            return null;

        } catch (Exception e) {
            System.err.println("No se pudo cargar la configuración.");
            return null;
        }
    }
}
