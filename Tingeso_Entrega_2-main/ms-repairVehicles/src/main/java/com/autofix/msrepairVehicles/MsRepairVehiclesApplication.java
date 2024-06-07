package com.autofix.msrepairVehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRepairVehiclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRepairVehiclesApplication.class, args);
	}

}
