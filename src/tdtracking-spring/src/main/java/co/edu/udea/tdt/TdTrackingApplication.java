package co.edu.udea.tdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TdTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdTrackingApplication.class, args);
	}
}
