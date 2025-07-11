package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConexionBD {
    private static final Logger logger = LoggerFactory.getLogger(ConexionBD.class);
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=InventarioHM;encrypt=false;trustServerCertificate=true";
    private static final String USUARIO = "sa";
    private static final String CONTRASENA = "1234567";
    private static Connection conexion;

    private ConexionBD() {
        // Constructor privado para prevenir instanciación
    }

    /**
     * Obtiene una conexión activa a la base de datos.
     * @return conexión activa
     * @throws SQLException si hay error al conectar
     */
    public static Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                logger.info("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                logger.error("No se encontró el driver de SQL Server", e);
            } catch (SQLException e) {
                logger.error("Error al conectar a la base de datos: {}", e.getMessage(), e);
                throw e;
            }
        }
        return conexion;
    }

    /**
     * Cierra la conexión activa si está abierta.
     */
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                logger.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: {}", e.getMessage(), e);
            }
        }
    }
}
