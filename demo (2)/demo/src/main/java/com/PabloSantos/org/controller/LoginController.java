package com.PabloSantos.org.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String inicio() {
        return "redirect:/auth";
    }

    @GetMapping("/auth")
    public String mostrarLogin() {
        return "Login";
    }

    // Procesa el formulario de Login
    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        String userCorrecto = "admin";
        String passCorrecto = "1234";

        if (usuario.equals(userCorrecto) && password.equals(passCorrecto)) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "Login";
        }
    }


    @GetMapping("/home")
    public String mostrarHome(HttpSession session) {
        // Protección de ruta: si no hay sesión, al login
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/auth";
        }
        return "Home"; // Busca templates/Home.html
    }
}