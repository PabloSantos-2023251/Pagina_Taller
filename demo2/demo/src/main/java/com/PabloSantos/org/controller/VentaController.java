package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Venta;
import com.PabloSantos.org.entity.Cliente;
import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.service.VentaService;
import com.PabloSantos.org.service.ClienteService;
import com.PabloSantos.org.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public VentaController(VentaService ventaService, ClienteService clienteService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Venta> ventas = ventaService.getAllVentas();

        if (buscar != null && !buscar.isEmpty()) {
            ventas = ventas.stream()
                    .filter(v -> v.getCodigoVenta().toString().contains(buscar) ||
                            (v.getCliente() != null && v.getCliente().getNombre_cliente().toLowerCase().contains(buscar.toLowerCase())) ||
                            (v.getUsuario() != null && v.getUsuario().getUsername().toLowerCase().contains(buscar.toLowerCase())))
                    .collect(Collectors.toList());
        }

        model.addAttribute("ventas", ventas);
        model.addAttribute("ventaObj", new Venta());
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        model.addAttribute("buscar", buscar);
        model.addAttribute("editando", false);
        return "Ventas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta,
                          @RequestParam("clienteId") Integer clienteId,
                          @RequestParam("usuarioId") Integer usuarioId,
                          RedirectAttributes redirectAttrs) {
        try {
            venta.setCliente(clienteService.getClienteById(clienteId));
            venta.setUsuario(usuarioService.getUsuarioById(usuarioId));
            ventaService.saveVenta(venta);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error al guardar la venta: " + e.getMessage());
        }
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        model.addAttribute("ventas", ventaService.getAllVentas());
        model.addAttribute("ventaObj", ventaService.getVentaById(id));
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        model.addAttribute("editando", true);
        return "Ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            ventaService.deleteVenta(id);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "No se puede eliminar la venta.");
        }
        return "redirect:/ventas";
    }
}