package co.com.arqsoft.demodecouplingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoDecouplingSpringApplication {

	public static void main(String[] args){

		SpringApplication.run(DemoDecouplingSpringApplication.class, args);

	}

}
