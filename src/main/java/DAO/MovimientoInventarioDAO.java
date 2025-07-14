package DAO;

import Modelo.MovimientoInventario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoInventarioDAO {

    public boolean registrarMovimiento(MovimientoInventario movimiento) {
        String sql = "INSERT INTO MovimientoInventario (codigo_producto, cantidad, tipo_movimiento, motivo, fecha_movimiento) " +
                     "VALUES (?, ?, ?, ?, GETDATE())";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimiento.getCodigoProducto());
            stmt.setInt(2, movimiento.getCantidad());
            stmt.setString(3, movimiento.getTipoMovimiento());
            stmt.setString(4, movimiento.getMotivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MovimientoInventario> listarMovimientosPorRango(Date fechaInicio, Date fechaFin) {
        List<MovimientoInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM MovimientoInventario WHERE fecha_movimiento BETWEEN ? AND ? ORDER BY fecha_movimiento";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, fechaInicio);
            stmt.setDate(2, fechaFin);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MovimientoInventario m = new MovimientoInventario();
                    m.setId(rs.getInt("id"));
                    m.setCodigoProducto(rs.getString("codigo_producto"));
                    m.setCantidad(rs.getInt("cantidad"));
                    m.setTipoMovimiento(rs.getString("tipo_movimiento"));
                    m.setMotivo(rs.getString("motivo"));
                    m.setFechaMovimiento(rs.getTimestamp("fecha_movimiento"));
                    lista.add(m);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int obtenerStockAcumuladoHasta(String codigoProducto, Date fechaLimite) {
        String sql = "SELECT SUM(CASE WHEN tipo_movimiento = 'entrada' THEN cantidad ELSE -cantidad END) as stock " +
             "FROM MovimientoInventario " +
             "WHERE codigo_producto = ? AND fecha_movimiento <= ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoProducto);
            stmt.setDate(2, fechaLimite);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
