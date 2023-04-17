package br.com.ifba.giovaneneves.RegistrationProjectWithSpring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationProjectWithSpringApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RegistrationProjectWithSpringApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		System.setProperty("java.awt.headless", "true");
		new br.com.ifba.giovaneneves.RegistrationProjectWithSpring.view.StudentDashboardGUI();
	}
}
