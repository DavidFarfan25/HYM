package Test;

import DAO.ProductoDAO;
import Modelo.Producto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoDAOTest {

    private final ProductoDAO dao = new ProductoDAO();

    @Test
    void listarProductos_debeRetornarLista() {
        List<Producto> productos = dao.listarProductos();
        assertNotNull(productos, "La lista de productos no debe ser null");
    }

    @Test
    void buscarPorCodigo_debeRetornarProductoSiExiste() {
        Producto producto = dao.buscarPorCodigo("X001");
        if (producto != null) {
            assertEquals("X001", producto.getCodigo());
        }
    }

    @Test
    void registrarYActualizarProducto() {
        Producto nuevo = new Producto();
        nuevo.setCodigo("TEST123");
        nuevo.setNombre("Test Producto");
        nuevo.setCategoria("Pruebas");
        nuevo.setTalla("M");
        nuevo.setPrecio(new BigDecimal("49.99"));
        nuevo.setStock(10);
        nuevo.setColor("Azul");
        nuevo.setImagen("imagen.jpg");
        nuevo.setVisible(true);

        boolean registrado = dao.registrarProducto(nuevo);
        assertTrue(registrado, "No se pudo registrar el producto de prueba");

        // Actualiza el stock
        nuevo.setStock(15);
        boolean actualizado = dao.actualizarProducto(nuevo);
        assertTrue(actualizado, "No se pudo actualizar el producto");
    }

    @Test
    void ocultarProductoPorCodigo_debeMarcarComoNoVisible() {
        Producto p = new Producto();
        p.setCodigo("TEST123");
        p.setNombre("Producto Ocultable");
        p.setCategoria("Pruebas");
        p.setTalla("S");
        p.setPrecio(new BigDecimal("10.00"));
        p.setStock(5);
        p.setColor("Rojo");
        p.setImagen("img.jpg");
        p.setVisible(true);

        if (dao.buscarPorCodigo("TEST123") == null) {
            dao.registrarProducto(p);
        }

        boolean resultado = dao.ocultarProductoPorCodigo("TEST123");
        assertTrue(resultado, "No se pudo ocultar el producto con código TEST123");
    }
}
