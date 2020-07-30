package t3b.pv.com.org.EnvioAutomatico.utils;

import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;

public class HiloTiempo extends Thread {

	private int minutos = 0, segundos = 0, minutos_totales = 0, tiempo_espera = 10;
	private FEnvioTurnos frame;

	public HiloTiempo() {
	}

	public HiloTiempo(FEnvioTurnos frame) {
		this.frame = frame;
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

				frame.getlTiempo().setText("Tiempo  " + minutost + ":" + segundost);

				Thread.sleep(1000);

				if (minutos_totales >= tiempo_espera) {
					frame.muestraMensajeTiempoExcedido();
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

	public FEnvioTurnos getFrame() {
		return frame;
	}

	public void setFrame(FEnvioTurnos frame) {
		this.frame = frame;
	}

	public int getMinutos_totales() {
		return minutos_totales;
	}

	public void setMinutos_totales(int minutos_totales) {
		this.minutos_totales = minutos_totales;
	}

}
