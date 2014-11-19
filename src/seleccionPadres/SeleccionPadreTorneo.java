package seleccionPadres;

import java.util.Collections;
import java.util.Vector;

import cromosoma.Cromosoma;

public class SeleccionPadreTorneo implements SeleccionPadres {

	private Integer k;
	public static final String nombre = "Selección de Padres por Torneo";
	
	
	public SeleccionPadreTorneo(Integer k) {
		this.k = k;
	}

	@Override
	public Vector<Vector<Cromosoma>> seleccionar(Vector<Cromosoma> conjunto) {
		Vector<Vector<Cromosoma>> salida = new Vector<Vector<Cromosoma>>();
		
		//Mientras existan padres para conformar parejas
		while ( ! conjunto.isEmpty() ){
			//Vector que conforma una pareja conformada por dos padres ganadores de dos torneos
			Vector<Cromosoma> pareja = new Vector<Cromosoma>();
			//Vector que conforma los padres participantes de un torneo especifico
			Vector<Cromosoma> torneo = new Vector<Cromosoma>();
			
			//Mezclo el conjunto con la totalidad de padres disponibles
			Collections.shuffle(conjunto);

			if ( conjunto.size() >= k){
				//Se seleccionan k padres para conformar al torneo
				torneo.addAll(conjunto.subList(0, k));
			
			}else{
				//Si ya en el conjunto quedan menos de k padres se los mete junto en el torneo
				torneo.addAll(conjunto);
			}

			//Ordeno el vector torneo para obtener al ganador.
			Collections.sort(torneo);
			//Agrego al ganador como primer padre y lo borro del conjunto.
			pareja.add(torneo.lastElement());
			conjunto.remove(torneo.lastElement());
			//Borro el vector de torneo.
			torneo.clear();
			
			//Mezclo el conjunto de padres sobrantes.
			Collections.shuffle(conjunto);

			if ( conjunto.size() >= k){
				//Se seleccionan k padres para conformar al torneo
				torneo.addAll(conjunto.subList(0, k));
			
			}else{
				//Si ya en el conjunto quedan menos de k padres se los mete junto en el torneo
				torneo.addAll(conjunto);
			}
			
			//Ordeno el vector torneo para obtener al ganador.
			Collections.sort(torneo);
			//Agrego al ganador como segundo padre y lo borro del conjunto.
			pareja.add(torneo.lastElement());
			conjunto.remove(torneo.lastElement());
			
			//Agrego la pareja obtenida
			salida.add(pareja);
		}
		
		return salida;
	}

	@SuppressWarnings("static-access")
	public String toString(){
		return this.nombre + " (k = "+this.k+")";
	}
	
	
}
