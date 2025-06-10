package com.proyecto.test.controlador;

import Controlador.DashboardControlador;
import DAO.DashboardDAO;
import Modelo.DashboardResumen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardControladorTest {

    @Mock
    private DashboardDAO dashboardDAO;

    @InjectMocks
    private DashboardControlador controlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerResumen() {
        DashboardResumen resumenMock = new DashboardResumen();
        resumenMock.setEstadoSistema("OK");

        when(dashboardDAO.obtenerResumenDashboard()).thenReturn(resumenMock);

        DashboardResumen resultado = controlador.obtenerResumen();

        assertNotNull(resultado);
        assertEquals("OK", resultado.getEstadoSistema());
        verify(dashboardDAO, times(1)).obtenerResumenDashboard();
    }
}
