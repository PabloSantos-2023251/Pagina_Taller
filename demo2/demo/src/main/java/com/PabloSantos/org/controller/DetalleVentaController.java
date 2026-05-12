package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.DetalleVenta;
import com.PabloSantos.org.service.DetalleVentaService;
import com.PabloSantos.org.service.ProductoService;
import com.PabloSantos.org.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/detalles")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;
    private final ProductoService productoService;
    private final VentaService ventaService;

    public DetalleVentaController(DetalleVentaService service, ProductoService pService, VentaService vService) {
        this.detalleVentaService = service;
        this.productoService = pService;
        this.ventaService = vService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<DetalleVenta> detalles = detalleVentaService.getAllDetalleVentas();

        if (buscar != null && !buscar.isEmpty()) {
            detalles = detalles.stream()
                    .filter(d ->
                            // Busca por nombre de producto
                            d.getProducto().getNombre_producto().toLowerCase().contains(buscar.toLowerCase()) ||
                                    // Busca por número de venta
                                    d.getVenta().getCodigoVenta().toString().contains(buscar) ||
                                    // Busca por ID de detalle
                                    d.getCodigoDetalleVenta().toString().contains(buscar)
                    )
                    .collect(Collectors.toList());
        }

        model.addAttribute("detalles", detalles);
        model.addAttribute("detalleObj", new DetalleVenta());
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("ventas", ventaService.getAllVentas());
        model.addAttribute("buscar", buscar); // Para mantener el texto en el input
        return "Detalles";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleVenta detalleVenta,
                          @RequestParam("ventaId") Integer ventaId,
                          @RequestParam("productoId") Integer productoId) {

        detalleVenta.setVenta(ventaService.getVentaById(ventaId));
        detalleVenta.setProducto(productoService.getProductoById(productoId));

        if (detalleVenta.getCantidad() != null && detalleVenta.getPrecio_unitario() != null) {
            BigDecimal cantidad = new BigDecimal(detalleVenta.getCantidad());
            detalleVenta.setSubtotal(detalleVenta.getPrecio_unitario().multiply(cantidad));
        }

        detalleVentaService.saveDetalleVenta(detalleVenta);
        return "redirect:/detalles";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detalleVentaService.deleteDetalleVenta(id);
        return "redirect:/detalles";
    }
}