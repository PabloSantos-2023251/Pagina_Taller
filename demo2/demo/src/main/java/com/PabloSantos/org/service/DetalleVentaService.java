package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> getAllDetalleVentas();
    DetalleVenta getDetalleVentaById(Integer id);
    DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta);
    void deleteDetalleVenta(Integer id);
}