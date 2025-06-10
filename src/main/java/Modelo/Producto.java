package Modelo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Clase que representa un producto del inventario.
 */
public class Producto {

    // === Atributos ===
    private int id;
    private String nombre;
    private String categoria;
    private String talla;
    private BigDecimal precio;
    private int stock;
    private String codigo;
    private String color;
    private String imagen;
    private Timestamp fechaCreacion;
    private Timestamp ultimaActualizacion;
    private boolean visible;

    // === Constructores ===

    public Producto() {
        // Constructor vacío por defecto
    }

    public Producto(
        int id,
        String nombre,
        String categoria,
        String talla,
        BigDecimal precio,
        int stock,
        String codigo,
        String color,
        String imagen,
        Timestamp fechaCreacion,
        Timestamp ultimaActualizacion
    ) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
        this.color = color;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    // === Getters y Setters ===

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Timestamp ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
