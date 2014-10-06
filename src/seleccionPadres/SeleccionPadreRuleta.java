package seleccionPadres;

import java.util.Vector;

import cromosoma.Cromosoma;

public class SeleccionPadreRuleta implements SeleccionPadres {
	
	public static final String nombre = "Selección de Padres por Ruleta";
	
	@Override
	public Vector<Vector<Cromosoma>> seleccionar(Vector<Cromosoma> conjunto) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
