package DAO;

import com.google.common.base.Preconditions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Producto;

public class ProductoDAO {

    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO Producto (nombre, categoria, talla, precio, stock, codigo, color, imagen, fecha_creacion, ultima_actualizacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())";
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
            System.err.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE Producto SET nombre = ?, categoria = ?, talla = ?, precio = ?, stock = ?, codigo = ?, color = ?, imagen = ?, visible = ?, ultima_actualizacion = GETDATE() WHERE id = ?";
    
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
        stmt.setBoolean(9, producto.isVisible());
        stmt.setInt(10, producto.getId()); 
        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.err.println("Error al actualizar producto: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

   public List<Producto> listarProductos() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM Producto WHERE visible = 1";
    
    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setCategoria(rs.getString("categoria"));
            p.setTalla(rs.getString("talla"));
            p.setPrecio(rs.getBigDecimal("precio"));
            p.setStock(rs.getInt("stock"));
            p.setCodigo(rs.getString("codigo"));
            p.setColor(rs.getString("color"));
            p.setImagen(rs.getString("imagen"));
            p.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
            p.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"));
            p.setVisible(rs.getBoolean("visible"));
            productos.add(p);
        }
    } catch (SQLException e) {
        System.err.println("Error al listar todos los productos: " + e.getMessage());
    }
    return productos;
}
    
    public Producto obtenerInfoProducto(String codigo) {
    String sql = "SELECT nombre, categoria, precio, talla FROM Producto WHERE codigo = ?";
    try (   Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, codigo);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Producto p = new Producto();
            p.setCodigo(codigo);
            p.setNombre(rs.getString("nombre"));
            p.setTalla(rs.getString("talla"));
            p.setCategoria(rs.getString("categoria"));
            p.setPrecio(rs.getBigDecimal("precio"));
            return p;
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar producto: " + e.getMessage());
    }
    return null;
}

    
    public List<Producto> buscarPorNombreOCodigo(String texto) {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM Producto WHERE nombre LIKE ? OR codigo LIKE ?";

    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        String parametro = "%" + texto + "%";
        stmt.setString(1, parametro);
        stmt.setString(2, parametro);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setTalla(rs.getString("talla"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCodigo(rs.getString("codigo"));
                p.setColor(rs.getString("color"));
                p.setImagen(rs.getString("imagen"));
                p.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                p.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"));
                p.setVisible(rs.getBoolean("visible")); // ¡IMPORTANTE!

                productos.add(p);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al buscar producto: " + e.getMessage());
    }

    return productos;
}
    
    public Producto buscarPorCodigo(String codigo) {
        Preconditions.checkNotNull(codigo, "El ID no puede ser nulo");

        String sql = "SELECT * FROM Producto WHERE codigo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setTalla(rs.getString("talla"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCodigo(rs.getString("codigo"));
                p.setColor(rs.getString("color"));
                p.setImagen(rs.getString("imagen"));
                p.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                p.setUltimaActualizacion(rs.getTimestamp("ultima_actualizacion"));
                return p;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }
    
    public boolean ocultarProductoPorCodigo(String codigo) {
    String sql = "UPDATE Producto SET visible = 0, ultima_actualizacion = GETDATE() WHERE codigo = ?";
    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, codigo);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al ocultar producto: " + e.getMessage());
        return false;
    }
}
}