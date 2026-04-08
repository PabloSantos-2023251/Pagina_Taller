package com.PabloSantos.org.controller;
import com.PabloSantos.org.entity.Producto;
import com.PabloSantos.org.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService service) { this.productoService = service; }

    @GetMapping
    public List<Producto> getAllProductos() { return productoService.getAllProductos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
        try { return ResponseEntity.ok(productoService.getProductoById(id)); }
        catch (RuntimeException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }
    }

    @PostMapping
    public ResponseEntity<Object> saveProducto(@RequestBody Producto producto) {
        try { return new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED); }
        catch (Exception e) { return ResponseEntity.badRequest().body("Error"); }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        try { productoService.updateProducto(id, producto); return ResponseEntity.ok("Actualizado"); }
        catch (RuntimeException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) {
        try { productoService.deleteProducto(id); return ResponseEntity.ok("Eliminado"); }
        catch (RuntimeException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }
    }
}