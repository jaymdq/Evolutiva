package ui;

import java.util.Vector;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Configuracion;

public class LanzadorDeConfiguraciones implements Runnable {




	private Consola consola;
	private Cronometro cronometro;
	private Thread threadEjecucion;
	private Vector<Configuracion> configuraciones;

	public LanzadorDeConfiguraciones(Consola consola, Cronometro cronometro,
			Thread threadEjecucion, Vector<Configuracion> configuraciones) {

		this.consola = consola;
		this.cronometro = cronometro;
		this.threadEjecucion = threadEjecucion;
		this.configuraciones = configuraciones;

	}

	@Override
	public void run() {

		for (Configuracion c : configuraciones){
			
			consola.limpiar();
			consola.escribirSalto(c.toString());
			consola.escribirSalto(info.configPc());

			AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(c,consola);

			//Mostramos la fecha
			consola.escribirSalto("Comienzo de la ejecución : " + info.getHoraFecha());

			threadEjecucion = new Thread(algoritmo);
			cronometro.empezar();
			threadEjecucion.start();
			
			while (threadEjecucion.isAlive()){
				try {
					Thread.currentThread();
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
			}
			
		}

	}

}
