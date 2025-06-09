package DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Modelo.DashboardResumen;
import java.sql.*;
import java.util.*;

public class DashboardDAO {
    private static final Logger logger = LoggerFactory.getLogger(DashboardDAO.class);
    private final Connection conexion;

    public DashboardDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public DashboardResumen obtenerResumenDashboard() {
        logger.info("Inicio de obtención de resumen del dashboard");
        DashboardResumen resumen = new DashboardResumen();
        
        resumen.setProductosEnStock(contarProductosPorStock("> 10"));
        resumen.setProductosBajoStock(contarProductosPorStock("BETWEEN 1 AND 10"));
        resumen.setProductosSinStock(contarProductosPorStock("= 0"));
        resumen.setProductosMasVendidos(obtenerProductosMasVendidos());
        resumen.setUltimosIngresos(obtenerUltimosIngresos());
        resumen.setEstadoSistema("Todos los módulos trabajando correctamente");
        
        logger.info("Resumen obtenido: {}", resumen);
        return resumen;
    }

    public int contarProductosPorStock(String condicion) {
        int cantidad = 0;
        String sql = "SELECT COUNT(*) FROM Producto WHERE stock " + condicion;

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidad;
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


        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int ventas = rs.getInt("total_ventas");
                lista.add(nombre + " - " + ventas + " ventas");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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


        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                lista.add(nombre + " - " + cantidad + " unidades");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
