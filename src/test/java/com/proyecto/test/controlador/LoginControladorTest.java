package com.proyecto.test.controlador;

import Controlador.LoginControlador;
import DAO.TIDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControladorTest {

    @Mock
    private TIDAO usuarioDAO;

    @InjectMocks
    private LoginControlador controlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidarLogin() {
        when(usuarioDAO.validarCredenciales("usuario1", "pass1")).thenReturn(true);
        when(usuarioDAO.validarCredenciales("usuario2", "pass2")).thenReturn(false);

        assertTrue(controlador.validarLogin("usuario1", "pass1"));
        assertFalse(controlador.validarLogin("usuario2", "pass2"));

        verify(usuarioDAO).validarCredenciales("usuario1", "pass1");
        verify(usuarioDAO).validarCredenciales("usuario2", "pass2");
    }
}
