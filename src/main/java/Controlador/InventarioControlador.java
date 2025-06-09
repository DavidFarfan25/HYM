package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InventarioControlador {
    private static final Logger logger = LoggerFactory.getLogger(InventarioControlador.class);
    private final ProductoDAO productoDAO;

    public InventarioControlador() {
        this.productoDAO = new ProductoDAO();
    }

    public InventarioControlador(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> listarProductos() {
        logger.info("Listando todos los productos");
        return productoDAO.listarProductos();
    }

    public List<Producto> buscarProductos(String texto) {
        Preconditions.checkNotNull(texto, "El texto de búsqueda no puede ser nulo");
        logger.info("Buscando productos con texto: {}", texto);
        return productoDAO.buscarPorNombreOCodigo(texto);
    }

    public Producto obtenerProductoPorCodigo(String codigo) {
        Preconditions.checkNotNull(codigo, "El código del producto no puede ser nulo");
        logger.info("Buscando producto con código: {}", codigo);
        return productoDAO.buscarPorCodigo(codigo);
    }

    public boolean registrarProducto(Producto producto) {
        Preconditions.checkNotNull(producto, "El producto no puede ser nulo");
        logger.info("Registrando producto: {}", producto);
        return productoDAO.registrarProducto(producto);
    }

    public boolean actualizarProducto(Producto producto) {
        Preconditions.checkNotNull(producto, "El producto no puede ser nulo");
        logger.info("Actualizando producto: {}", producto);
        return productoDAO.actualizarProducto(producto);
    }

    public boolean ocultarProducto(String codigo) {
        Preconditions.checkNotNull(codigo, "El código no puede ser nulo");
        logger.info("Ocultando producto con código: {}", codigo);
        return productoDAO.ocultarProductoPorCodigo(codigo);
    }
}
