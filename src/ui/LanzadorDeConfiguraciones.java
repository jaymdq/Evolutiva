package ui;

import java.util.Vector;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Configuracion;

public class LanzadorDeConfiguraciones implements Runnable {




	private Consola consola;
	private Cronometro cronometro;
	private Thread threadEjecucion;
	private Vector<Configuracion> configuraciones;
	private MainWindow main;

	public LanzadorDeConfiguraciones(MainWindow main,Consola consola, Cronometro cronometro,
			Thread threadEjecucion, Vector<Configuracion> configuraciones) {

		this.consola = consola;
		this.cronometro = cronometro;
		this.threadEjecucion = threadEjecucion;
		this.configuraciones = configuraciones;
		this.main = main;

	}

	@Override
	public void run() {

	Integer i = 1;
		for (Configuracion c : configuraciones){
			
			System.out.println("Se ejecuta la configuración: " + i);
			
			consola.limpiar();
			consola.escribirSalto(c.toString());
			consola.escribirSalto(info.configPc());

			AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(c,consola);

			//Mostramos la fecha
			consola.escribirSalto("Comienzo de la ejecución : " + info.getHoraFecha());

			threadEjecucion = new Thread(algoritmo);
			cronometro.empezar();
			threadEjecucion.start();
			
			do {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
			} while (MainWindow.ejecutando);
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}

			i++;
		}
		System.out.println("Termino el lanzador");
		MainWindow.automatizado = false;

	}

}
