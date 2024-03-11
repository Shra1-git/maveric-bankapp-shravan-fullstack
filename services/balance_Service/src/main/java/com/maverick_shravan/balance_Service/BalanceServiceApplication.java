package com.maverick_shravan.balance_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BalanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceServiceApplication.class, args);
	}

}
