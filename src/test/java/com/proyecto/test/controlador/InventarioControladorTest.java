package Test;

import Controlador.InventarioControlador;
import DAO.ProductoDAO;
import Modelo.Producto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioControladorTest {

    private ProductoDAO productoDAO;
    private InventarioControlador controlador;

    @BeforeEach
    void setUp() {
        productoDAO = mock(ProductoDAO.class);
        controlador = new InventarioControlador(productoDAO);
    }

    @Test
    void listarProductos_debeRetornarLista() {
        Producto producto = new Producto();
        when(productoDAO.listarProductos()).thenReturn(List.of(producto));

        List<Producto> productos = controlador.listarProductos();

        assertEquals(1, productos.size());
        verify(productoDAO).listarProductos();
    }

    @Test
    void buscarProducto_debeRetornarCoincidencias() {
        Producto mock = new Producto();
        when(productoDAO.buscarPorNombreOCodigo("ABC")).thenReturn(List.of(mock));

        List<Producto> resultados = controlador.buscarProductos("ABC");

        assertFalse(resultados.isEmpty());
        verify(productoDAO).buscarPorNombreOCodigo("ABC");
    }

    @Test
    void obtenerProducto_conCodigoExistente_debeRetornarProducto() {
        Producto p = new Producto();
        when(productoDAO.buscarPorCodigo("123")).thenReturn(p);

        Producto resultado = controlador.obtenerProducto("123");

        assertNotNull(resultado);
        verify(productoDAO).buscarPorCodigo("123");
    }

    @Test
    void aumentarStock_debeActualizarStock() {
        Producto p = new Producto();
        p.setCodigo("X001");
        p.setStock(5);

        when(productoDAO.buscarPorCodigo("X001")).thenReturn(p);
        when(productoDAO.actualizarProducto(any(Producto.class))).thenReturn(true);

        boolean actualizado = controlador.aumentarStock("X001", 3);

        assertTrue(actualizado);
        verify(productoDAO).buscarPorCodigo("X001");
        verify(productoDAO).actualizarProducto(any(Producto.class));
    }

    @Test
    void ocultarProducto_conCodigoValido_debeLlamarDAO() {
        when(productoDAO.ocultarProductoPorCodigo("X001")).thenReturn(true);

        boolean result = controlador.ocultarProducto("X001");

        assertTrue(result);
        verify(productoDAO).ocultarProductoPorCodigo("X001");
    }
}
