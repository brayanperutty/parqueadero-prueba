package com.pruebatecnica.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSocioRequest {

    private String cedula;
    private String nombreCompleto;
    private String username;
    private String password;
    private Integer rol;
}
