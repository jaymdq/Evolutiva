package algoritmoGenetico;

import java.text.DecimalFormat;

import condicionDeCorte.CondicionCorte;
import mutacion.Mutacion;
import cruzamiento.Cruzamiento;
import fitness.Evaluacion;
import generarPoblacion.GenerarPoblacion;
import seleccionPadres.SeleccionPadres;
import seleccionSobrevivientes.SeleccionSobrevivientes;

public class Configuracion {

	private Integer n;
	private GenerarPoblacion genPob;
	private SeleccionPadres seleccionPadres;
	private Cruzamiento cruzamiento;
	private Mutacion mutacion;
	private SeleccionSobrevivientes seleccionSobrevivientes;
	private Integer tamPoblacion;
	private Double probabilidadCruce;
	private Double probabilidadMutacion;
	private CondicionCorte condicionCorte; 
	private Evaluacion evaluacion;
	
	public Configuracion(Integer n,GenerarPoblacion genPob, SeleccionPadres seleccionPadres,
			Cruzamiento cruzamiento, Mutacion mutacion,
			SeleccionSobrevivientes seleccionSobrevivientes,
			Integer tamPoblacion, Double probabilidadCruce,
			Double probabilidadMutacion,CondicionCorte condicion, Evaluacion eval) {
		super();
		this.setN(n);
		this.setGenPob(genPob);
		this.setSeleccionPadres(seleccionPadres);
		this.setCruzamiento(cruzamiento);
		this.setMutacion(mutacion);
		this.setSeleccionSobrevivientes(seleccionSobrevivientes);
		this.setTamPoblacion(tamPoblacion);
		this.setProbabilidadCruce(probabilidadCruce);
		this.setProbabilidadMutacion(probabilidadMutacion);
		this.setCondicionCorte(condicion);
		this.setEvaluacion(eval);
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public SeleccionPadres getSeleccionPadres() {
		return seleccionPadres;
	}

	public void setSeleccionPadres(SeleccionPadres seleccionPadres) {
		this.seleccionPadres = seleccionPadres;
	}

	public Cruzamiento getCruzamiento() {
		return cruzamiento;
	}

	public void setCruzamiento(Cruzamiento cruzamiento) {
		this.cruzamiento = cruzamiento;
	}

	public Mutacion getMutacion() {
		return mutacion;
	}

	public void setMutacion(Mutacion mutacion) {
		this.mutacion = mutacion;
	}

	public SeleccionSobrevivientes getSeleccionSobrevivientes() {
		return seleccionSobrevivientes;
	}

	public void setSeleccionSobrevivientes(SeleccionSobrevivientes seleccionSobrevivientes) {
		this.seleccionSobrevivientes = seleccionSobrevivientes;
	}

	public Integer getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(Integer tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public Double getProbabilidadCruce() {
		return probabilidadCruce;
	}

	public void setProbabilidadCruce(Double probabilidadCruce) {
		this.probabilidadCruce = probabilidadCruce;
	}

	public Double getProbabilidadMutacion() {
		return probabilidadMutacion;
	}

	public void setProbabilidadMutacion(Double probabilidadMutacion) {
		this.probabilidadMutacion = probabilidadMutacion;
	}

	public CondicionCorte getCondicionCorte() {
		return condicionCorte;
	}

	public void setCondicionCorte(CondicionCorte condicionCorte) {
		this.condicionCorte = condicionCorte;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	

	public String toString (){
		DecimalFormat df = new DecimalFormat("0.00");
		String salida = "";
		salida += "Configuraci�n Utilizada: \n";
		salida += "  + Cantidad de Aviones (N) = [" + this.n + "].\n";
		salida += "  + Funci�n de Fitness = [" + this.evaluacion.toString()+"].\n";
		salida += "  + M�todo de Generaci�n de Poblaci�n = [" + this.genPob.toString()+"].\n";
		salida += "  + M�todo de Selecci�n de Padres = [" + this.seleccionPadres.toString()+"].\n";
		salida += "  + Operador de Cruzamiento = [" + this.cruzamiento.toString()+"].\n";
		salida += "  + Operador de Mutaci�n = [" + this.mutacion.toString()+"].\n";
		salida += "  + M�todo de Selecci�n de Sobrevivientes = [" + this.seleccionSobrevivientes.toString()+"].\n";
		salida += "  + Tama�o de la Poblaci�n = [" + this.tamPoblacion+"].\n";
		salida += "  + Probabilidad de Cruce = [" + df.format(this.probabilidadCruce)+"].\n";
		salida += "  + Probabilidad de Mutaci�n = [" + df.format(this.probabilidadMutacion)+"].\n";
		salida += "  + Condici�n de Corte = [" + this.condicionCorte.toString()+"].\n";
		
		
		return salida;
	}

	public GenerarPoblacion getGenPob() {
		return genPob;
	}

	public void setGenPob(GenerarPoblacion genPob) {
		this.genPob = genPob;
	}
	
}
