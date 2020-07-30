package t3b.pv.com.org.EnvioAutomatico.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import t3b.pv.cierraturno.dto.TurnoDto;
import t3b.pv.cierraturno.service.DatosService;
import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;

public class HiloEjecucion extends Thread {

	@Autowired
	@Qualifier("datosServiceImpl")
	private DatosService cierra;

	private FEnvioTurnos frame;
	private HiloTiempo tiempo;

	public HiloEjecucion() {

	}

	public HiloEjecucion(FEnvioTurnos frame) {
		this.frame = frame;
	}

	public FEnvioTurnos getFrame() {
		return frame;
	}

	public void setFrame(FEnvioTurnos frame) {
		this.frame = frame;
	}

	public HiloEjecucion(FEnvioTurnos frame, HiloTiempo tiempo) {
		this.frame = frame;
		this.tiempo = tiempo;
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	public void run() {

		int cantTurnosProcesos = 0;
		List<TurnoDto> turnos = null;

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		tiempo.start();

		turnos = cierra.consultaTurnosNoEnviadosExt();
		cantTurnosProcesos = (turnos.size() + 1) * 2;

		frame.getlTienda().setText("Tienda: " + cierra.getClave());
		frame.getlCaja().setText("Caja: " + cierra.getCaja());

		frame.getlProceso().setText("Proceso 2 Existen " + String.valueOf(turnos.size()) + " Turnos No enviados");
		frame.getjProgressBar1().setValue(cantTurnosProcesos);

		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {

		}

		frame.getlProceso()
				.setText("Proceso 3 Comienza envio " + String.valueOf(turnos.size()) + " Turnos No enviados");

		try {
			this.sleep(2000);
		} catch (InterruptedException ex) {
		}

		frame.getjProgressBar1().setValue(frame.getjProgressBar1().getValue() + cantTurnosProcesos);

		for (TurnoDto dto : turnos) {

			if (dto.activo == 1) {

				frame.getlProceso().setText("Proceso Calculando datos Turno PV " + dto.getIdturno());
				// cierra.borraInfoTurno(dto.getIdturno());
				try {
					this.sleep(2000);
				} catch (InterruptedException ex) {
				}

			}

			frame.getlProceso().setText("Proceso 3 Borrando datos Turno BOT " + dto.getIdturno());
			cierra.borraInfoTurno(dto.getIdturno());
			try {
				this.sleep(2000);
			} catch (InterruptedException ex) {
			}
			frame.getjProgressBar1().setValue(frame.getjProgressBar1().getValue() + cantTurnosProcesos);
			frame.getlProceso().setText("Proceso 3 Enviando datos Turno " + dto.getIdturno());
			cierra.procesoCierraTurnoBOT(dto.getIdturno());
			try {
				this.sleep(2000);
			} catch (InterruptedException ex) {
			}
			frame.getjProgressBar1().setValue(frame.getjProgressBar1().getValue() + cantTurnosProcesos);

		}

		tiempo.stop();

		frame.getlProceso().setText("Proceso 4 Turnos Enviados Total: " + turnos.size());
		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {

		}

		frame.getjProgressBar1().setValue(100);

		frame.getlProceso().setText("Proceso 5 Cierre los Turnos en el sistema BOT por favor ");
		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {
		}
		frame.getlProceso().setText("Proceso 6 Cerrando aplicacion");
		try {
			this.sleep(2000);
		} catch (InterruptedException ex) {
		}

		System.exit(0);

	}

}
