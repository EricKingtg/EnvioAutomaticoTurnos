package t3b.pv.com.org.EnvioAutomatico.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import t3b.pv.cierraturno.dto.TurnoDto;
import t3b.pv.cierraturno.service.DatosService;
import t3b.pv.com.org.EnvioAutomatico.frame.FEnvioTurnos;

@Service
public class HiloEjecucion extends Thread {

	@Autowired
	@Qualifier("datosServiceImpl")
	DatosService datosServiceImpl;

	private FEnvioTurnos frameEnvioTurnos;

	private HiloTiempo tiempo;

	public HiloEjecucion() {
	}

	public HiloEjecucion(FEnvioTurnos frame) {
		this.frameEnvioTurnos = frame;
	}

	public FEnvioTurnos getFrame() {
		return frameEnvioTurnos;
	}

	public void setFrame(FEnvioTurnos frame) {
		this.frameEnvioTurnos = frame;
	}

	public void  init(FEnvioTurnos frame, HiloTiempo tiempo) {
		this.frameEnvioTurnos = frame;
		this.tiempo = tiempo;
		datosServiceImpl.init();
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	public void run() {

		int cantTurnosProcesos = 0;
		List<TurnoDto> turnos = null;

		frameEnvioTurnos.setLocationRelativeTo(null);
		frameEnvioTurnos.setDefaultCloseOperation(frameEnvioTurnos.EXIT_ON_CLOSE);
		frameEnvioTurnos.setVisible(true);

		tiempo.start();

		turnos = datosServiceImpl.consultaTurnosNoEnviadosExt();
		cantTurnosProcesos = (turnos.size() + 1) * 2;
		
		frameEnvioTurnos.getlTienda().setText("Tienda: " + datosServiceImpl.getClave());
		frameEnvioTurnos.getlCaja().setText("Caja: " + datosServiceImpl.getCaja());

		frameEnvioTurnos.getlTienda().setText("Tienda: " + datosServiceImpl.getClave());
		frameEnvioTurnos.getlCaja().setText("Caja: " + datosServiceImpl.getCaja());

		frameEnvioTurnos.getlProceso()
				.setText("Proceso 2 Existen " + String.valueOf(turnos.size()) + " Turnos No enviados");
		frameEnvioTurnos.getjProgressBar1().setValue(cantTurnosProcesos);

		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {

		}

		frameEnvioTurnos.getlProceso()
				.setText("Proceso 3 Comienza envio " + String.valueOf(turnos.size()) + " Turnos No enviados");

		try {
			this.sleep(2000);
		} catch (InterruptedException ex) {
		}

		frameEnvioTurnos.getjProgressBar1()
				.setValue(frameEnvioTurnos.getjProgressBar1().getValue() + cantTurnosProcesos);

		for (TurnoDto dto : turnos) {

			if (dto.activo == 1) {

				frameEnvioTurnos.getlProceso().setText("Proceso Calculando datos Turno PV " + dto.getIdturno());
				// cierra.borraInfoTurno(dto.getIdturno());
				try {
					this.sleep(2000);
				} catch (InterruptedException ex) {
				}

			}

			frameEnvioTurnos.getlProceso().setText("Proceso 3 Borrando datos Turno BOT " + dto.getIdturno());
			datosServiceImpl.borraInfoTurno(dto.getIdturno());
			try {
				this.sleep(2000);
			} catch (InterruptedException ex) {
			}
			frameEnvioTurnos.getjProgressBar1()
					.setValue(frameEnvioTurnos.getjProgressBar1().getValue() + cantTurnosProcesos);
			frameEnvioTurnos.getlProceso().setText("Proceso 3 Enviando datos Turno " + dto.getIdturno());
			datosServiceImpl.procesoCierraTurnoBOT(dto.getIdturno());
			try {
				this.sleep(2000);
			} catch (InterruptedException ex) {
			}
			frameEnvioTurnos.getjProgressBar1()
					.setValue(frameEnvioTurnos.getjProgressBar1().getValue() + cantTurnosProcesos);

		}

		tiempo.stop();

		frameEnvioTurnos.getlProceso().setText("Proceso 4 Turnos Enviados Total: " + turnos.size());
		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {

		}

		frameEnvioTurnos.getjProgressBar1().setValue(100);

		frameEnvioTurnos.getlProceso().setText("Proceso 5 Cierre los Turnos en el sistema BOT por favor ");
		try {
			this.sleep(5000);
		} catch (InterruptedException ex) {
		}
		frameEnvioTurnos.getlProceso().setText("Proceso 6 Cerrando aplicacion");
		try {
			this.sleep(2000);
		} catch (InterruptedException ex) {
		}

		System.exit(0);

	}

}
