package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Venta;
import com.PabloSantos.org.repository.VentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaServiceImplements implements VentaService {
    private final VentaRepository repo;
    public VentaServiceImplements(VentaRepository repo) { this.repo = repo; }
    @Override public List<Venta> getAllVentas() { return repo.findAll(); }
    @Override public Venta getVentaById(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")); }
    @Override public Venta saveVenta(Venta v) { return repo.save(v); }
    @Override public void deleteVenta(Integer id) { repo.deleteById(id); }
}