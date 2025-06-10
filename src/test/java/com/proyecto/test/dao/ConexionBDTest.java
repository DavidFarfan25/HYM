package com.proyecto.test.dao;

import DAO.ConexionBD;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConexionBDTest {

    @BeforeEach
    void setUp() {
        ConexionBD.cerrarConexion();
    }

    @AfterEach
    void tearDown() {
        ConexionBD.cerrarConexion();
    }

    @Test
    void testObtenerConexionExitosa() throws SQLException {
        Connection conn = ConexionBD.obtenerConexion();

        assertNotNull(conn, "La conexión no debe ser null");
        assertFalse(conn.isClosed(), "La conexión debe estar abierta");
    }

    @Test
    void testObtenerConexionReutilizada() throws SQLException {
        Connection conn1 = ConexionBD.obtenerConexion();
        Connection conn2 = ConexionBD.obtenerConexion();

        assertSame(conn1, conn2, "La conexión debe ser la misma instancia (reutilizada)");
    }

    @Test
    void testCerrarConexion() throws SQLException {
        Connection conn = ConexionBD.obtenerConexion();
        ConexionBD.cerrarConexion();

        assertTrue(conn.isClosed(), "La conexión debe estar cerrada después de cerrarConexion()");
    }

    @Test
    void testCerrarConexionSinConexionAbierta() {
        assertDoesNotThrow(ConexionBD::cerrarConexion, "No debe lanzar excepción si no hay conexión activa");
    }
}
