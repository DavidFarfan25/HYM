package DAO;

import Modelo.Producto;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductoDAO.class);

    
    public List<Producto> listarProductos() {
        logger.info("Consultando todos los productos visibles");
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM Producto WHERE visible = 1 ORDER BY nombre";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            logger.error("Error al listar productos: {}", e.getMessage(), e);
        }

        return productos;
    }

    
    public List<Producto> buscarPorNombreOCodigo(String termino) {
        Preconditions.checkNotNull(termino, "El término de búsqueda no puede ser null");

        logger.info("Buscando productos por término: '{}'", termino);
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM Producto WHERE nombre LIKE ? OR codigo LIKE ? ORDER BY nombre";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String likeTerm = "%" + termino + "%";
            stmt.setString(1, likeTerm);
            stmt.setString(2, likeTerm);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            logger.error("Error al buscar productos: {}", e.getMessage(), e);
        }

        return productos;
    }

    
    public Producto buscarPorCodigo(String codigo) {
        Preconditions.checkNotNull(codigo, "El código no puede ser null");

        logger.info("Buscando producto con código: '{}'", codigo);
        String sql = "SELECT * FROM Producto WHERE codigo = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }

        } catch (SQLException e) {
            logger.error("Error al buscar producto por código '{}': {}", codigo, e);
        }

        return null;
    }

   
    public Producto obtenerInfoProducto(String codigo) {
        Preconditions.checkNotNull(codigo, "El código no puede ser null");

        logger.info("Obteniendo información básica del producto con código: '{}'", codigo);
        String sql = "SELECT codigo, nombre, precio, talla FROM Producto WHERE codigo = ? AND visible = 1";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setTalla(rs.getString("talla"));
                    producto.setPrecio(rs.getBigDecimal("precio"));
                    return producto;
                }
            }

        } catch (SQLException e) {
            logger.error("Error al obtener info básica del producto '{}': {}", codigo, e);
        }

        return null;
    }

   
    public boolean ocultarProductoPorCodigo(String codigo) {
        Preconditions.checkNotNull(codigo, "El código no puede ser null");

        logger.info("Marcando como no visible el producto con código: '{}'", codigo);
        String sql = "UPDATE Producto SET visible = 0, ultima_actualizacion = GETDATE() WHERE codigo = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error al ocultar producto con código '{}': {}", codigo, e);
        }

        return false;
    }

    
   public boolean actualizarProducto(Producto producto) {
    Preconditions.checkNotNull(producto, "El producto no puede ser null");
    Preconditions.checkNotNull(producto.getCodigo(), "El código del producto no puede ser null");

    logger.info("Actualizando producto con código: '{}'", producto.getCodigo());

    String sqlUpdate = "UPDATE Producto SET " +
            "nombre = ?, categoria = ?, talla = ?, precio = ?, stock = ?, color = ?, imagen = ?, " +
            "visible = ?, ultima_actualizacion = GETDATE() " +
            "WHERE codigo = ?";

    String sqlSelectStock = "SELECT stock FROM Producto WHERE codigo = ?";
    String sqlInsertMovimiento = "INSERT INTO MovimientoInventario (codigo_producto, tipo_movimiento, cantidad, fecha_movimiento) VALUES (?, ?, ?, GETDATE())";

    try (Connection conn = ConexionBD.obtenerConexion()) {
        conn.setAutoCommit(false);

        int stockAnterior = 0;

        // Obtener stock anterior
        try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelectStock)) {
            stmtSelect.setString(1, producto.getCodigo());
            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    stockAnterior = rs.getInt("stock");
                }
            }
        }

        int stockNuevo = producto.getStock();
        int diferencia = stockNuevo - stockAnterior;

        // Actualizar producto
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
            stmtUpdate.setString(1, producto.getNombre());
            stmtUpdate.setString(2, producto.getCategoria());
            stmtUpdate.setString(3, producto.getTalla());
            stmtUpdate.setBigDecimal(4, producto.getPrecio());
            stmtUpdate.setInt(5, stockNuevo);
            stmtUpdate.setString(6, producto.getColor());
            stmtUpdate.setString(7, producto.getImagen());
            stmtUpdate.setInt(8, producto.isVisible() ? 1 : 0);
            stmtUpdate.setString(9, producto.getCodigo());
            stmtUpdate.executeUpdate();
        }

        // Insertar movimiento solo si hay cambio
        if (diferencia != 0) {
            String tipo = (diferencia > 0) ? "entrada" : "salida";

            try (PreparedStatement stmtMov = conn.prepareStatement(sqlInsertMovimiento)) {
                stmtMov.setString(1, producto.getCodigo());
                stmtMov.setString(2, tipo);
                stmtMov.setInt(3, Math.abs(diferencia)); // La cantidad siempre positiva
                stmtMov.executeUpdate();
            }
        }

        conn.commit();
        return true;

    } catch (SQLException e) {
        logger.error("Error al actualizar producto '{}': {}", producto.getCodigo(), e);
        e.printStackTrace();
    }

    return false;
}

  
    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO Producto (nombre, categoria, talla, precio, stock, codigo, color, imagen, " +
             "fecha_creacion, ultima_actualizacion, visible) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(), 1)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setString(3, producto.getTalla());
            stmt.setBigDecimal(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setString(6, producto.getCodigo());
            stmt.setString(7, producto.getColor());
            stmt.setString(8, producto.getImagen());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Error al registrar producto '{}': {}", producto.getCodigo(), e);
        }

        return false;
    }

    
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();

        producto.setId(rs.getInt("id"));
        producto.setCodigo(rs.getString("codigo"));
        producto.setNombre(rs.getString("nombre"));
        producto.setCategoria(rs.getString("categoria"));
        producto.setTalla(rs.getString("talla"));
        producto.setPrecio(rs.getBigDecimal("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setColor(rs.getString("color"));
        producto.setImagen(rs.getString("imagen"));
        producto.setVisible(rs.getBoolean("visible"));
        producto.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        producto.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"));

        return producto;
    }
}
