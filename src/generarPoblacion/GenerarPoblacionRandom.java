package generarPoblacion;

import java.util.Vector;

import cromosoma.Cromosoma;

public class GenerarPoblacionRandom implements GenerarPoblacion {

	public static final String nombre = "Permutaciones Aleatorias";
	
	@Override
	public Vector<Cromosoma> generar(Integer cantidad,Integer n) {
		Vector<Cromosoma> salida = new Vector<Cromosoma>();
		for (int i = 0; i < cantidad; i++){
			salida.add(new Cromosoma(n));
		}
		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
