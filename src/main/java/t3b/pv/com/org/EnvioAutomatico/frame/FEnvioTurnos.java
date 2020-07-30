package t3b.pv.com.org.EnvioAutomatico.frame;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import t3b.pv.cierraturno.service.DatosService;
import t3b.pv.com.org.EnvioAutomatico.utils.HiloEjecucion;
import t3b.pv.com.org.EnvioAutomatico.utils.HiloTiempo;


@Service("frameEnvioTurnos")
public class FEnvioTurnos extends JFrame {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("datosServiceImpl")
	private DatosService inicio;

	public FEnvioTurnos() {
		initComponents();

	}

	public void muestraMensajeTiempoExcedido() {

		int respuesta = 0;

		respuesta = JOptionPane.showConfirmDialog(null,
				"El tiempo de ejecucion a excedido de 10 mins\n¿Desea continuar con la ejecucion de envio de Turnos?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (respuesta == 1) {
			System.exit(0);
		}

	}

	public void iniciaProceso() {

		Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate

		System.out.println(objDate);
		String strDateFormat = "yyyy-MM-dd"; // El formato de fecha está especificado
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un
																		// argumento al objeto
		System.out.println(objSDF.format(objDate)); // El formato de fech

		this.getlFecha().setText("Fecha: " + objSDF.format(objDate));

		HiloTiempo hiloTiempo = new HiloTiempo(this);
		// hiloTiempo.start();
		HiloEjecucion hiloEjecucion = new HiloEjecucion(this, hiloTiempo);
		hiloEjecucion.start();

	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		pbProgreso = new javax.swing.JProgressBar();
		lTienda = new javax.swing.JLabel();
		lCaja = new javax.swing.JLabel();
		lFecha = new javax.swing.JLabel();
		lProceso = new javax.swing.JLabel();
		lTiempo = new javax.swing.JLabel();
		lAviso = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envio de Turnos Automaticos",
				javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

		pbProgreso.setName("pbProgreso"); // NOI18N

		lTienda.setText("Tienda:");
		lTienda.setName("lTienda"); // NOI18N

		lCaja.setText("Caja:");
		lCaja.setName("lCaja"); // NOI18N

		lFecha.setText("Fecha:");
		lFecha.setName("lFecha"); // NOI18N

		lProceso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lProceso.setText("Paso 1 Consultando Retiros no enviados");
		lProceso.setName("lProceso"); // NOI18N

		lTiempo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		lTiempo.setText("Tiempo: 00:00");
		lTiempo.setName("lTiempo"); // NOI18N

		lAviso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		lAviso.setText("Por favor no Apague el PV o cancele el proceso");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(66, 66, 66).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(lProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 391,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(lFecha)
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lTienda).addGap(92, 92, 92)
								.addComponent(lCaja))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(121, 121, 121).addComponent(lTiempo)))
						.addContainerGap(49, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel1Layout.createSequentialGroup()
												.addComponent(pbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 292,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(100, 100, 100))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
										.createSequentialGroup().addComponent(lAviso).addGap(82, 82, 82)))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lTienda).addComponent(lCaja))
								.addGap(18, 18, 18).addComponent(lFecha).addGap(14, 14, 14)
								.addComponent(lProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(lAviso).addGap(13, 13, 13)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(23, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(22, 22, 22)));

		pack();
	}// </editor-fold>

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FEnvioTurnos.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FEnvioTurnos.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FEnvioTurnos.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FEnvioTurnos.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FEnvioTurnos().setVisible(true);
			}
		});
	}

	public JPanel getjPanel1() {
		return jPanel1;
	}

	public void setjPanel1(JPanel jPanel1) {
		this.jPanel1 = jPanel1;
	}

	public JProgressBar getjProgressBar1() {
		return pbProgreso;
	}

	public void setjProgressBar1(JProgressBar jProgressBar1) {
		this.pbProgreso = jProgressBar1;
	}

	public JLabel getlAviso() {
		return lAviso;
	}

	public void setlAviso(JLabel lAviso) {
		this.lAviso = lAviso;
	}

	public JLabel getlCaja() {
		return lCaja;
	}

	public void setlCaja(JLabel lCaja) {
		this.lCaja = lCaja;
	}

	public JLabel getlFecha() {
		return lFecha;
	}

	public void setlFecha(JLabel lFecha) {
		this.lFecha = lFecha;
	}

	public JLabel getlProceso() {
		return lProceso;
	}

	public void setlProceso(JLabel lProceso) {
		this.lProceso = lProceso;
	}

	public JLabel getlTiempo() {
		return lTiempo;
	}

	public void setlTiempo(JLabel lTiempo) {
		this.lTiempo = lTiempo;
	}

	public JLabel getlTienda() {
		return lTienda;
	}

	public void setlTienda(JLabel lTienda) {
		this.lTienda = lTienda;
	}

	public JProgressBar getPbProgreso() {
		return pbProgreso;
	}

	public void setPbProgreso(JProgressBar pbProgreso) {
		this.pbProgreso = pbProgreso;
	}

	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel lAviso;
	private javax.swing.JLabel lCaja;
	private javax.swing.JLabel lFecha;
	private javax.swing.JLabel lProceso;
	private javax.swing.JLabel lTiempo;
	private javax.swing.JLabel lTienda;
	private javax.swing.JProgressBar pbProgreso;
	// End of variables declaration
}
