package cruzamiento;

import java.util.Vector;

import cromosoma.Cromosoma;

public class CCiclos implements Cruzamiento {
	
	public static final String nombre = "Cruzamiento basado en ciclos";
	
	@Override
	public Vector<Cromosoma> cruzar(Vector<Cromosoma> padres) {
		Vector<Integer> padre1 = padres.elementAt(0).getGenes();
		Vector<Integer> padre2 = padres.elementAt(1).getGenes();
		
		Integer N = padre1.size();
		
		Vector<Integer> hijo1 = new Vector<Integer>();
		hijo1.addAll(padre1);
		Vector<Integer> hijo2 = new Vector<Integer>();
		hijo2.addAll(padre2);
		
		//Calculo
		int i = 0;
		Vector<Integer> visitados = new Vector<Integer>();
		Vector<Vector<Integer>> ciclos = new Vector<Vector<Integer>>();
		while (visitados.size() < N){
			ciclos.add(getCiclos(i,padre1,padre2));
			visitados.addAll(ciclos.lastElement());
			
			while (visitados.contains(i)){
				i++;
			}
		}
		
		boolean aux = true;
		for (Vector<Integer> ciclo : ciclos){
			if (aux){
				//h1
				for (Integer j : ciclo){
					hijo1.setElementAt(padre1.get(j), j);
					hijo2.setElementAt(padre2.get(j), j);
				}
				
			}else{
				//h2
				for (Integer j : ciclo){
					hijo1.setElementAt(padre2.get(j), j);
					hijo2.setElementAt(padre1.get(j), j);
				}
			}
			aux = !aux;
		}
		
		//Salida
		Vector<Cromosoma> hijos = new Vector<Cromosoma>();
		hijos.add(new Cromosoma(hijo1));
		hijos.add(new Cromosoma(hijo2));
		
		return hijos;
	}

	public Vector<Integer> getCiclos(Integer posInicial,Vector<Integer> p1, Vector<Integer> p2){
		Vector<Integer> salida = new Vector<Integer>();
		
		Integer punto_inicio_final = posInicial;
		Integer punto_actual = punto_inicio_final;
		do{
			salida.add(punto_actual);
			punto_actual = p1.indexOf(p2.elementAt(punto_actual));			
		} while (punto_actual != punto_inicio_final) ;
		
		return salida;
	}
	
	
	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre;
	}
	
}
