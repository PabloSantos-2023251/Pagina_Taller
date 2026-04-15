package com.PabloSantos.org.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Venta {

    @Id
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_venta")
    private LocalDate fecha_venta;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario")
    private Usuario usuario;

    // Getters y Setters
    public Integer getCodigoVenta() { return codigoVenta; }
    public void setCodigoVenta(Integer codigoVenta) { this.codigoVenta = codigoVenta; }
    public LocalDate getFecha_venta() { return fecha_venta; }
    public void setFecha_venta(LocalDate fecha_venta) { this.fecha_venta = fecha_venta; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}