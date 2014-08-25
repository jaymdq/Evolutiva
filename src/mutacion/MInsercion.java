package mutacion;

import cromosoma.Cromosoma;

public class MInsercion implements Mutacion {

	public static final String nombre = "Mutación por Inserción";
	
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
		
		c.getGenes().insertElementAt(c.getGen(g2), g1 + 1);
		c.getGenes().removeElementAt(g2+1);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
}
