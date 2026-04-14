package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Usuario;
import java.util.List;
public interface LoginService {
    Usuario registrar(String usuario, String password);

    Usuario login(String usuario, String password);

    List<Usuario> listar();

    void eliminar(int id);
}
