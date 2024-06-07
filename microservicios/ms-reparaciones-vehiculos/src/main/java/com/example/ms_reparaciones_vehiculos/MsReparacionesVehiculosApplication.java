package com.example.ms_reparaciones_vehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsReparacionesVehiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReparacionesVehiculosApplication.class, args);
	}

}
