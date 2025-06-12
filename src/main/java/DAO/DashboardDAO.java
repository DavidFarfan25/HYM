package DAO;

import Modelo.DashboardResumen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;


public class DashboardDAO {

    private static final Logger logger = LoggerFactory.getLogger(DashboardDAO.class);
    private final Connection conexion;

    public DashboardDAO(Connection conexion) {
        this.conexion = conexion;
    }


    private void verificarConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            throw new SQLException("La conexión está cerrada o no disponible.");
        }
    }


    public DashboardResumen obtenerResumenDashboard() {
        logger.info("Inicio de obtención de resumen del dashboard");

        DashboardResumen resumen = new DashboardResumen();
        try {
            verificarConexion();
            resumen.setProductosEnStock(contarProductosPorStock("> 50"));
            resumen.setProductosBajoStock(contarProductosPorStock("BETWEEN 1 AND 49"));
            resumen.setProductosSinStock(contarProductosPorStock("= 0"));
            resumen.setProductosMasVendidos(obtenerProductosMasVendidos());
            resumen.setUltimosIngresos(obtenerUltimosIngresos());
            resumen.setEstadoSistema(obtenerEstadoSistema());

            logger.info("Resumen obtenido: {}", resumen);
        } catch (SQLException e) {
            logger.error("Error al obtener el resumen del dashboard", e);
        }

        return resumen;
    }


    public int contarProductosPorStock(String condicion) {
        String sql = "SELECT COUNT(*) FROM Producto WHERE stock " + condicion;
        try {
            verificarConexion();
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            logger.error("Error al contar productos por condición '{}': {}", condicion, e.getMessage());
            return 0;
        }
    }


    public List<String> obtenerProductosMasVendidos() {
        List<String> lista = new ArrayList<>();

        String sql =
            "SELECT p.nombre, SUM(m.cantidad) as total_ventas " +
            "FROM Movimiento m " +
            "JOIN Producto p ON m.producto_id = p.id " +
            "WHERE m.tipo = 'egreso' " +
            "GROUP BY p.nombre " +
            "ORDER BY total_ventas DESC " +
            "OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY";

        try {
            verificarConexion();
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int ventas = rs.getInt("total_ventas");
                    lista.add(String.format("%s<html><br> (%d ventas)</html>", nombre, ventas));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener productos más vendidos: {}", e.getMessage());
        }

        return lista;
    }


    public List<String> obtenerUltimosIngresos() {
        List<String> lista = new ArrayList<>();

        String sql =
            "SELECT TOP 3 p.nombre, m.cantidad " +
            "FROM Movimiento m " +
            "JOIN Producto p ON m.producto_id = p.id " +
            "WHERE m.tipo = 'ingreso' " +
            "ORDER BY m.fecha DESC";

        try {
            verificarConexion();
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    lista.add(String.format("%s<html><br> (%d unidades)</html>", nombre, cantidad));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener últimos ingresos: {}", e.getMessage());
        }

        return lista;
    }

    public String obtenerEstadoSistema() {
    String sql = "SELECT nombre_estado FROM EstadoSistema WHERE id = 1";

    try {
        verificarConexion();

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String estado = rs.getString("nombre_estado").toUpperCase();

                switch (estado) {
                    case "CRITICAL":
                        return "Sistema inestable - Revisar urgentemente";
                    case "ERROR":
                        return "Error en sincronización de datos";
                    case "WARN":
                        return "Sistema funcionando con advertencias menores";
                    case "OK":
                        return "Todos los módulos funcionando correctamente";
                    default:
                        logger.warn("Estado desconocido detectado: {}", estado);
                        return "Estado del sistema no reconocido: " + estado;
                }
            } else {
                logger.warn("No se encontró ninguna fila en EstadoSistema con id = 1.");
                return "No se pudo determinar el estado del sistema";
            }

        }

    } catch (SQLException e) {
        logger.error("Error al evaluar el estado del sistema", e);
        return "No se pudo determinar el estado del sistema";
    }
}

}
