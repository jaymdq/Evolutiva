package algoritmoGenetico;

public class SolucionAlgoritmo implements Comparable<SolucionAlgoritmo> {

	private Double iteraciones;
	private Double tiempo;
	private Configuracion config;
	private Double efectividad;
	
	
	public SolucionAlgoritmo(Double iteracionesProm, Double tiempoProm,
			Configuracion config, Double efectividad) {
		super();
		this.iteraciones = iteracionesProm;
		this.tiempo = tiempoProm;
		this.config = config;
		this.efectividad = efectividad;
	}

	@Override
	public int compareTo(SolucionAlgoritmo arg0) {
		
		return 0;
	}

	public Double getIteraciones() {
		return iteraciones;
	}

	public void setIteraciones(Double iteraciones) {
		this.iteraciones = iteraciones;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	public Configuracion getConfig() {
		return config;
	}

	public void setConfig(Configuracion config) {
		this.config = config;
	}

	public Double getEfectividad() {
		return efectividad;
	}

	public void setEfectividad(Double efectividad) {
		this.efectividad = efectividad;
	}

	public String toString(){
		String salida = "";
		
		salida += getConfig() + "\n";
		salida += "# Efectividad: " + (int) (getEfectividad() * 100) + "%\n";
		salida += "# Iteraciones promedio: " + getIteraciones() + "\n";
		salida += "# Tiempo promedio: " + getTiempo() + "ms\n";
		return salida;
	}
	
	
}
