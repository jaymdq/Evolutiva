package seleccionPadres;

import java.util.Vector;

import cromosoma.Cromosoma;

public class SeleccionPadreRuleta implements SeleccionPadres {
	
	public static final String nombre = "Selección de Padres por Ruleta";
	
	@Override
	public Vector<Vector<Cromosoma>> seleccionar(Vector<Cromosoma> conjunto) {
		Double total = 0.0;
		for (Cromosoma c: conjunto)
			total += c.getFitness();
		
		Vector<Vector<Cromosoma>> salida = new Vector<Vector<Cromosoma>>();
		
		while (salida.size() != conjunto.size()/2) {
			Vector<Cromosoma> pareja = new Vector<Cromosoma>();
			Double rand = Math.random() * total;
			int pos = 0;
			while (conjunto.get(pos).getFitness() < rand) {
				rand -= conjunto.get(pos).getFitness();
				pos++;
			}
			salida.add(pareja);
		}
		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
