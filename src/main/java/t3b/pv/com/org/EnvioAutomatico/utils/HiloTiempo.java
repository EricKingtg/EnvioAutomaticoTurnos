package t3b.pv.com.org.EnvioAutomatico.utils;

import org.springframework.stereotype.Service;

import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;

@Service
public class HiloTiempo extends Thread {

	private int minutos = 0;
	private int segundos = 0;
	private int minutos_totales = 0;
	private int tiempo_espera = 10;

	FEnvioTurnos frameEnvioTurnos;

	public HiloTiempo() {
	}

	public void init(FEnvioTurnos frame) {
		this.frameEnvioTurnos = frame;
	}

	public void ejecutar() {

		String minutost = "", segundost = "";

		try {
			while (true) {
				if (segundos == 59) {
					segundos = 0;
					minutos++;
					minutos_totales++;
				}
				segundos++;
				// System.out.println("Tiempo " + minutos + ":" + segundos);

				if (minutos < 10) {
					minutost = "0" + String.valueOf(minutos);
				} else {
					minutost = String.valueOf(minutos);
				}
				if (segundos < 10) {
					segundost = "0" + String.valueOf(segundos);
				} else {
					segundost = String.valueOf(segundos);
				}

				frameEnvioTurnos.getlTiempo().setText("Tiempo  " + minutost + ":" + segundost);

				Thread.sleep(1000);

				if (minutos_totales >= tiempo_espera) {
					frameEnvioTurnos.muestraMensajeTiempoExcedido();
					minutos_totales = 0;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		ejecutar();
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public int getMinutos_totales() {
		return minutos_totales;
	}

	public void setMinutos_totales(int minutos_totales) {
		this.minutos_totales = minutos_totales;
	}

}
