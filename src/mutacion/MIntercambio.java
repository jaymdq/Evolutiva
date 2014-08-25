package mutacion;

import cromosoma.Cromosoma;

public final class MIntercambio implements Mutacion {

	public static final String nombre = "Mutación por Intercambio";
	
	@Override
	public final void mutar(Cromosoma c) {
		Integer N = c.getSize();
		Integer g1 = (int) (Math.random() * N);
		Integer g2 = (int) (Math.random() * N);

		Integer a1 = c.getGen(g1);
		Integer a2 = c.getGen(g2);

		c.setGen(g1, a2);
		c.setGen(g2, a1);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
	
}
