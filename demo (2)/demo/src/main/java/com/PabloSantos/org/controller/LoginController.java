package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.service.LoginService;
import com.PabloSantos.org.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String redi() {return "redirect:/usuario"; }

    @GetMapping("/usuario")
    public String login() {
        return "Login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String usuario,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Usuario u = service.login(usuario, password);
        if (u != null) {
            session.setAttribute("usuarioId", u.getCodigoUsuario());
            session.setAttribute("rol", u.getRol());
            session.setAttribute("usuarioLogueado", u.getUsername());
            return "redirect:/home";
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
                          @RequestParam String email,
                          @RequestParam String rol,
                          @RequestParam Integer codigo,
                          Model model) {

        Usuario nuevo = new Usuario();
        nuevo.setCodigoUsuario(codigo);
        nuevo.setUsername(usuario);
        nuevo.setPassword(password);
        nuevo.setEmail(email);
        nuevo.setRol(rol);
        nuevo.setEstado(1); // Activo por defecto

        try {
            usuarioService.saveUsuario(nuevo);
            return "redirect:/usuario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar: El ID o usuario ya existe");
            return "registro";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuario";
    }
}