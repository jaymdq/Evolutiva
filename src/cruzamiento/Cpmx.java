package cruzamiento;

import java.util.Vector;

import cromosoma.Cromosoma;

public class Cpmx implements Cruzamiento {

	public static final String nombre = "Cruzamiento PMX";

	@Override
	public Vector<Cromosoma> cruzar(Vector<Cromosoma> padres) {
		Vector<Integer> padre1 = padres.elementAt(0).getGenes();
		Vector<Integer> padre2 = padres.elementAt(1).getGenes();

		Integer N = padre1.size();

		Integer puntoCruce1 = (int) (Math.random() * N);
		Integer puntoCruce2 ;
		do{
			puntoCruce2 = (int) (Math.random() * N);
		} while(puntoCruce2 == puntoCruce1);

		if (puntoCruce1 > puntoCruce2){
			Integer aux = puntoCruce1;
			puntoCruce1 = puntoCruce2;
			puntoCruce2 = aux;
		}

		/*System.out.println(padre1);
		System.out.println(padre2);
		System.out.println("Punto inf " + puntoCruce1);
		System.out.println("Punto sup " + puntoCruce2);	
		 */
		Vector<Integer> hijo1 = new Vector<Integer>();
		Vector<Integer> hijo2 = new Vector<Integer>();
		for (int i = 0; i < N ; i++){
			if (puntoCruce1 <= i && i <= puntoCruce2){
				hijo1.add(padre2.elementAt(i));
				hijo2.add(padre1.elementAt(i));
			}else{
				hijo1.add(-1);
				hijo2.add(-1);
			}	
		}

		//System.out.println("\n" + hijo1);
		//System.out.println(hijo2);

		//Se rellena el hijo 1
		for (int i = 0; i < puntoCruce1 ; i++){
			if (hijo1.contains(padre1.elementAt(i))){
				//Se busca la correspondencia
				Integer punto_actual = hijo1.indexOf(padre1.elementAt(i));
				Integer elementoSalida = null ;
				while (puntoCruce1 <= punto_actual && punto_actual <= puntoCruce2){
					elementoSalida = padre1.elementAt(punto_actual);
					punto_actual = hijo1.indexOf(elementoSalida);
				}
				hijo1.setElementAt(elementoSalida, i);	
			}else{
				//Se copia
				hijo1.setElementAt(padre1.elementAt(i), i);
			}
		}

		for (int i = puntoCruce2 + 1; i < N ; i++){
			if (hijo1.contains(padre1.elementAt(i))){
				//Se busca la correspondencia
				Integer punto_actual = hijo1.indexOf(padre1.elementAt(i));
				Integer elementoSalida = null ;
				while (puntoCruce1 <= punto_actual && punto_actual <= puntoCruce2){
					elementoSalida = padre1.elementAt(punto_actual);
					punto_actual = hijo1.indexOf(elementoSalida);
				}
				hijo1.setElementAt(elementoSalida, i);	
			}else{
				//Se copia
				hijo1.setElementAt(padre1.elementAt(i), i);
			}
		}


		//Se rellena el hijo 2
		for (int i = 0; i < puntoCruce1 ; i++){
			if (hijo2.contains(padre2.elementAt(i))){
				//Se busca la correspondencia
				Integer punto_actual = hijo2.indexOf(padre2.elementAt(i));
				Integer elementoSalida = null ;
				while (puntoCruce1 <= punto_actual && punto_actual <= puntoCruce2){
					elementoSalida = padre2.elementAt(punto_actual);
					punto_actual = hijo2.indexOf(elementoSalida);
				}
				hijo2.setElementAt(elementoSalida, i);	
			}else{
				//Se copia
				hijo2.setElementAt(padre2.elementAt(i), i);
			}
		}

		for (int i = puntoCruce2 + 1; i < N ; i++){
			if (hijo2.contains(padre2.elementAt(i))){
				//Se busca la correspondencia
				Integer punto_actual = hijo2.indexOf(padre2.elementAt(i));
				Integer elementoSalida = null ;
				while (puntoCruce1 <= punto_actual && punto_actual <= puntoCruce2){
					elementoSalida = padre2.elementAt(punto_actual);
					punto_actual = hijo2.indexOf(elementoSalida);
				}
				hijo2.setElementAt(elementoSalida, i);	
			}else{
				//Se copia
				hijo2.setElementAt(padre2.elementAt(i), i);
			}
		}


		//	System.out.println("Ahora \n" + hijo1);
		//	System.out.println(hijo2);

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
