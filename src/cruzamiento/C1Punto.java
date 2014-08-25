package cruzamiento;

import java.util.Vector;

import cromosoma.Cromosoma;

public final class C1Punto implements Cruzamiento {

	public static final String nombre = "Cruzamiento en 1 Punto";
	
	@Override
	public Vector<Cromosoma> cruzar(Vector<Cromosoma> padres) {
		Vector<Integer> padre1 = padres.elementAt(0).getGenes();
		Vector<Integer> padre2 = padres.elementAt(1).getGenes();
		Integer N = padre1.size();
		Integer puntoCruce = (int) (Math.random() * N);

		Vector<Integer> hijo1 = new Vector<Integer>();
		Vector<Integer> hijo2 = new Vector<Integer>();
		
		
		hijo1.addAll(padre1.subList(0, puntoCruce));
		hijo2.addAll(padre2.subList(0, puntoCruce));
		
		for (int i = 0; i < N ; i++){
			
			if ( ! hijo1.contains(padre2.elementAt(i))){
				hijo1.add(padre2.elementAt(i));
			}
			if ( ! hijo2.contains(padre1.elementAt(i))){
				hijo2.add(padre1.elementAt(i));
			}
			
		}
		
		Vector<Cromosoma> hijos = new Vector<Cromosoma>();
		hijos.add(new Cromosoma(hijo1));
		hijos.add(new Cromosoma(hijo2));
		
		return hijos;
	}
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}

}
