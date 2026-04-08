package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Producto;
import com.PabloSantos.org.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImplements implements ProductoService {
    private final ProductoRepository repo;
    public ProductoServiceImplements(ProductoRepository repo) { this.repo = repo; }
    @Override public List<Producto> getAllProductos() { return repo.findAll(); }
    @Override public Producto getProductoById(Integer id) { return repo.findById(id).orElse(null); }
    @Override public Producto saveProducto(Producto p) { return repo.save(p); }
    @Override public Producto updateProducto(Integer id, Producto p) { p.setCodigoProducto(id); return repo.save(p); }
    @Override public void deleteProducto(Integer id) { repo.deleteById(id); }
}