package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.DetalleVenta;
import com.PabloSantos.org.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService {
    private final DetalleVentaRepository repo;
    public DetalleVentaServiceImplements(DetalleVentaRepository repo) { this.repo = repo; }
    @Override public List<DetalleVenta> getAllDetalleVentas() { return repo.findAll(); }
    @Override public DetalleVenta getDetalleVentaById(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")); }
    @Override public DetalleVenta saveDetalleVenta(DetalleVenta dv) { return repo.save(dv); }
    @Override public void deleteDetalleVenta(Integer id) { repo.deleteById(id); }
}