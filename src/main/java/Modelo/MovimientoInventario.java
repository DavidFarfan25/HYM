package Modelo;

import java.sql.Timestamp;

public class MovimientoInventario {

    private int id;
    private String codigoProducto;
    private int cantidad;
    private String tipoMovimiento; // "entrada" o "salida"
    private String motivo;
    private Timestamp fechaMovimiento;

    public MovimientoInventario() {
    }

    public MovimientoInventario(String codigoProducto, int cantidad, String tipoMovimiento, String motivo) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.motivo = motivo;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}
