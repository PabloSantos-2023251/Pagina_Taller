package com.PabloSantos.org.service;
import com.PabloSantos.org.entity.Cliente;
import com.PabloSantos.org.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImplements implements ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteServiceImplements(ClienteRepository repo) { this.clienteRepository = repo; }
    @Override public List<Cliente> getAllClientes() { return clienteRepository.findAll(); }
    @Override public Cliente getClienteById(Integer id) { return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")); }
    @Override public Cliente saveCliente(Cliente c) { return clienteRepository.save(c); }
    @Override public Cliente updateCliente(Integer id, Cliente c) { c.setDpiCliente(id); return clienteRepository.save(c); }
    @Override public void deleteCliente(Integer id) { clienteRepository.deleteById(id); }
}