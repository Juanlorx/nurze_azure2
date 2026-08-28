package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /*
     * Producción (Azure): configura DB_HOST, DB_PORT, DB_NAME, DB_USER,
     * DB_PASSWORD y DB_SSL_MODE como variables de entorno. DB_URL permite
     * reemplazar la URL completa si se requiere una configuración especial.
     * Los valores por defecto conservan el funcionamiento local con XAMPP.
     */
    private static final String URL = obtenerUrl();
    private static final String USER = entorno("DB_USER", "root");
    private static final String PASSWORD = entorno("DB_PASSWORD", "");

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
                + "&serverTimezone=UTC&connectTimeout=10000&socketTimeout=30000";
    }

    private static String entorno(String nombre, String valorPorDefecto) {
        String valor = System.getenv(nombre);
        return valor == null || valor.trim().isEmpty() ? valorPorDefecto : valor.trim();
    }

    public Connection getConexion() {

        Connection con = null;

        try {
            Class.forName(DRIVER);

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexión a la base de datos establecida.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
