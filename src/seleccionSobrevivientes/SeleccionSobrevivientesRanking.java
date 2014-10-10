package seleccionSobrevivientes;

import java.util.Collections;
import java.util.Vector;

import cromosoma.Cromosoma;

public class SeleccionSobrevivientesRanking implements SeleccionSobrevivientes {

	@Override
	public Vector<Cromosoma> seleccionar(Vector<Cromosoma> conjuntoPadres,Vector<Cromosoma> conjuntoHijos) {
		Vector<Cromosoma> total = new Vector<Cromosoma>();
		total.addAll(conjuntoPadres);
		total.addAll(conjuntoHijos);
		Collections.sort(total);
		
		Vector<Double> probs = new Vector<Double>();
		Vector<Cromosoma> salida = new Vector<Cromosoma>();
		
		while (salida.size() != conjuntoPadres.size()) {
			probs.clear();
			for (int i = 0; i < total.size(); i++) {
				probs.add(1.5/total.size() + i/(total.size() * (total.size() - 1)));
			}
			Double rand = Math.random();
			int pos = 0;
			while (probs.get(pos) < rand) {
				rand -= probs.get(pos);
				pos++;
			}
			
			salida.add(total.get(pos));
			total.remove(pos);
		}
		
		return salida;
	}

}
