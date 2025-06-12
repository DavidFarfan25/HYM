package Modelo;

import java.util.List;

public class DashboardResumen {

    private int productosEnStock;
    private int productosBajoStock;
    private int productosSinStock;

    private List<String> productosMasVendidos;
    private List<String> ultimosIngresos;

    private String estadoSistema;

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
