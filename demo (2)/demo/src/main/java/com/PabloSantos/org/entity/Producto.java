package com.PabloSantos.org.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "Productos")
public class Producto {
    @Id @Column(name = "codigo_producto") private Integer codigoProducto;
    private String nombre_producto;
    private BigDecimal precio;
    private Integer stock;
    private Integer estado;

    public Integer getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(Integer codigoProducto) { this.codigoProducto = codigoProducto; }
    public String getNombre_producto() { return nombre_producto; }
    public void setNombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}