package ui;

import fitness.Evaluacion;
import fitness.Evaluacion1toN;
import generarPoblacion.GenerarPoblacion;
import generarPoblacion.GenerarPoblacionRandom;
import generarPoblacion.GenerarPoblacionRandomSinRepeticiones;

import java.util.Vector;

import condicionDeCorte.CondicionCorte;
import condicionDeCorte.CondicionCorte1Solucion;
import mutacion.MInsercion;
import mutacion.MIntercambio;
import mutacion.MInversion;
import mutacion.MMezcla;
import mutacion.Mutacion;
import seleccionPadres.SeleccionPadreRuleta;
import seleccionPadres.SeleccionPadreTorneo;
import seleccionPadres.SeleccionPadres;
import seleccionSobrevivientes.SeleccionSobrevivienteSteadyState;
import seleccionSobrevivientes.SeleccionSobrevivientes;
import seleccionSobrevivientes.SeleccionSobrevivientesRanking;
import cruzamiento.C1Punto;
import cruzamiento.CCiclos;
import cruzamiento.Cpmx;
import cruzamiento.Cruzamiento;
import algoritmoGenetico.Configuracion;

public class ConfiguracionAutomatizada {

	private Integer nAviones;
	private Integer tamPob;
	private Double probCru;
	private Double probMut;
	private Long genMax;
	private Integer nn;
	private Integer k;

	public ConfiguracionAutomatizada(Integer nAviones, Integer tamPob,
			Double probCru, Double probMut, Long genMax, Integer nn, Integer k) {
		this.nAviones = nAviones;
		this.tamPob = tamPob;
		this.probCru = probCru;
		this.probMut = probMut;
		this.genMax = genMax;
		this.nn = nn;
		this.k = k;
	}

	public Vector<Configuracion> getConfiguraciones() {
		Vector<Configuracion> salida = new Vector<Configuracion>();

		CondicionCorte condicion = new CondicionCorte1Solucion();
		Evaluacion eval = new Evaluacion1toN();

		for (int metodoGeneracion = 0; metodoGeneracion <= 1; metodoGeneracion++) {
			for (int metodoSeleccion = 0; metodoSeleccion <= 1; metodoSeleccion++) {
				for (int operadorCruce = 0; operadorCruce <= 2; operadorCruce++) {
					for (int operadorMutacion = 0; operadorMutacion <= 3; operadorMutacion++) {
						for (int metodoSobrevivientes = 0; metodoSobrevivientes <= 1; metodoSobrevivientes++) {

							GenerarPoblacion genPob = null;
							switch (metodoGeneracion) {
							case 0:
								genPob = new GenerarPoblacionRandom();
								break;
							case 1:
								genPob = new GenerarPoblacionRandomSinRepeticiones();
								break;
							}

							SeleccionPadres selPad = null;
							switch (metodoSeleccion) {
							case 0:
								selPad = new SeleccionPadreTorneo(k);
								break;
							case 1:
								selPad = new SeleccionPadreRuleta();
								break;
							}

							Cruzamiento cruza = null;
							switch (operadorCruce) {
							case 0:
								cruza = new C1Punto();
								break;
							case 1:
								cruza = new CCiclos();
								break;
							case 2:
								cruza = new Cpmx();
								break;
							}

							Mutacion mutacion = null;
							switch (operadorMutacion) {
							case 0:
								mutacion = new MIntercambio();
								break;
							case 1:
								mutacion = new MInversion();
								break;
							case 2:
								mutacion = new MMezcla();
								break;
							case 3:
								mutacion = new MInsercion();
								break;
							}

							SeleccionSobrevivientes selSob = null;
							switch (metodoSobrevivientes) {
							case 0:
								selSob = new SeleccionSobrevivienteSteadyState(
										nn);
								break;
							case 1:
								selSob = new SeleccionSobrevivientesRanking();
								break;
							}

							// Se hace la configuracion
							Configuracion config = new Configuracion(nAviones,
									genPob, selPad, cruza, mutacion, selSob,
									tamPob, probCru, probMut, condicion, eval,
									genMax);
							
							//Se agregan varias veces a proposito
							for (int i = 0; i < 5; i++)
								salida.add(config);
						}
					}
				}
			}
		}

		return salida;
	}

	public Integer getnAviones() {
		return nAviones;
	}

	public void setnAviones(Integer nAviones) {
		this.nAviones = nAviones;
	}

	public Integer getTamPob() {
		return tamPob;
	}

	public void setTamPob(Integer tamPob) {
		this.tamPob = tamPob;
	}

	public Double getProbCru() {
		return probCru;
	}

	public void setProbCru(Double probCru) {
		this.probCru = probCru;
	}

	public Double getProbMut() {
		return probMut;
	}

	public void setProbMut(Double probMut) {
		this.probMut = probMut;
	}

	public Long getGenMax() {
		return genMax;
	}

	public void setGenMax(Long genMax) {
		this.genMax = genMax;
	}

}
