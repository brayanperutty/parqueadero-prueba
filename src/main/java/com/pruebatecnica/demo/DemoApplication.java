package com.pruebatecnica.demo;

import com.pruebatecnica.demo.auth.AuthController;
import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.auth.RegisterSocioRequest;
import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.TipoVehiculo;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.TipoVehiculoRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;

	private final TipoVehiculoRepository tipoVehiculoRepository;

	private final AuthController authController;

	public DemoApplication(UsuarioRepository usuarioRepository, RolRepository rolRepository, AuthController authController, TipoVehiculoRepository tipoVehiculoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
		this.authController = authController;
		this.tipoVehiculoRepository = tipoVehiculoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {

			if(rolRepository.count() == 0) {
				Rol admin = new Rol(null, "ADMIN");
				Rol socio = new Rol(null, "SOCIO");
				rolRepository.save(admin);
				rolRepository.save(socio);
			}

			if(tipoVehiculoRepository.count() == 0){
				TipoVehiculo moto = TipoVehiculo.builder()
						.tipo("MOTO")
						.build();
				TipoVehiculo carro = TipoVehiculo.builder()
						.tipo("CARRO")
						.build();
				tipoVehiculoRepository.save(moto);
				tipoVehiculoRepository.save(carro);
			}

			if(usuarioRepository.count() == 0) {

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
