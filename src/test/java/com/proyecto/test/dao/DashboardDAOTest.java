package com.proyecto.test.dao;

import DAO.ConexionBD;
import DAO.DashboardDAO;
import Modelo.DashboardResumen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardDAOTest {

    private DashboardDAO dao;
    private Connection conexion;

    @BeforeEach
    void setUp() throws SQLException {
        conexion = ConexionBD.obtenerConexion(); // Usa la conexión configurada en ConexionBD
        dao = new DashboardDAO(conexion);
    }

    @AfterEach
    void tearDown() {
        ConexionBD.cerrarConexion();
    }

    @Test
    void testObtenerResumenDashboard() {
        DashboardResumen resumen = dao.obtenerResumenDashboard();
        assertNotNull(resumen);
        assertTrue(resumen.getProductosEnStock() >= 0);
        assertTrue(resumen.getProductosBajoStock() >= 0);
        assertTrue(resumen.getProductosSinStock() >= 0);
        assertNotNull(resumen.getProductosMasVendidos());
        assertNotNull(resumen.getUltimosIngresos());
        assertEquals("Todos los módulos trabajando correctamente", resumen.getEstadoSistema());
    }

    @Test
    void testContarProductosPorStock() {
        int enStock = dao.contarProductosPorStock("> 10");
        int bajoStock = dao.contarProductosPorStock("BETWEEN 1 AND 10");
        int sinStock = dao.contarProductosPorStock("= 0");

        assertTrue(enStock >= 0);
        assertTrue(bajoStock >= 0);
        assertTrue(sinStock >= 0);
    }

    @Test
    void testObtenerProductosMasVendidos() {
        List<String> productos = dao.obtenerProductosMasVendidos();
        assertNotNull(productos);
        assertTrue(productos.size() <= 3);
        for (String producto : productos) {
            assertTrue(producto.contains("ventas"));
        }
    }

    @Test
    void testObtenerUltimosIngresos() {
        List<String> ingresos = dao.obtenerUltimosIngresos();
        assertNotNull(ingresos);
        assertTrue(ingresos.size() <= 3);
        for (String ingreso : ingresos) {
            assertTrue(ingreso.contains("unidades"));
        }
    }
}
