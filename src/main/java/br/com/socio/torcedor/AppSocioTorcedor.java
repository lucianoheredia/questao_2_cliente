package br.com.socio.torcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("br.com.socio.*")
public class AppSocioTorcedor {
	
	public static void main(String[] args) {
		SpringApplication.run(AppSocioTorcedor.class, args);
    }

}
