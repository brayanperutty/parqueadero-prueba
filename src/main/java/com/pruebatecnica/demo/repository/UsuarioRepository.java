package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM usuario u WHERE u.cedula = :cedula", nativeQuery = true)
    boolean validateCedula(String cedula);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM usuario u WHERE u.correo = :correo", nativeQuery = true)
    boolean validateCorreo(String correo);
}
