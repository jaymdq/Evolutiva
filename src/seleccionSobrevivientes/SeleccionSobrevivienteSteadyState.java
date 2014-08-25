package seleccionSobrevivientes;

import java.util.Collections;
import java.util.Vector;
import cromosoma.Cromosoma;

public class SeleccionSobrevivienteSteadyState implements SeleccionSobrevivientes {

	public static final String nombre = "Steady-State";
	private Integer n;
	
	public SeleccionSobrevivienteSteadyState(Integer n) {
		this.n = n;
	}

	@Override
	public Vector<Cromosoma> seleccionar(Vector<Cromosoma> conjuntoPadres,Vector<Cromosoma> conjuntoHijos) {
		Vector<Cromosoma> salida = new Vector<Cromosoma>();
		//Ordeno tanto los padres como los hijos por su fitness
		Collections.sort(conjuntoPadres);
		Collections.sort(conjuntoHijos);
		//Me quedo con los hijos con mayor fitness
		Collections.reverse(conjuntoHijos);
		
		//Genero la salida
		int naux = n;
		if (conjuntoHijos.size() >= n){
			salida.addAll(conjuntoHijos.subList(0, n));
		}else{
			salida.addAll(conjuntoHijos);
			naux = conjuntoHijos.size();
		}
		salida.addAll(conjuntoPadres.subList(naux, conjuntoPadres.size()));
			
		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre + " (n = "+this.n+")";
	}

}
