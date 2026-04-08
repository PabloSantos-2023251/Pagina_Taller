package com.PabloSantos.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/clientes")
    public String irAClientes() {
        return "Cliente";
    }

    @GetMapping("/logout")
    public String Logout() {
        return "redirect:/auth";
    }

}