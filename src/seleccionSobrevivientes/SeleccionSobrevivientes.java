package seleccionSobrevivientes;

import java.util.Vector;
import cromosoma.Cromosoma;

public interface SeleccionSobrevivientes {

	public Vector<Cromosoma> seleccionar(Vector<Cromosoma> conjuntoPadres,Vector<Cromosoma> conjuntoHijos);
	
}
