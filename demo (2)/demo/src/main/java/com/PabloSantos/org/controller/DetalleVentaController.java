package com.PabloSantos.org.controller;
import com.PabloSantos.org.entity.DetalleVenta;
import com.PabloSantos.org.service.DetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;
    public DetalleVentaController(DetalleVentaService service) { this.detalleVentaService = service; }

    @GetMapping
    public List<DetalleVenta> getAllDetalleVentas() {
        return detalleVentaService.getAllDetalleVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetalleVentaById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(detalleVentaService.getDetalleVentaById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        try {
            return new ResponseEntity<>(detalleVentaService.saveDetalleVenta(detalleVenta), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetalleVenta(@PathVariable Integer id) {
        try {
            detalleVentaService.deleteDetalleVenta(id);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}