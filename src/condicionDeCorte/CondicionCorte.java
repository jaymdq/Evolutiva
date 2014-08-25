package condicionDeCorte;

import java.util.Vector;

import cromosoma.Cromosoma;

public abstract class CondicionCorte {

	public abstract boolean corto(Vector<Cromosoma> poblacion);
	protected Vector<Integer> solucion = null;
	
	public abstract Vector<Integer> getSolucion();
	
	public void anularSolucion(){
		solucion.clear();
	}
}
