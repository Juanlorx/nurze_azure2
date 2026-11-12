
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    // Logger para registrar errores
    private static final Logger LOGGER =
            Logger.getLogger(Conexion.class.getName());

    // Controlador JDBC
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Configuración de conexión
    private static final String URL = obtenerUrl();
    private static final String USER = entorno("DB_USER", "root");
    private static final String PASSWORD = entorno("DB_PASSWORD", "");

    /*
     * Configuración para producción (Azure):
     *
     * DB_HOST
     * DB_PORT
     * DB_NAME
     * DB_USER
     * DB_PASSWORD
     * DB_SSL_MODE
     * DB_URL (opcional)
     *
     * Los valores predeterminados permiten trabajar
     * localmente con XAMPP.
     */

    // Obtener URL de conexión
    private static String obtenerUrl() {

        String urlCompleta = System.getenv("DB_URL");

        if (urlCompleta != null && !urlCompleta.trim().isEmpty()) {
            return urlCompleta.trim();
        }

        String host = entorno("DB_HOST", "localhost");
        String puerto = entorno("DB_PORT", "3306");
        String baseDatos = entorno("DB_NAME", "nurse");
        String sslMode = entorno("DB_SSL_MODE", "PREFERRED");

        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos
                + "?sslMode=" + sslMode
                + "&serverTimezone=UTC"
                + "&connectTimeout=10000"
                + "&socketTimeout=30000"
                + "&trustCertificateKeyStoreUrl=file:/opt/java/openjdk/lib/security/cacerts"
                + "&trustCertificateKeyStorePassword=changeit";
    }

    // Obtener variables de entorno
    private static String entorno(String nombre, String valorPorDefecto) {

        String valor = System.getenv(nombre);

        return (valor == null || valor.trim().isEmpty())
                ? valorPorDefecto
                : valor.trim();
    }

    // Establecer conexión con la base de datos
    public Connection getConexion() {

        Connection con = null;

        try {

            // Cargar controlador JDBC
            Class.forName(DRIVER);

            // Establecer conexión
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "No se encontró el controlador JDBC de MySQL.",
                    e
            );

        } catch (SQLException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "Error al establecer la conexión con la base de datos.",
                    e
            );
        }

        return con;
    }
}
