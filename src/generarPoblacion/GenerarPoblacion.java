package generarPoblacion;

import java.util.Vector;

import cromosoma.Cromosoma;

public interface GenerarPoblacion {

	public Vector<Cromosoma> generar(Integer cantidad,Integer n);
	
}
