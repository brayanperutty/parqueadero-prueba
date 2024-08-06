package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM rol r WHERE r.id_rol = :idRol", nativeQuery = true)
    boolean validateRol(Integer idRol);
}
