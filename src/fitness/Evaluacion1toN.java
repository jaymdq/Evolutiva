package fitness;

import cromosoma.Cromosoma;

public final class Evaluacion1toN implements Evaluacion {

	public static final String nombre = "Evaluación 1 a N";
	
	@Override
	public final void calcularFitness(Cromosoma c) {
		Integer N = c.getSize();
		Double resultado = (double) N;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if ( j != i && inConflict(c,j,i)){
					resultado -= (double) 1/N; 
				}
			}
		}
		c.setFitness(resultado);
	}
	
	private boolean inConflict (Cromosoma c, Integer g1, Integer g2){
		return Math.abs(c.getGen(g1) - c.getGen(g2)) == Math.abs(g1 - g2);
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
	
}
