package t3b.pv.com.org.EnvioAutomatico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;


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