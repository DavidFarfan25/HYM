package Controlador;

import DAO.ConexionBD;
import DAO.DashboardDAO;
import Modelo.DashboardResumen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Controlador para manejar la lógica del panel de Dashboard.
 */
public class DashboardControlador {

    private static final Logger logger = LoggerFactory.getLogger(DashboardControlador.class);

    /**
     * Obtiene el resumen actual del sistema para mostrar en el Dashboard.
     */
    public DashboardResumen obtenerResumen() {
        try (Connection conexion = ConexionBD.obtenerConexion()) {
            DashboardDAO dashboardDAO = new DashboardDAO(conexion);
            return dashboardDAO.obtenerResumenDashboard();
        } catch (SQLException e) {
            logger.error("Error al obtener resumen del dashboard", e);
            return null;
        }
    }
}
