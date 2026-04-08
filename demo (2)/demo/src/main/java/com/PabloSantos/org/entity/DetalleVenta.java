package com.PabloSantos.org.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {
    @Id @Column(name = "codigo_detalle_venta") private Integer codigoDetalleVenta;
    private Integer cantidad;
    private BigDecimal precio_unitario;
    private BigDecimal subtotal;

    @ManyToOne @JoinColumn(name = "Productos_codigo_producto") private Producto producto;
    @ManyToOne @JoinColumn(name = "Ventas_codigo_venta") private Venta venta;

    // Getters y Setters
    public Integer getCodigoDetalleVenta() { return codigoDetalleVenta; }
    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) { this.codigoDetalleVenta = codigoDetalleVenta; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(BigDecimal precio_unitario) { this.precio_unitario = precio_unitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
}