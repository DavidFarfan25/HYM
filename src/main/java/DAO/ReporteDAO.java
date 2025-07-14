package DAO;

import Modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    public static class ProductoComparativo {
        private String codigo;
        private String nombre;
        private int stockInicial;
        private int stockFinal;

        public ProductoComparativo(String codigo, String nombre, int stockInicial, int stockFinal) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.stockInicial = stockInicial;
            this.stockFinal = stockFinal;
        }

        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public int getStockInicial() { return stockInicial; }
        public int getStockFinal() { return stockFinal; }
        public int getVariacion() { return stockFinal - stockInicial; }
    }

    public List<ProductoComparativo> obtenerComparativaDeStock(Timestamp fechaInicio, Timestamp fechaFin) {
    List<ProductoComparativo> lista = new ArrayList<>();

    String sql = "SELECT p.codigo, p.nombre, " +
         "ISNULL(( " +
         "    SELECT SUM(CASE WHEN m.tipo_movimiento = 'entrada' THEN m.cantidad ELSE -m.cantidad END) " +
         "    FROM MovimientoInventario m " +
         "    WHERE m.codigo_producto = p.codigo AND m.fecha_movimiento < ? " +
         "), 0) AS stock_inicial, " +
         "ISNULL(( " +
         "    SELECT SUM(CASE WHEN m.tipo_movimiento = 'entrada' THEN m.cantidad ELSE -m.cantidad END) " +
         "    FROM MovimientoInventario m " +
         "    WHERE m.codigo_producto = p.codigo AND m.fecha_movimiento <= ? " +
         "), 0) AS stock_final " +
         "FROM Producto p " +
         "WHERE p.visible = 1 " +
         "ORDER BY p.nombre";

    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setTimestamp(1, fechaInicio);
        stmt.setTimestamp(2, fechaFin);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                int stockInicial = rs.getInt("stock_inicial");
                int stockFinal = rs.getInt("stock_final");

                lista.add(new ProductoComparativo(codigo, nombre, stockInicial, stockFinal));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}
