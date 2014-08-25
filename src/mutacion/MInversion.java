package mutacion;

import java.util.Collections;

import cromosoma.Cromosoma;

public class MInversion implements Mutacion {

	public static final String nombre = "Mutación por Inversión";
	
	@Override
	public void mutar(Cromosoma c) {
		Integer N = c.getSize();
		Integer g1 = (int) (Math.random() * N);
		Integer g2 = (int) (Math.random() * N);

		if ( g1 > g2 ){
			Integer aux = g1;
			g1 = g2;
			g2 = aux;
		}
		Collections.reverse(c.getGenes().subList(g1, g2));
	
	}
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}

}
