package com.proyecto.test.dao;

import DAO.UsuarioDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    private UsuarioDAO dao;

    @BeforeEach
    void setUp() {
        dao = new UsuarioDAO();
    }

    @Test
    void testValidarCredencialesCorrectas() {
        boolean resultado = dao.validarCredenciales("admin", "1234");
        assertTrue(resultado, "Debe validar correctamente credenciales válidas");
    }

    @Test
    void testValidarCredencialesIncorrectas() {
        boolean resultado = dao.validarCredenciales("usuarioNoExistente", "passIncorrecta");
        assertFalse(resultado, "Debe devolver false para credenciales inválidas");
    }

    @Test
    void testValidarCredencialesConCamposVacios() {
        assertThrows(IllegalArgumentException.class, () -> {
            dao.validarCredenciales("", "");
        }, "Debe lanzar excepción por campos vacíos");
    }

    @Test
    void testValidarCredencialesNulos() {
        assertThrows(IllegalArgumentException.class, () -> {
            dao.validarCredenciales(null, null);
        }, "Debe lanzar excepción por valores nulos");
    }
}
