package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@Builder
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo"})})
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

    @Id
    @Column(nullable = false)
    private String cedula;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "correo", nullable = false)
    private String username;

    @Column(name = "pass", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_parqueadero",
            joinColumns = @JoinColumn(name = "usuario_cedula"),
            inverseJoinColumns = @JoinColumn(name = "parqueadero_id")
    )
    private Set<Parqueadero> parqueaderos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Token> tokens = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((new SimpleGrantedAuthority("ROLE_" + rol.getRol())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cedula, usuario.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
