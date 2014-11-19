package ui;


import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Configuracion;

import com.alee.extended.filechooser.WebDirectoryChooser;
import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.progress.WebProgressOverlay;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.extended.time.ClockType;
import com.alee.extended.time.WebClock;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.managers.language.LanguageConstants;
import com.alee.managers.language.LanguageManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotificationPopup;
import com.alee.utils.swing.DialogOptions;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Toolkit;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Color;


public class MainWindow {

	public static JFrame frame;
	private static Consola consola;
	private static String rutaDirectorio=null;
	private String titulo = "Trabajo Práctico Ubicador de Aviones - Introducción a la Computación Evolutiva.";
	private Configuracion config = null;
	private Thread threadEjecucion;
	private static Cronometro cronometro;
	private static WebStatusLabel tiempo;
	private static WebButton button;
	DialogConfiguracion dialogConfig;
	private Vector<Configuracion> configuraciones = new Vector<Configuracion>();
	private boolean automatizado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					//Look and feel
				    LanguageManager.setDefaultLanguage(LanguageConstants.SPANISH);
					WebLookAndFeel.install();
					
					// System.setOut(new PrintStream("salidas.txt"));
					// System.setErr(new PrintStream("errores.txt"));
					 	 
					//Lanzamos la ventana
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/imagenes/Avion.png")));
		frame.setTitle(titulo);
		frame.setBounds(0,0,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('a');
		menuBar.add(menuArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo..");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		mntmNuevo.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/file.png")));
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuArchivo.add(mntmNuevo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/exit.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		menuArchivo.add(mntmSalir);

		JMenu mnConfiguracin = new JMenu("Configuraci\u00F3n");
		mnConfiguracin.setMnemonic('c');
		menuBar.add(mnConfiguracin);
		
		JMenuItem mntmParmetrosDelAlgoritmo = new JMenuItem("Parámetros del Algoritmo");
		mntmParmetrosDelAlgoritmo.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/check1.png")));
		mntmParmetrosDelAlgoritmo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmParmetrosDelAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirConfiguracion();
			}
		});
		mnConfiguracin.add(mntmParmetrosDelAlgoritmo);
		
		JMenuItem mntmDirectorioDeSalida = new JMenuItem("Directorio de Salida");
		mntmDirectorioDeSalida.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/folder.png")));
		mntmDirectorioDeSalida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmDirectorioDeSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarSalida();
			}
		});
		mnConfiguracin.add(mntmDirectorioDeSalida);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		mnAcercaDe.setMnemonic('y');
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmVerInforme = new JMenuItem("Ver Informe");
		mntmVerInforme.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/pdf.gif")));
		mntmVerInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInforme();
			}
		});
		mntmVerInforme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnAcercaDe.add(mntmVerInforme);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.setIcon(new ImageIcon(MainWindow.class.getResource("/imagenes/about.png")));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAcercaDe();
			}
		});
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnAcercaDe.add(mntmAcercaDe);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
        //Boton de Ejecutar / Parar
        final ImageIcon start = new ImageIcon(MainWindow.class.getResource("/imagenes/start.png"));
        final ImageIcon stop = new ImageIcon(MainWindow.class.getResource("/imagenes/stop.png"));

        // Progress overlay
        final WebProgressOverlay progressOverlay = new WebProgressOverlay ();
        progressOverlay.setConsumeEvents ( false );
        
        // Progress state change button
        button = new WebButton ( "Ejecutar", start );
        button.setFontSize(16);
        button.setEnabled(false);
        button.setPreferredWidth(248);
        button.setRound (9);
        progressOverlay.setComponent ( button );

        // Progress switch
        button.addActionListener ( new ActionListener ()
        {
        	@Override
        	public void actionPerformed ( ActionEvent e )
        	{
        		boolean showLoad = !progressOverlay.isShowLoad ();

        		// Changing progress visibility
        		progressOverlay.setShowLoad ( showLoad );

        		// Changing buttons text and icons
        		button.setText ( showLoad ? "Parar" : "Ejecutar" );
        		button.setIcon ( showLoad ? stop : start );
        		if (showLoad){
        			ejecutar();
        		}else
        			pararEjecucion();
        	}
        } );

        //Barra de Estado
        WebStatusBar statusBar = new WebStatusBar ();
        statusBar.add(progressOverlay,ToolbarLayout.START);
      
        tiempo = new WebStatusLabel ( "00:00:00:000");
        tiempo.setFontSize(16);
        statusBar.add(tiempo, ToolbarLayout.MIDDLE);
        WebMemoryBar memoryBar = new WebMemoryBar ();
        memoryBar.setFontSize(16);
        memoryBar.setPreferredWidth ( memoryBar.getPreferredSize ().width + 100 );
        statusBar.add ( memoryBar, ToolbarLayout.END );
        panel.add(statusBar);
        
        JPanel panelTexto = new JPanel();
        panelTexto.setForeground(Color.LIGHT_GRAY);
        panelTexto.setBorder(null);
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.X_AXIS));
        frame.getContentPane().add(panelTexto, BorderLayout.CENTER);
        
        //Cronometro
        cronometro = new Cronometro(tiempo);
        
        //Consola
        consola = new Consola();
        panelTexto.add(consola.getComponent());
        consola.escribirSalto("Bienvenidos");
        
        //Configuración
        dialogConfig = new DialogConfiguracion(this);
        
	}

	private void nuevo() {
		consola.limpiar();	
		cronometro.reiniciar();
		config = null;
		button.setEnabled(false);
	}

	private void mostrarInforme() {
		try {
			File file = new File("doc/manual.pdf");
			Desktop.getDesktop().open(file);
		} catch (Exception e) {}
	}

	private void seleccionarSalida() {
		WebDirectoryChooser directoryChooser = new WebDirectoryChooser ( frame, "Elija un directorio" );
		directoryChooser.setVisible ( true );
		if ( directoryChooser.getResult () == DialogOptions.OK_OPTION )
		{
			File file = directoryChooser.getSelectedDirectory ();
			rutaDirectorio = file.getAbsolutePath();
			frame.setTitle(titulo + "     Salida: " + rutaDirectorio);
		}
	}
	
	private static void escribirAArchivo(){
		if (rutaDirectorio != null){
			FileOutputStream fos;
			try {
				String fechahora = info.getHoraFechaArchivo();
				fos = new FileOutputStream(rutaDirectorio+fechahora+".sol");
				try {
					consola.escribir("Archivo almacenado en " + rutaDirectorio + fechahora + ".sol");
					fos.write(consola.getTexto().getBytes());
					fos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			}
		}

	}

	
	private void mostrarAcercaDe() {
		DialogAcercaDe diag = new DialogAcercaDe(this);
		//Mostramos en pantalla
		diag.setVisible(true);
		diag.setAlwaysOnTop(true);
		diag.setModal(true);
		diag.setModalityType(ModalityType.APPLICATION_MODAL);
	}

	private void abrirConfiguracion() {
		//Mostramos en pantalla
		dialogConfig.setVisible(true);
		dialogConfig.setAlwaysOnTop(true);
		dialogConfig.setModal(true);
		dialogConfig.setModalityType(ModalityType.APPLICATION_MODAL);
	}

	public static void dibujarAviones(Vector<Integer> solucion){ 

		//Paramos al reloj.
		cronometro.cronometroActivo = false;
		//Sincronizamos el texto por pantalla y el cronometro.
		try {
			Thread.currentThread();
			Thread.sleep(5);
		} catch (InterruptedException e) {}
		tiempo.setText(cronometro.toString());		
		
		consola.escribirSalto("Fin de la Ejecución : " + info.getHoraFecha());
		consola.escribirSalto("Tiempo de Ejecución : " + tiempo.getText());
		consola.escribirSalto("Solución :");
		
		/* SOLUCION GIRADA
		 for (int i = 1; i <= solucion.size(); i++){
			for (int j = 1; j <= solucion.size(); j++){
				if ( j  == solucion.elementAt(i-1) ){
					consola.escribir("X ");
				}else
					consola.escribir("O ");
				
				
			} 
			consola.escribirSalto("");
		}
		consola.escribirSalto("");
		*/
		
		//Solución Correcta
		for (int i = 1; i <= solucion.size(); i++){
			int pos = solucion.indexOf((int) i );
			for (int j = 0; j < solucion.size(); j++){
				if (j == pos){
					consola.escribir("X ");
				}else
					consola.escribir("O ");
			}
			consola.escribirSalto("");
		}
		
		//Aviso que se encontró una solución.
		 final WebNotificationPopup notificationPopup = new WebNotificationPopup ();
         notificationPopup.setIcon ( new ImageIcon( Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/imagenes/plus.png")) ));
         notificationPopup.setDisplayTime ( 5000 );
         final WebClock clock = new WebClock ();
         clock.setClockType ( ClockType.timer );
         clock.setTimeLeft ( 5000 );
         clock.setTimePattern ( "'Se encontró una solución.'" );
         notificationPopup.setContent ( new GroupPanel ( clock ) );
         NotificationManager.showNotification ( notificationPopup );
         clock.start ();
 
 		//Necesario repintar la pantalla (sino no se updateaba el menuBar)
 		frame.repaint();
 		
 		//Paro la animación del botón. !! DEJAR ACÁ ABAJO
 		button.doClick();

 		//Escribimos a Archivo
 		escribirAArchivo();
	}

	public void setConfig(Configuracion config) {
		this.config  = config;
		button.setEnabled(true);
		consola.limpiar();
		consola.escribirSalto(config.toString());
		consola.escribirSalto(info.configPc());
	}

	private void ejecutar() {	
		if (automatizado)
		{
			
				LanzadorDeConfiguraciones lanzador = new LanzadorDeConfiguraciones(consola,cronometro,threadEjecucion,configuraciones);	
				Thread t = new Thread(lanzador);
				t.start();
		}
		else
		if (config != null){
			consola.limpiar();
			consola.escribirSalto(config.toString());
			consola.escribirSalto(info.configPc());
			
			AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(this.config,consola);
			
			//Mostramos la fecha
			consola.escribirSalto("Comienzo de la ejecución : " + info.getHoraFecha());
			
			threadEjecucion = new Thread(algoritmo);
			cronometro.empezar();
			threadEjecucion.start();
		}

	}

	private void pararEjecucion() {
		if (threadEjecucion != null && threadEjecucion.isAlive()){
			threadEjecucion.interrupt();
			//consola.escribirSalto("Ejecución interrumpida : " + info.getHoraFecha());
			
			System.gc();
		}
		//Paramos al reloj.
		cronometro.cronometroActivo = false;
	}

	public static void noSeEncontroSolucion(boolean doclick) {
		
		try{
		consola.escribirSalto("Fin de la Ejecución : " + info.getHoraFecha());
		
		consola.escribirSalto("Se llegó al límite de generaciones permitidas o se canceló la ejecución: no se encontró una solución.");
		} catch(Exception e) {}
		
		//Aviso que no se encontró una solución.
		 final WebNotificationPopup notificationPopup = new WebNotificationPopup ();
        notificationPopup.setIcon ( new ImageIcon( Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/imagenes/plus.png")) ));
        notificationPopup.setDisplayTime ( 5000 );
        final WebClock clock = new WebClock ();
        clock.setClockType ( ClockType.timer );
        clock.setTimeLeft ( 5000 );
        clock.setTimePattern ( "'Se llegó al límite de generaciones permitidas y no se encontró una solución.'" );
        notificationPopup.setContent ( new GroupPanel ( clock ) );
        NotificationManager.showNotification ( notificationPopup );
        clock.start ();

		//Necesario repintar la pantalla (sino no se updateaba el menuBar)
		frame.repaint();
		
		//Paro la animación del botón. !! DEJAR ACÁ ABAJO
		if (!doclick)
			button.doClick();

		//Escribimos a Archivo
		escribirAArchivo();
		
	}

	public void setConfigAutomatizada(Vector<Configuracion> configuraciones) {

		this.configuraciones  = configuraciones;
		automatizado = true;
		
	}
}
