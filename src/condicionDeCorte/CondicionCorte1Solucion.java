package condicionDeCorte;

import java.util.Vector;

import cromosoma.Cromosoma;

public class CondicionCorte1Solucion extends CondicionCorte {

	public static final String nombre = "Condición de Corte 1 Solución";
	
	@Override
	public boolean corto(Vector<Cromosoma> poblacion) {
		boolean condicion = false;
		for (Cromosoma c : poblacion){
			if ( c.getFitness() == (double) c.getSize()){
				solucion = c.getGenes();
				condicion = true;
				break;
			}
		}
		
		return condicion;
	}

	public Vector<Integer> getSolucion() {
		return solucion;
	}

	
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
	
}
