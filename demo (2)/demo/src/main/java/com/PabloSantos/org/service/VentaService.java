package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> getAllVentas();
    Venta getVentaById(Integer id);
    Venta saveVenta(Venta venta);
    void deleteVenta(Integer id);
}