package ui;

import java.util.Vector;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Configuracion;

public class LanzadorDeConfiguraciones implements Runnable {

	private Consola consola;
	private Cronometro cronometro;
	private Thread threadEjecucion;
	private Vector<Configuracion> configuraciones;
	private boolean activo;

	public LanzadorDeConfiguraciones(Consola consola, Cronometro cronometro,
			Thread threadEjecucion, Vector<Configuracion> configuraciones) {

		this.consola = consola;
		this.cronometro = cronometro;
		this.threadEjecucion = threadEjecucion;
		this.configuraciones = configuraciones;

	}

	@Override
	public void run() {
		this.activo = true;
		Integer i = 1;
		for (Configuracion c : configuraciones){

			if (activo == false)
				break;
			
			System.out.println("Se ejecuta la configuración: " + i);

			consola.limpiar();
			consola.escribirSalto(c.toString());
			//NO BORRAR ESTOO !! 
			if (System.getProperty("file.separator") != "/")
				consola.escribirSalto(info.configPc());

			AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(c,consola);

			//Mostramos la fecha
			consola.escribirSalto("Comienzo de la ejecución : " + info.getHoraFecha());

			threadEjecucion = new Thread(algoritmo);
			cronometro.reiniciar();
			cronometro.empezar();
			threadEjecucion.start();

			do {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			} while (MainAviones.ejecutando);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
						
			i++;
		}
		System.out.println("Termino el lanzador");
		if (activo)
			MainAviones.doClick();
		MainAviones.automatizado = false;
		if (activo)
			MainAviones.calcularEstadisticas();

	}

	public void terminar(){
		this.activo = false;
	}

}
