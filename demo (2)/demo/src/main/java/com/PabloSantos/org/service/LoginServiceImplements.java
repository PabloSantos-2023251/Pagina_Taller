package com.PabloSantos.org.service;

import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginServiceImplements implements LoginService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public Usuario registrar(String usuario, String password) {
        if (repo.findByUsername(usuario) != null) return null;

        Usuario u = new Usuario();
        u.setUsername(usuario);
        u.setPassword(password);
        u.setEmail(usuario + "@tienda.com");
        u.setRol("CLIENTE");
        u.setEstado(1);
        return repo.save(u);
    }

    @Override
    public Usuario login(String usuario, String password) {
        Usuario u = repo.findByUsername(usuario);
        // trim() ayuda si hay espacios en blanco en la DB
        if (u != null && u.getPassword().trim().equals(password.trim())) {
            return u;
        }
        return null;
    }

    @Override
    public List<Usuario> listar() { return repo.findAll(); }

    @Override
    public void eliminar(int id) { repo.deleteById(id); }
}