package com.pruebatecnica.demo;

import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;

	public DemoApplication(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

			if (rolRepository.count() == 0) {
				Rol admin = new Rol(null, "ADMIN");
				Rol socio = new Rol(null, "SOCIO");
				rolRepository.save(admin);
				rolRepository.save(socio);
			}

			if (usuarioRepository.count() == 0) {

				Rol admin = rolRepository.findById(1).orElseThrow(() -> new RuntimeException("Rol de ADMIN no encontrado"));

				Usuario administrador = new Usuario();
				administrador.setCedula("1234567890");
				administrador.setNombreCompleto("Brayan Alexander Perutty Ramirez");
				administrador.setCorreo("admin@mail.com");
				administrador.setPass("admin");
				administrador.setIdRol(admin);
				usuarioRepository.save(administrador);
			}
		};
	}

}
