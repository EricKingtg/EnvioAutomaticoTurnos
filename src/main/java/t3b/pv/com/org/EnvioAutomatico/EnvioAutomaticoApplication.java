package t3b.pv.com.org.EnvioAutomatico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;

@SpringBootApplication
public class EnvioAutomaticoApplication implements ApplicationRunner {

	@Autowired
	@Qualifier("frameEnvioTurnos")
	FEnvioTurnos frameEnvioTurnos;

	public static void main(String[] args) {
		SpringApplication.run(EnvioAutomaticoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		frameEnvioTurnos.iniciaProceso();
	}
}