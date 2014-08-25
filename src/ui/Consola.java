package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.text.DefaultCaret;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

public class Consola {

	private WebTextArea text;
	private WebScrollPane scroll;
	
	public Consola() {
		text = new WebTextArea ();
		//false para que pueda escribir para la derecha.
		text.setLineWrap ( false );
		text.setWrapStyleWord ( true );
		text.setMargin ( 5 );
		text.setFont(new Font("monospaced", Font.PLAIN, 16));
		text.setEditable(false);
		text.setDoubleBuffered(true);
		text.setSelectionColor(Color.YELLOW);
		scroll = new WebScrollPane ( text, false, true );
		scroll.setPreferredSize ( null );
		//Hacer que baje el documento a medida que se escribe
		DefaultCaret caret = (DefaultCaret) text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void escribir(String linea){
		text.append(linea);
	}
	
	public void escribirSalto(String linea){
		text.append(linea+"\n");
	}
	
	public void limpiar(){
		text.clear();
	}
	
	public Component getComponent(){
		return scroll;
	}

	public String getTexto(){
		return text.getText();
	}
	
	
}
