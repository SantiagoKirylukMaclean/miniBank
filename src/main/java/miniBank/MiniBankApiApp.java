package miniBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import miniBank.dao.StartConfiguration;



@SpringBootApplication
public class MiniBankApiApp {

	public static void main(String[] args) {
		
		// start H2 Database in memory
		StartConfiguration startConf = new StartConfiguration();
		startConf.startAppConfigurations();
		
		SpringApplication.run(MiniBankApiApp.class, args);
		

		
		

	}

}
