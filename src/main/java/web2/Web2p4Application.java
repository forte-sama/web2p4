package web2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Web2p4Application {
	public static void main(String[] args) {
		SpringApplication.run(Web2p4Application.class, args);
	}
}
