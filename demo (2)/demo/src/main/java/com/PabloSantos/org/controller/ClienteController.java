package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Cliente;
import com.PabloSantos.org.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @GetMapping("/clientes")
    public String irAClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "Cliente";
    }

    @GetMapping("/api/clientes")
    @ResponseBody
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Object> getClienteById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(clienteService.getClienteById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity<Object> saveCliente(@RequestBody Cliente cliente) {
        try {
            return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            clienteService.updateCliente(id, cliente);
            return ResponseEntity.ok("Actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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