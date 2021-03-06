package algoritmoGenetico;

import generarPoblacion.GenerarPoblacion;

import java.text.DecimalFormat;
import java.util.Vector;

import ui.Consola;
import ui.MainAviones;
import cromosoma.Cromosoma;

public class AlgoritmoGenetico implements Runnable{

	private Configuracion configuracion;
	private Consola consola;
	private boolean activo;
	private Long iteraciones;

	public AlgoritmoGenetico(Configuracion config,Consola consola) {
		this.configuracion = config;
		this.consola = consola;
	}

	@SuppressWarnings("unchecked")
	public Vector<Integer> getSolucion(){
		//Variables auxiliares
		iteraciones = (long) 0;
		Double mejorFit = 0.0;
		Double peorFit = (double) configuracion.getTamPoblacion();
		Double promFit = 0.0;
		DecimalFormat df = new DecimalFormat("0.00");

		//Generamos la población
		Vector<Cromosoma> poblacion = new Vector<Cromosoma>();
		GenerarPoblacion gen = configuracion.getGenPob();
		poblacion = gen.generar(configuracion.getTamPoblacion(),configuracion.getN());

		for (Cromosoma c : poblacion){
			//Configuracion
			configuracion.getEvaluacion().calcularFitness(c);
			//Estadisticas
			if (c.getFitness() > mejorFit){
				mejorFit = c.getFitness();
			}
			if (c.getFitness() < peorFit){
				peorFit = c.getFitness();
			}
			promFit += c.getFitness();
		}

		//Estadistica
		promFit /= configuracion.getTamPoblacion();

		//System.out.println("1. poblacion inicial : " + poblacion);

		while( ! configuracion.getCondicionCorte().corto(poblacion,iteraciones,configuracion.getGenMax()) && activo ){

			//Estadisticas
			iteraciones++;
			consola.escribirSalto("["+iteraciones+"]\tMejor Fitness ["+df.format(mejorFit)+"]\tFitness Promedio["+df.format(promFit)+"]\tPeor Fitness ["+df.format(peorFit)+"]." );	

			//Se seleccionan los padres y se conforman las parejas
			Vector<Cromosoma> padres = (Vector<Cromosoma>) poblacion.clone();
			Vector<Vector<Cromosoma>> parejas = configuracion.getSeleccionPadres().seleccionar(poblacion);

			//Cruzamos a las parejas
			Vector<Cromosoma> hijos = new Vector<Cromosoma>();

			for (Vector<Cromosoma> pareja : parejas){
				//Vemos si se da la chanche de que se crucen
				if (Math.random() < configuracion.getProbabilidadCruce()){
					hijos.addAll(configuracion.getCruzamiento().cruzar(pareja));	
				}
			}

			//Mutamos a los hijos
			for (Cromosoma c : hijos){
				if ( Math.random() < configuracion.getProbabilidadMutacion())
					configuracion.getMutacion().mutar(c);
			}

			//Calculamos el Fitness de los hijos
			for (Cromosoma c : hijos){
				configuracion.getEvaluacion().calcularFitness(c);
			}

			//Seleccionamos los sobrevivientes
			poblacion = configuracion.getSeleccionSobrevivientes().seleccionar(padres, hijos);

			//Estadisticas
			mejorFit = 0.0;
			peorFit = (double) configuracion.getN();
			promFit = 0.0;
			for (Cromosoma c : poblacion){
				//Estadisticas
				if (c.getFitness() > mejorFit){
					mejorFit = c.getFitness();
				}
				if (c.getFitness() < peorFit){
					peorFit = c.getFitness();
				}
				promFit += c.getFitness();
			}

			//Estadistica
			promFit /= configuracion.getTamPoblacion();

			//Descomentar para ver.
			//Al estar comentado esto el algoritmo va mas rapido
			//System.out.println("Pob> " + poblacion);

		}
		return configuracion.getCondicionCorte().getSolucion();
	}

	@Override
	public void run() {
		MainAviones.ejecutando = true;
		this.activo = true;
		Vector<Integer> solucion;
		solucion = getSolucion();
		Solucion solution;
		if (solucion != null && activo){
			solution = new Solucion(iteraciones,true,null,configuracion);
			MainAviones.addNuevaSolucion(solution);
			MainAviones.dibujarAviones(solucion);
		}else{
			if (activo){
				solution = new Solucion(iteraciones,false,null,configuracion);
				MainAviones.addNuevaSolucion(solution);
				MainAviones.noSeEncontroSolucion(  );
			}
		}
		MainAviones.ejecutando = false;
	}

	public void terminar(){
		this.activo = false;
	}

}
