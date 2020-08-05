package t3b.pv.com.org.EnvioAutomatico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.context.annotation.ComponentScan;

import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;



@ComponentScan(basePackages = {"t3b.pv.cierraturno.service","t3b.pv.com.org.EnvioAutomatico","t3b.pv.cierraturno.dao","t3b.pv.cierraturno.utils"})
@SpringBootApplication
public class EnvioAutomaticoApplication implements ApplicationRunner {

	@Autowired
	@Qualifier("frameEnvioTurnos")
	private FEnvioTurnos envioTurnos;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(EnvioAutomaticoApplication.class);
		builder.headless(false);
		builder.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		envioTurnos.iniciaProceso();
	}
}