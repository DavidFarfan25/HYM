package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InventarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(InventarioControlador.class);
    private final ProductoDAO productoDAO;
    private Timer simuladorTimer;

    public InventarioControlador() {
        this.productoDAO = new ProductoDAO();
    }

    
    public InventarioControlador(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> listarProductos() {
        logger.info("Listando todos los productos visibles del inventario.");
        return productoDAO.listarProductos();
    }

    public List<Producto> buscarProductos(String termino) {
        Preconditions.checkNotNull(termino, "El término de búsqueda no puede ser null");
        Preconditions.checkArgument(!termino.trim().isEmpty(), "El término de búsqueda no puede estar vacío");

        logger.info("Buscando productos por nombre o código: '{}'", termino);
        return productoDAO.buscarPorNombreOCodigo(termino);
    }

    public Producto obtenerProducto(String codigo) {
        Preconditions.checkNotNull(codigo, "El código del producto no puede ser null");
        Preconditions.checkArgument(!codigo.trim().isEmpty(), "El código del producto no puede estar vacío");

        logger.info("Obteniendo producto con código: '{}'", codigo);
        return productoDAO.buscarPorCodigo(codigo);
    }

    public Producto obtenerInfoProducto(String codigo) {
        Preconditions.checkNotNull(codigo, "El código del producto no puede ser null");
        Preconditions.checkArgument(!codigo.trim().isEmpty(), "El código del producto no puede estar vacío");

        logger.info("Obteniendo información básica del producto con código: '{}'", codigo);
        return productoDAO.obtenerInfoProducto(codigo);
    }

    public boolean ocultarProducto(String codigo) {
        Preconditions.checkNotNull(codigo, "El código del producto no puede ser null");
        Preconditions.checkArgument(!codigo.trim().isEmpty(), "El código del producto no puede estar vacío");

        logger.info("Ocultando producto con código: '{}'", codigo);
        boolean exito = productoDAO.ocultarProductoPorCodigo(codigo);

        if (exito) {
            logger.info("Producto con código '{}' ocultado correctamente.", codigo);
        } else {
            logger.warn("No se pudo ocultar el producto con código '{}'", codigo);
        }

        return exito;
    }

    public boolean aumentarStock(String codigo, int cantidad) {
        Preconditions.checkNotNull(codigo, "El código del producto no puede ser null");
        Preconditions.checkArgument(!codigo.trim().isEmpty(), "El código del producto no puede estar vacío");
        Preconditions.checkArgument(cantidad > 0, "La cantidad debe ser positiva");

        logger.info("Aumentando stock del producto con código '{}'. Cantidad: {}", codigo, cantidad);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            logger.warn("No se encontró producto con código '{}'", codigo);
            return false;
        }

        producto.setStock(producto.getStock() + cantidad);
        return productoDAO.actualizarProducto(producto);
    }

    public boolean actualizarProducto(Producto producto) {
        Preconditions.checkNotNull(producto, "El producto no puede ser null");
        Preconditions.checkNotNull(producto.getCodigo(), "El código del producto no puede ser null");

        logger.info("Actualizando producto ID '{}': nuevo stock {}, visible: {}",
                producto.getId(), producto.getStock(), producto.isVisible());

        return productoDAO.actualizarProducto(producto);
    }


    public void iniciarSimuladorDeStock() {
    if (simuladorTimer != null && simuladorTimer.isRunning()) {
        logger.info("El simulador ya está en ejecución.");
        return;
    }

    simuladorTimer = new Timer(2000, (ActionEvent e) -> {
        List<Producto> productos = productoDAO.listarProductos();
        List<Producto> disponibles = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getStock() > 0) {
                disponibles.add(p);
            }
        }

        if (disponibles.isEmpty()) {
            logger.info("No hay productos con stock disponible.");
            return;
        }

        Collections.shuffle(disponibles);

        int limite = Math.min(30, disponibles.size());

        for (int i = 0; i < limite; i++) {
            Producto producto = disponibles.get(i);
            int stockActual = producto.getStock();
            int nuevoStock;

            if (stockActual >= 10) {
                nuevoStock = stockActual - 10;
            } else {
                nuevoStock = stockActual - 1;
            }

            nuevoStock = Math.max(0, nuevoStock);

            producto.setStock(nuevoStock);
            productoDAO.actualizarProducto(producto);

            logger.info("Stock actualizado: {} = {}", producto.getNombre(), nuevoStock);

            if (nuevoStock == 15 || nuevoStock == 10 || nuevoStock == 5) {
                JOptionPane.showMessageDialog(
                        null,
                        "STOCK BAJO: El producto \"" + producto.getNombre() + "\" tiene " + nuevoStock + " unidades.",
                        "Alerta de Stock Bajo",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            if (nuevoStock <= 5 && stockActual > 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "STOCK CRÍTICO: El producto \"" + producto.getNombre() + "\" tiene solo " + nuevoStock + " unidades.",
                        "¡ALERTA CRÍTICA!",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    });

    simuladorTimer.start();
    logger.info("Simulador de reducción de stock iniciado.");
}

    
    public void detenerSimuladorDeStock() {
        if (simuladorTimer != null) {
            simuladorTimer.stop();
            logger.info("Simulador detenido.");
        }
    }
}
