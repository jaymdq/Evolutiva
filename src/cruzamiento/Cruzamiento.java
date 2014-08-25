package cruzamiento;
import cromosoma.Cromosoma;

import java.util.Vector;

public interface Cruzamiento{

	public Vector<Cromosoma> cruzar(Vector<Cromosoma> padres);
	
}
