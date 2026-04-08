package com.PabloSantos.org.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente {
    @Id @Column(name = "dpi_cliente") private Integer dpiCliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private String direccion;
    private Integer estado;

    // Getters y Setters
    public Integer getDpiCliente() { return dpiCliente; }
    public void setDpiCliente(Integer dpiCliente) { this.dpiCliente = dpiCliente; }
    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }
    public String getApellido_cliente() { return apellido_cliente; }
    public void setApellido_cliente(String apellido_cliente) { this.apellido_cliente = apellido_cliente; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}