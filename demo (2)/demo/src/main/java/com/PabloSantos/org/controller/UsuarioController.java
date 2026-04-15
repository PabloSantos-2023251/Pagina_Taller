package com.PabloSantos.org.controller;

import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public String listar(@RequestParam(required = false) String buscar, Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (buscar != null && !buscar.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u ->
                            u.getUsername().toLowerCase().contains(buscar.toLowerCase()) ||
                                    u.getEmail().toLowerCase().contains(buscar.toLowerCase()) ||
                                    u.getRol().toLowerCase().contains(buscar.toLowerCase()) ||
                                    u.getCodigoUsuario().toString().contains(buscar)
                    )
                    .collect(Collectors.toList());
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioObj", new Usuario());
        model.addAttribute("buscar", buscar);
        return "Usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String precargarEdicion(@PathVariable Integer id, Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        model.addAttribute("usuarioObj", usuarioService.getUsuarioById(id));
        model.addAttribute("editando", true);
        return "Usuario";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        try {
            usuarioService.deleteUsuario(id);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "No se puede eliminar: El usuario tiene registros asociados.");
        }
        return "redirect:/usuarios";
    }
}