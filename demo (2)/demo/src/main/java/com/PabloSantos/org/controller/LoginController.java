package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/")
    public String redi() {return "redirect:/usuario"; }

    @GetMapping("/usuario")
    public String login() {
        return "Login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String usuario,
                          @RequestParam String password,
                          Model model) {
        Usuario u = service.login(usuario, password);
        if (u != null) {
            return "Home";
        }
        model.addAttribute("error", "Credenciales incorrectas");
        return "Login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam String usuario,
                          @RequestParam String password,
                          Model model) {
        Usuario u = service.registrar(usuario, password);
        if (u == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "registro";
        }
        return "redirect:/usuario";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        service.eliminar(id);
        return "redirect:/lista";
    }
}