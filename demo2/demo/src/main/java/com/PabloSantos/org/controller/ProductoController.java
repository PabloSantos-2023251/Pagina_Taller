package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Producto;
import com.PabloSantos.org.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    public ProductoController(ProductoService service) { this.productoService = service; }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Producto> productos = productoService.getAllProductos();
        if (buscar != null && !buscar.isEmpty()) {
            productos = productos.stream()
                    .filter(p -> p.getNombre_producto().toLowerCase().contains(buscar.toLowerCase()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("productos", productos);
        model.addAttribute("productoObj", new Producto());
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String precargar(@PathVariable Integer id, Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("productoObj", productoService.getProductoById(id));
        model.addAttribute("editando", true);
        return "productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            productoService.deleteProducto(id);
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se puede eliminar: El producto tiene ventas asociadas.");
        }
        return "redirect:/productos";
    }
}