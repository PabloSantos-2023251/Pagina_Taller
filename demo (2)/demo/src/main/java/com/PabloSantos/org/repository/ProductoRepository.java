package com.PabloSantos.org.repository;
import com.PabloSantos.org.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {}