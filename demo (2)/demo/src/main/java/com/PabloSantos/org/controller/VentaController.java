package com.PabloSantos.org.controller;
import com.PabloSantos.org.entity.Venta;
import com.PabloSantos.org.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private final VentaService ventaService;
    public VentaController(VentaService service) { this.ventaService = service; }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVentaById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ventaService.getVentaById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveVenta(@RequestBody Venta venta) {
        try {
            return new ResponseEntity<>(ventaService.saveVenta(venta), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVenta(@PathVariable Integer id) {
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}