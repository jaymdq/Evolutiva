package generarPoblacion;

import java.util.Vector;

import cromosoma.Cromosoma;

public class GenerarPoblacionRandomSinRepeticiones implements GenerarPoblacion {

	public static final String nombre = "Permutaciones Aleatorias Sin Repeticiones";
	
	@Override
	public Vector<Cromosoma> generar(Integer cantidad, Integer n) {
		Vector<Cromosoma> salida = new Vector<Cromosoma>();
		for (int i = 0; i < cantidad; i++){
			Cromosoma c = new Cromosoma(n);			
			if (!salida.contains(c))
				salida.add(c);
			else
				i--;
		}
		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
