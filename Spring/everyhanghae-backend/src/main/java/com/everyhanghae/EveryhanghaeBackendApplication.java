package com.everyhanghae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling // 스케줄러를 사용하려면 필요합니다!
public class EveryhanghaeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveryhanghaeBackendApplication.class, args);
	}

}
