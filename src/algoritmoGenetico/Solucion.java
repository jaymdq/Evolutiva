package algoritmoGenetico;

public class Solucion {

	private Long iteraciones;
	private boolean encontroSolucion;
	private Long tiempo;
	private Configuracion config;
	
	public Solucion(Long iteraciones, boolean encontroSolucion, Long tiempo,
			Configuracion config) {
		super();
		this.iteraciones = iteraciones;
		this.encontroSolucion = encontroSolucion;
		this.tiempo = tiempo;
		this.config = config;
	}
	
	public Long getIteraciones() {
		return iteraciones;
	}
	public void setIteraciones(Long iteraciones) {
		this.iteraciones = iteraciones;
	}
	public boolean EncontroSolucion() {
		return encontroSolucion;
	}
	public void setEncontroSolucion(boolean encontroSolucion) {
		this.encontroSolucion = encontroSolucion;
	}
	public Long getTiempo() {
		return tiempo;
	}
	public void setTiempo(Long tiempo) {
		this.tiempo = tiempo;
	}
	public Configuracion getConfig() {
		return config;
	}
	public void setConfig(Configuracion config) {
		this.config = config;
	}
	
	
}
