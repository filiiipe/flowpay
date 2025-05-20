package com.fluxo.flowpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
public class FlowpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowpayApplication.class, args);
	}

}
