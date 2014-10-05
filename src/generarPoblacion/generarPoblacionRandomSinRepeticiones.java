package generarPoblacion;

import java.util.Vector;

import cromosoma.Cromosoma;

public class generarPoblacionRandomSinRepeticiones implements GenerarPoblacion {

	public static final String nombre = "Permutaciones Aleatorias Sin Repeticiones";
	
	// Corregir esta clase!!
	
	@Override
	public Vector<Cromosoma> generar(Integer cantidad, Integer n) {
		Vector<Cromosoma> salida = new Vector<Cromosoma>();
		for (int i = 0; i < cantidad; i++){
			Cromosoma c = new Cromosoma(n);
			
			if (!salida.contains(c))
				salida.add(c);
			//else
				// Que pasa acá?
		}
		return salida;
	}

	

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
