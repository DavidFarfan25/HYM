package com.proyecto.test.controlador;

import Controlador.InventarioControlador;
import DAO.ProductoDAO;
import Modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventarioControladorTest {

    private ProductoDAO productoDAO;
    private InventarioControlador controlador;

    @BeforeEach
    void setUp() {
        productoDAO = mock(ProductoDAO.class);
        controlador = new InventarioControlador(productoDAO); // Constructor debe recibir el DAO
    }

    @Test
    void testListarProductos() {
        Producto producto = crearProducto();
        when(productoDAO.listarProductos()).thenReturn(Collections.singletonList(producto));

        List<Producto> resultado = controlador.listarProductos();

        assertEquals(1, resultado.size());
        assertEquals("Camiseta", resultado.get(0).getNombre());
        verify(productoDAO).listarProductos();
    }

    @Test
    void testBuscarProductos() {
        Producto producto = crearProducto();
        when(productoDAO.buscarPorNombreOCodigo("camiseta")).thenReturn(Collections.singletonList(producto));

        List<Producto> resultado = controlador.buscarProductos("camiseta");

        assertFalse(resultado.isEmpty());
        assertEquals("Camiseta", resultado.get(0).getNombre());
        verify(productoDAO).buscarPorNombreOCodigo("camiseta");
    }

    @Test
    void testObtenerProductoPorCodigo() {
        Producto producto = crearProducto();
        when(productoDAO.buscarPorCodigo("123")).thenReturn(producto);

        Producto resultado = controlador.obtenerProductoPorCodigo("123");

        assertNotNull(resultado);
        assertEquals("123", resultado.getCodigo());
        verify(productoDAO).buscarPorCodigo("123");
    }

    @Test
    void testRegistrarProducto() {
        Producto producto = crearProducto();
        when(productoDAO.registrarProducto(producto)).thenReturn(true);

        boolean resultado = controlador.registrarProducto(producto);

        assertTrue(resultado);
        verify(productoDAO).registrarProducto(producto);
    }

    @Test
    void testActualizarProducto() {
        Producto producto = crearProducto();
        when(productoDAO.actualizarProducto(producto)).thenReturn(true);

        boolean resultado = controlador.actualizarProducto(producto);

        assertTrue(resultado);
        verify(productoDAO).actualizarProducto(producto);
    }

    @Test
    void testOcultarProducto() {
        when(productoDAO.ocultarProductoPorCodigo("123")).thenReturn(true);

        boolean resultado = controlador.ocultarProducto("123");

        assertTrue(resultado);
        verify(productoDAO).ocultarProductoPorCodigo("123");
    }

    // Método de utilidad para crear un producto de prueba
    private Producto crearProducto() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Camiseta");
        producto.setCategoria("Ropa");
        producto.setTalla("M");
        producto.setPrecio(new BigDecimal("29.99"));
        producto.setStock(10);
        producto.setCodigo("123");
        producto.setColor("Rojo");
        producto.setImagen("camiseta.jpg");
        producto.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        producto.setUltimaActualizacion(new Timestamp(System.currentTimeMillis()));
        producto.setVisible(true);
        return producto;
    }
}
