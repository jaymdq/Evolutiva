package ui;

import fitness.Evaluacion;
import fitness.Evaluacion1toN;
import generarPoblacion.GenerarPoblacion;
import generarPoblacion.GenerarPoblacionRandom;
import generarPoblacion.GenerarPoblacionRandomSinRepeticiones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import algoritmoGenetico.Configuracion;
import algoritmoGenetico.ConfiguracionAutomatizada;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.SingleAlignPanel;
import com.alee.extended.window.PopOverDirection;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.spinner.WebSpinner;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import condicionDeCorte.CondicionCorte;
import condicionDeCorte.CondicionCorte1Solucion;
import cruzamiento.C1Punto;
import cruzamiento.CCiclos;
import cruzamiento.Cpmx;
import cruzamiento.Cruzamiento;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

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

import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;


public class DialogConfiguracion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainAviones main;
	private WebSpinner aviones;
	private WebSpinner sizePoblacion;
	private WebSpinner probCruce;
	private WebSpinner probMutacion;
	private WebComboBox metodosSeleccion;
	private WebSpinner k;
	private WebComboBox operadoresCruce;
	private WebComboBox operadoresMutacion;
	private WebComboBox metodosSobrevivientes;
	private WebSpinner nn;
	private WebComboBox cortes;
	private WebComboBox fit;
	private JLabel lblK;
	private JLabel lblN;
	private WebComboBox metodosGeneracion;
	private JButton botonAceptar;
	private JLabel lblGeneracinMximaPermitida;
	private WebSpinner generacionesMaximas;
	private JLabel lblNewLabel_2;
	private JButton btnConfiguracinAutomatizada;
	
	@SuppressWarnings("static-access")
	public DialogConfiguracion(MainAviones mainWindow) {
		super(mainWindow.frame, "Configuración", ModalityType.APPLICATION_MODAL);
		main = mainWindow;
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogConfiguracion.class.getResource("/imagenes/Avion.png")));
		setBounds(100, 100, 1000, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panelConfig = new JPanel();
		panelConfig.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelConfig, BorderLayout.CENTER);
		panelConfig.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("65dlu"),
				ColumnSpec.decode("max(40dlu;default)"),
				ColumnSpec.decode("max(100dlu;default)"),
				ColumnSpec.decode("max(90dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNmeroDeAviones = new JLabel("N\u00FAmero de aviones");
		lblNmeroDeAviones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblNmeroDeAviones, "2, 2, left, default");
		
		aviones = new WebSpinner ();
		aviones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aviones.setModel(new SpinnerNumberModel(8, 4, 10000, 1));
		aviones.setValue(8);
		panelConfig.add(aviones, "6, 2");
        
        JLabel lblFuncinFitness = new JLabel("Funci\u00F3n Fitness");
        lblFuncinFitness.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelConfig.add(lblFuncinFitness, "2, 4");
		
      //Función de Fitness
        String[] funfit = new String[1];
        funfit[0] = Evaluacion1toN.nombre;
		fit = new WebComboBox ( funfit );
		fit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(fit, "6, 4, 2, 1");
        
        JLabel lblMtodoDeGeneracin = new JLabel("M\u00E9todo de Generaci\u00F3n de Poblaci\u00F3n Inicial");
        lblMtodoDeGeneracin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelConfig.add(lblMtodoDeGeneracin, "2, 6, 4, 1");
        
        //Métodos de generación de la población inicial.
        String[] metodos1 = new String[2];
        metodos1[0] = GenerarPoblacionRandom.nombre;
        metodos1[1] = GenerarPoblacionRandomSinRepeticiones.nombre;
        metodosGeneracion = new WebComboBox ( metodos1 );
		metodosGeneracion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(metodosGeneracion, "6, 6, 2, 1");
		
		Label label = new Label("M\u00E9todo de Selecci\u00F3n de Padres");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(label, "2, 8, 4, 1");
		
		//Métodos de selección de padres.
	    String[] metodos2 = new String[2];
	    metodos2[0] = SeleccionPadreTorneo.nombre;
	    metodos2[1] = SeleccionPadreRuleta.nombre;
        metodosSeleccion = new WebComboBox ( metodos2 );
        metodosSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        metodosSeleccion.addItemListener( new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
                if (metodosSeleccion.getSelectedIndex() != 0){
                	lblK.setVisible(false);
                	k.setVisible(false);
                }else{
                	lblK.setVisible(true);
                	k.setVisible(true);
                }
                	
            }	
        });
		panelConfig.add(metodosSeleccion, "6, 8, 2, 1");
		
		lblK = new JLabel("k = ");
		lblK.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panelConfig.add(lblK, "9, 8");
		
		k = new WebSpinner ();
		k.setFont(new Font("Tahoma", Font.PLAIN, 14));
		k.setModel(new SpinnerNumberModel(2, 2, 10000, 1));
		k.setValue(2);
		panelConfig.add(k, "11, 8, default, center");
		
		JLabel lblNewLabel = new JLabel("Operador de Cruce Gen\u00E9tico");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblNewLabel, "2, 10, 3, 1");
		
		//Operadores de cruce
	    String[] operadores1 = new String[3];
	    operadores1[0] = C1Punto.nombre;
	    operadores1[1] = CCiclos.nombre;
	    operadores1[2] = Cpmx.nombre;
        operadoresCruce = new WebComboBox ( operadores1 );
        operadoresCruce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(operadoresCruce, "6, 10, 2, 1");
		
		JLabel lblOperadorDeMutacin = new JLabel("Operador de Mutaci\u00F3n Gen\u00E9tica");
		lblOperadorDeMutacin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblOperadorDeMutacin, "2, 12, 3, 1");
		
		//Operadores de mutación
	    String[] operadores2 = new String[4];
	    operadores2[0] = MIntercambio.nombre;
	    operadores2[1] = MInversion.nombre;
	    operadores2[2] = MMezcla.nombre;
	    operadores2[3] = MInsercion.nombre;
        operadoresMutacion = new WebComboBox ( operadores2 );
        operadoresMutacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(operadoresMutacion, "6, 12, 2, 1");
		
		JLabel lblMtodoDeSeleccin = new JLabel("M\u00E9todo de Selecci\u00F3n de Sobrevivientes");
		lblMtodoDeSeleccin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblMtodoDeSeleccin, "2, 14, 4, 1");
		
		//Métodos de selección de sobrevivientes
	    String[] metodos3 = new String[2];
	    metodos3[0] = SeleccionSobrevivienteSteadyState.nombre;
	    metodos3[1] = SeleccionSobrevivientesRanking.nombre;
        metodosSobrevivientes = new WebComboBox ( metodos3 );
        metodosSobrevivientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        metodosSobrevivientes.addItemListener( new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
                if (metodosSobrevivientes.getSelectedIndex() != 0){
                	lblN.setVisible(false);
                	nn.setVisible(false);
                }else{
                	lblN.setVisible(true);
                	nn.setVisible(true);
                }
                	
            }	
        });
		panelConfig.add(metodosSobrevivientes, "6, 14, 2, 1");
		
		lblN = new JLabel("n = ");
		lblN.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panelConfig.add(lblN, "9, 14");
		
		nn = new WebSpinner ();
		nn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nn.setModel(new SpinnerNumberModel(2, 2, 10000, 1));
		nn.setValue(2);
		panelConfig.add(nn, "11, 14");
		
		JLabel lblTamaoDeLa = new JLabel("Tama\u00F1o de la Poblaci\u00F3n Inicial");
		lblTamaoDeLa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblTamaoDeLa, "2, 16, 3, 1");
		
		//Tamaño de la población inicial
		sizePoblacion = new WebSpinner ();
		sizePoblacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if ( (Integer) sizePoblacion.getValue() % 2 != 0){
				sizePoblacion.setValue((Integer) sizePoblacion.getValue() + 1);
				}
			}
		});
		sizePoblacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sizePoblacion.setModel(new SpinnerNumberModel(10, 1, 100000, 2));
		sizePoblacion.setValue(10);
		panelConfig.add(sizePoblacion, "6, 16");
		
		lblNewLabel_2 = new JLabel("Aclaraci\u00F3n : No exceder el factorial del n\u00FAmero de aviones");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panelConfig.add(lblNewLabel_2, "7, 16, 5, 1");
		
		JLabel lblProbabilidadDeCruce = new JLabel("Probabilidad de Cruce Gen\u00E9tico");
		lblProbabilidadDeCruce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblProbabilidadDeCruce, "2, 18, 4, 1");
	        
		//Probabilidad de Cruce
		probCruce = new WebSpinner ();
		probCruce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		probCruce.setModel(new SpinnerNumberModel(0.8, 0.00, 1.0, 0.01));
		probCruce.setValue(0.9);
		panelConfig.add(probCruce, "6, 18");

		JLabel lblNewLabel_1 = new JLabel("Probabilidad de Mutaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblNewLabel_1, "2, 20, 3, 1");

		//Probabilidad de Mutación
		probMutacion = new WebSpinner ();
		probMutacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		probMutacion.setModel(new SpinnerNumberModel(0.05, 0.00, 1.0, 0.01));
		probMutacion.setValue(0.05);
		panelConfig.add(probMutacion, "6, 20");
		
		JLabel lblCondicinDeCorte = new JLabel("Condici\u00F3n de Corte");
		lblCondicinDeCorte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(lblCondicinDeCorte, "2, 22");
		
		//Condición de Corte
        String[] corte = new String[1];
        corte[0] = CondicionCorte1Solucion.nombre;
        cortes = new WebComboBox ( corte );
		cortes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelConfig.add(cortes, "6, 22, 2, 1");

		//Cantidad de generaciones maxima
		generacionesMaximas = new WebSpinner ();
		generacionesMaximas.setModel(new SpinnerNumberModel(new Long(10000), new Long(1), new Long(Long.MAX_VALUE), new Long(1)));
		generacionesMaximas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		generacionesMaximas.setValue(new Long(10000));
		panelConfig.add(generacionesMaximas, "9, 22, 3, 1");
		
		lblGeneracinMximaPermitida = new JLabel("Generaci\u00F3n m\u00E1xima permitida");
		lblGeneracinMximaPermitida.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneracinMximaPermitida.setFont(new Font("Tahoma", Font.ITALIC, 14));
		panelConfig.add(lblGeneracinMximaPermitida, "9, 24, 3, 1");
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 10));
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( ! hayInconsistencias() ){
					crearConfig();
					
				}
					
			}
		});
		
		btnConfiguracinAutomatizada = new JButton("Configuraci\u00F3n Automatizada");
		btnConfiguracinAutomatizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (! hayInconsistencias() ){
					crearConfigAutomatizada();
				}
			}
		});
		btnConfiguracinAutomatizada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSur.add(btnConfiguracinAutomatizada);
		botonAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSur.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarConfig();
			}
		});
		botonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSur.add(botonCancelar);
	
	
	}
	
	private void crearConfigAutomatizada() {
		Integer n = (Integer) aviones.getValue();
		Integer tam = (Integer) sizePoblacion.getValue();
		Double probC = (Double) probCruce.getValue();
		Double probM = (Double) probMutacion.getValue();
		Long genMax = (Long) generacionesMaximas.getValue();
		
		ConfiguracionAutomatizada config = new ConfiguracionAutomatizada(n,tam,probC,probM,genMax,(Integer)nn.getValue(),(Integer)k.getValue());
		
		main.setConfigAutomatizada(config.getConfiguraciones());
		this.setVisible(false);
	}

	private boolean hayInconsistencias() {
		boolean salida = false;
		//Steady-State
		if (metodosSobrevivientes.getSelectedIndex() == 0 && (Integer) sizePoblacion.getValue() < (Integer) nn.getValue()){
			salida = true;
			 final WebPopOver popOver = new WebPopOver ( );
             popOver.setModal ( true );
             popOver.setMargin ( 10 );
             popOver.setMovable ( false );
             popOver.getContentPane().setLayout ( new VerticalFlowLayout () );
             popOver.setAlwaysOnTop(true);
             popOver.getContentPane().add ( new WebLabel ( "Si se usa Steady-State el n debe ser menor o igual al tamaño de la población." ).setFontSize(14) );
             popOver.getContentPane().add ( new SingleAlignPanel ( new WebButton ( "Entendido", new ActionListener ()
             {
                 @Override
                 public void actionPerformed ( final ActionEvent e )
                 {
                     popOver.dispose ();
                 }
             } ), SingleAlignPanel.RIGHT ).setMargin ( 10, 0, 0, 0 ) );
             popOver.show ( botonAceptar, PopOverDirection.up);
		}
		
		return salida;
	}

	private void crearConfig(){
		//Método que generará una clase con la configuración para una ejecución determinada.
		
		Integer n = (Integer) aviones.getValue();
		
		GenerarPoblacion genPob = null;
		switch ( metodosGeneracion.getSelectedIndex() ){
			case 0 : genPob = new GenerarPoblacionRandom();	break;
		    case 1 : genPob = new GenerarPoblacionRandomSinRepeticiones(); break;
	}
		
		SeleccionPadres selPad = null ;
		switch ( metodosSeleccion.getSelectedIndex() ){
			case 0 : selPad = new SeleccionPadreTorneo((Integer) k.getValue()); break;
			case 1 : selPad = new SeleccionPadreRuleta(); break;
		}

		Cruzamiento cruza = null;
		switch (operadoresCruce.getSelectedIndex()){
			case 0 : cruza = new C1Punto(); break;
			case 1 : cruza = new CCiclos(); break;
			case 2 : cruza = new Cpmx(); break;
		}

		Mutacion mutacion = null;
		switch (operadoresMutacion.getSelectedIndex()){
			case 0 : mutacion = new MIntercambio(); break;
			case 1 : mutacion = new MInversion(); break;
			case 2 : mutacion = new MMezcla(); break;
			case 3 : mutacion = new MInsercion(); break;
		}

		SeleccionSobrevivientes selSob = null;
		switch(metodosSobrevivientes.getSelectedIndex()){
			case 0 : selSob = new SeleccionSobrevivienteSteadyState((Integer) nn.getValue()); break;
			case 1 : selSob = new SeleccionSobrevivientesRanking(); break;
		}
		
		Integer tam = (Integer) sizePoblacion.getValue();

		Double probC = (Double) probCruce.getValue();
		
		Double probM = (Double) probMutacion.getValue();
		
		CondicionCorte condicion = null;
		switch(cortes.getSelectedIndex()){
			case 0 : condicion = new CondicionCorte1Solucion(); break;
		}
		
		Evaluacion eval = null;
		switch(fit.getSelectedIndex()){
			case 0 : eval = new Evaluacion1toN(); break;
		}
		
		Long genMax = (Long) generacionesMaximas.getValue();
		
		
		Configuracion config = new Configuracion(n,genPob,selPad,cruza,mutacion,selSob,tam,probC,probM,condicion,eval,genMax);
		
		main.setConfig(config);
		
		this.setVisible(false);
	}
	
	private void cerrarConfig() {
		this.dispose();
	}
}
