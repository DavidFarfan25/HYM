package Modelo;

import java.util.List;

/**
 * Clase que representa un resumen de datos para el dashboard principal.
 */
public class DashboardResumen {

    // === Atributos ===
    private int productosEnStock;
    private int productosBajoStock;
    private int productosSinStock;

    private List<String> productosMasVendidos;
    private List<String> ultimosIngresos;

    private String estadoSistema;

    // === Getters y Setters ===

    public int getProductosEnStock() {
        return productosEnStock;
    }

    public void setProductosEnStock(int productosEnStock) {
        this.productosEnStock = productosEnStock;
    }

    public int getProductosBajoStock() {
        return productosBajoStock;
    }

    public void setProductosBajoStock(int productosBajoStock) {
        this.productosBajoStock = productosBajoStock;
    }

    public int getProductosSinStock() {
        return productosSinStock;
    }

    public void setProductosSinStock(int productosSinStock) {
        this.productosSinStock = productosSinStock;
    }

    public List<String> getProductosMasVendidos() {
        return productosMasVendidos;
    }

    public void setProductosMasVendidos(List<String> productosMasVendidos) {
        this.productosMasVendidos = productosMasVendidos;
    }

    public List<String> getUltimosIngresos() {
        return ultimosIngresos;
    }

    public void setUltimosIngresos(List<String> ultimosIngresos) {
        this.ultimosIngresos = ultimosIngresos;
    }

    public String getEstadoSistema() {
        return estadoSistema;
    }

    public void setEstadoSistema(String estadoSistema) {
        this.estadoSistema = estadoSistema;
    }
}
