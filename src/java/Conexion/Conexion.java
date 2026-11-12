
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConexion() {

        try {
            Class.forName(DRIVER);

            Properties propiedades = new Properties();

            try (InputStream archivo = Conexion.class.getResourceAsStream(
                    "/database.properties")) {

                if (archivo == null) {
                    throw new IOException(
                        "No se encontró el archivo de configuración."
                    );
                }

                propiedades.load(archivo);
            }

            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");

            if (url == null || url.isBlank()
                    || usuario == null || usuario.isBlank()
                    || password == null) {

                throw new IllegalStateException(
                    "Configuración de base de datos incompleta."
                );
            }

            return DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException e) {

            System.err.println("No se encontró el controlador MySQL.");

        } catch (SQLException e) {

            System.err.println("Error de conexión con la base de datos.");

        } catch (IOException e) {

            System.err.println("Error al cargar la configuración.");

        } catch (IllegalStateException e) {

            System.err.println(e.getMessage());
        }

        return null;
    }
}
