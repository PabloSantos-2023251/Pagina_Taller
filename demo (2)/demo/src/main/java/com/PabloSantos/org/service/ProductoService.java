package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Producto;
import java.util.List;
public interface ProductoService {
    List<Producto> getAllProductos();
    Producto getProductoById(Integer id);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Integer id, Producto producto);
    void deleteProducto(Integer id);
}