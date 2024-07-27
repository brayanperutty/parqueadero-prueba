package com.pruebatecnica.demo;

import com.pruebatecnica.demo.auth.AuthController;
import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.auth.RegisterSocioRequest;
import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;

	private final AuthController authController;

	public DemoApplication(UsuarioRepository usuarioRepository, RolRepository rolRepository, AuthController authController) {
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
		this.authController = authController;
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

				RegisterSocioRequest administrador = new RegisterSocioRequest();
				administrador.setCedula("1234567890");
				administrador.setNombreCompleto("Brayan Alexander Perutty Ramirez");
				administrador.setUsername("admin@mail.com");
				administrador.setPassword("admin");
				administrador.setRol(1);

				authController.register(administrador);
			}
		};
	}

}
