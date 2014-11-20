package algoritmoGenetico;

import java.util.Comparator;

public class ComparadorSoluciones implements Comparator<SolucionAlgoritmo> {

	private ComparadorSoluciones sig;
	private String id;
	
	public ComparadorSoluciones(ComparadorSoluciones sig,String id) {
		super();
		this.sig = sig;
		this.id = id;
	}

	@Override
	public int compare(SolucionAlgoritmo arg0, SolucionAlgoritmo arg1) {
		int res = 0;
		if (id.equals("Tiempo"))
			res = arg0.getTiempo().compareTo(arg1.getTiempo());
		else
		if (id.equals("Iteraciones"))
			res = arg0.getIteraciones().compareTo(arg1.getIteraciones());
		else
		if (id.equals("Efectividad"))
			res = arg0.getEfectividad().compareTo(arg1.getEfectividad());
		
		if (res == 0 && sig != null)
			return sig.compare(arg0,arg1);
		else
			return res;
	}

}
