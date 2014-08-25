package seleccionPadres;

import java.util.Vector;

import cromosoma.Cromosoma;

public interface SeleccionPadres {

	public Vector<Vector<Cromosoma>> seleccionar(Vector<Cromosoma> conjunto);
	
}
