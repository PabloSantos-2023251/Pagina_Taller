package com.PabloSantos.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/clientes")
    public String irAClientes() {
        return "Cliente";
    }

    @GetMapping("/usuarios")
    public String irAUsuarios() {
        return "Usuario";
    }

    @GetMapping("/productos")
    public String irAproducto() {
        return "productos";
    }

    @GetMapping("/detalles")
    public String irAdetalles() {
        return "Detalles";
    }

    @GetMapping("/ventas")
    public String irVentas() {
        return "Ventas";
    }

    @GetMapping("/logout")
    public String Logout() {
        return "redirect:/";
    }

}