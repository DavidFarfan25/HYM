package Controlador;

import DAO.ConexionBD;
import DAO.DashboardDAO;
import Modelo.DashboardResumen;

import java.sql.Connection;
import java.sql.SQLException;

public class DashboardControlador {
    private DashboardDAO dashboardDAO;

    public DashboardControlador() throws SQLException {
        Connection conexion = ConexionBD.obtenerConexion();
        this.dashboardDAO = new DashboardDAO(conexion);
    }

    public DashboardResumen obtenerResumen() {
        return dashboardDAO.obtenerResumenDashboard();
    }
}
