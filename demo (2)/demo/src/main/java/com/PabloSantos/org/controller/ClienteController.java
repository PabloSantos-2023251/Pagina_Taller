package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Cliente;
import com.PabloSantos.org.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @GetMapping("/clientes")
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Cliente> clientes = clienteService.getAllClientes();

        if (buscar != null && !buscar.isEmpty()) {
            clientes = clientes.stream()
                    .filter(c -> c.getNombre_cliente().toLowerCase().contains(buscar.toLowerCase()) ||
                            c.getDpiCliente().toString().contains(buscar))
                    .collect(Collectors.toList());
        }

        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteObj", new Cliente());
        return "Cliente";
    }

    @PostMapping("/clientes/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("clienteObj", clienteService.getClienteById(id));
        model.addAttribute("editando", true);
        return "Cliente";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }

    @GetMapping("/home")
    public String irAHome() { return "Home"; }

    @GetMapping("/usuarios")
    public String irAUsuarios() { return "Usuario"; }

    @GetMapping("/productos")
    public String irAproducto() { return "productos"; }

    @GetMapping("/detalles")
    public String irAdetalles() { return "Detalles"; }

    @GetMapping("/ventas")
    public String irVentas() { return "Ventas"; }

    @GetMapping("/logout")
    public String Logout() { return "redirect:/"; }
}