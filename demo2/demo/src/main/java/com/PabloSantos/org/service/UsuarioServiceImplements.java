package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Usuario;
import com.PabloSantos.org.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioServiceImplements(UsuarioRepository repo) { this.usuarioRepository = repo; }

    @Override public List<Usuario> getAllUsuarios() { return usuarioRepository.findAll(); }
    @Override public Usuario getUsuarioById(Integer id) { return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")); }
    @Override public Usuario saveUsuario(Usuario u) { return usuarioRepository.save(u); }
    @Override public Usuario updateUsuario(Integer id, Usuario u) {
        u.setCodigoUsuario(id);
        return usuarioRepository.save(u);
    }
    @Override public void deleteUsuario(Integer id) { usuarioRepository.deleteById(id); }
}