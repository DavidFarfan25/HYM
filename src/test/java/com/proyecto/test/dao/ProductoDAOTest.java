package com.proyecto.test.dao;

import DAO.ProductoDAO;
import Modelo.Producto;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoDAOTest {

    private ProductoDAO productoDAO;
    
    @BeforeEach
void prepararDatos() {
    productoDAO = new ProductoDAO();
    // Insertar productos necesarios para los tests
    Producto p1 = new Producto(
        0,
        "ProductoTest",
        "CategoriaTest",
        "M",
        BigDecimal.valueOf(150.0),
        10,
        "COD12345",
        "Rojo",
        "imagen.jpg",
        new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis())
    );
    productoDAO.registrarProducto(p1);

    Producto p2 = new Producto(
        0,
        "ProductoTestActualizar",
        "CategoriaTest",
        "L",
        BigDecimal.valueOf(200.0),
        5,
        "CODACT01",
        "Azul",
        "imagen2.jpg",
        new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis())
    );
    productoDAO.registrarProducto(p2);
}

@AfterEach
void limpiarDatos() {
    // Aquí deberías implementar la lógica para eliminar los productos de prueba
    productoDAO.ocultarProductoPorCodigo("COD12345");
    productoDAO.ocultarProductoPorCodigo("CODACT01");
}

    @BeforeEach
    void setUp() {
        productoDAO = new ProductoDAO();
    }

    @Test
    void testRegistrarProducto() {
        Producto producto = new Producto(
            0,
            "ProductoTest",
            "CategoriaTest",
            "M",
            BigDecimal.valueOf(150.0),
            10,
            "COD12345",
            "Rojo",
            "imagen.jpg",
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
        );

        boolean resultado = productoDAO.registrarProducto(producto);
        assertTrue(resultado, "Debe registrar el producto exitosamente");
    }

    @Test
    void testActualizarProducto() {
        // Primero registramos un producto para luego actualizarlo
        Producto producto = new Producto(
            0,
            "ProductoTestActualizar",
            "CategoriaTest",
            "L",
            BigDecimal.valueOf(200.0),
            5,
            "CODACT01",
            "Azul",
            "imagen2.jpg",
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
        );
        productoDAO.registrarProducto(producto);

        // Simular que obtuvimos el ID (para pruebas reales deberías consultar la BD)
        producto.setId(obtenerIdPorCodigo("CODACT01")); // Implementa este método si quieres obtener id real

        producto.setPrecio(BigDecimal.valueOf(250.0));
        producto.setStock(7);
        producto.setVisible(true);

        boolean actualizado = productoDAO.actualizarProducto(producto);
        assertTrue(actualizado, "Debe actualizar el producto correctamente");
    }

    @Test
    void testListarProductos() {
        List<Producto> productos = productoDAO.listarProductos();
        assertNotNull(productos, "La lista no debe ser null");
        assertFalse(productos.isEmpty(), "Debe tener productos visibles");
    }

    @Test
    void testObtenerInfoProducto() {
        Producto producto = productoDAO.obtenerInfoProducto("COD12345");
        assertNotNull(producto, "Debe encontrar el producto por código");
        assertEquals("COD12345", producto.getCodigo());
    }

    @Test
    void testBuscarPorNombreOCodigo() {
        List<Producto> productos = productoDAO.buscarPorNombreOCodigo("ProductoTest");
        assertNotNull(productos);
        assertTrue(productos.size() > 0, "Debe encontrar productos con nombre o código parecido");
    }

    @Test
    void testBuscarPorCodigo() {
        Producto producto = productoDAO.buscarPorCodigo("COD12345");
        assertNotNull(producto, "Debe encontrar producto con el código exacto");
    }

    @Test
    void testOcultarProductoPorCodigo() {
        boolean resultado = productoDAO.ocultarProductoPorCodigo("COD12345");
        assertTrue(resultado, "Debe ocultar el producto correctamente");
    }

    // Método auxiliar para obtener id de producto, si lo necesitas para tests reales
    private int obtenerIdPorCodigo(String codigo) {
        Producto p = productoDAO.buscarPorCodigo(codigo);
        return (p != null) ? p.getId() : 0;
    }
}
