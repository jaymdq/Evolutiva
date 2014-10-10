package ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import com.alee.extended.label.WebLinkLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;

import java.awt.Font;

import javax.swing.JSeparator;

public class DialogAcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6228527787800000645L;
	private JPanel contentPanel = new JPanel();
	
	@SuppressWarnings("static-access")
	public DialogAcercaDe(MainWindow mainWindow) {
		super(mainWindow.frame, "Acerca De COMPLETAR", ModalityType.APPLICATION_MODAL);
		setTitle("Ubicador de Aviones");
		this.setResizable(false);
		setBounds(100, 100, 460, 470);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		FormLayout fl_contentPanel = new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(72dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("86dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
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
				FormFactory.DEFAULT_ROWSPEC,});
		contentPanel.setLayout(fl_contentPanel);
		{
			JLabel brian = new JLabel("");
			brian.setToolTipText("CAIMMI, Brian");
			brian.setBorder(new LineBorder(Color.BLACK));
			brian.setIcon(new ImageIcon(DialogAcercaDe.class.getResource("/imagenes/brian.jpg")));
			contentPanel.add(brian, "3, 2, center, default");
		}
		{
			JLabel hernan = new JLabel("");
			hernan.setToolTipText("ROCHA, Hern\u00E1n Gabriel");
			hernan.setBorder(new LineBorder(Color.BLACK));
			hernan.setIcon(new ImageIcon(DialogAcercaDe.class.getResource("/imagenes/viru.jpg")));
			contentPanel.add(hernan, "7, 2, center, center");
		}
		{
			JLabel lblCaimmibrian = new JLabel("CAIMMI,Brian");
			lblCaimmibrian.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCaimmibrian.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCaimmibrian, "3, 4");
		}
		{
			JLabel lblNewLabel = new JLabel("Desarrolladores");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, "5, 2, center, default");
		}
		{
			JLabel lblRochaHernnGabriel = new JLabel("VALLEJOS, Sebasti\u00E1n");
			lblRochaHernnGabriel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRochaHernnGabriel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblRochaHernnGabriel, "7, 4");
		}
		{	
			 WebLinkLabel mail = new WebLinkLabel ();
			 mail.setEmailLink ("bcaimmi@gmail.com");
		     contentPanel.add(mail, "3, 6, 2, 1");
		}
		{
			
			 WebLinkLabel mail = new WebLinkLabel ();
			 mail.setEmailLink ("sebaviru@gmail.com");
		     contentPanel.add(mail, "7, 6");
		
		}
		{
			JSeparator separator = new JSeparator();
			contentPanel.add(separator, "1, 8, 7, 1");
		}
		
		{
			JLabel lblNewLabel_2 = new JLabel("FACULTAD DE CIENCIAS EXACTAS");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel_2, "1, 10, 7, 1, center, default");
		}
		{
			JLabel lblUnicen = new JLabel("UNICEN");
			lblUnicen.setHorizontalAlignment(SwingConstants.CENTER);
			lblUnicen.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblUnicen, "1, 12, 7, 1, center, default");
		}
		{
			JLabel label = new JLabel(" ");
			label.setIcon(new ImageIcon(DialogAcercaDe.class.getResource("/imagenes/unicen.png")));
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(label, "1, 14, 7, 1, center, default");
		}
		{
			JLabel lblProyectoFinal = new JLabel("Proyecto Final");
			lblProyectoFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblProyectoFinal.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblProyectoFinal, "1, 16, 7, 1, center, default");
		}
		{
			JLabel lblProyectoFinalEstructuras = new JLabel("Introducci\u00F3n a la Computaci\u00F3n Evolutiva");
			lblProyectoFinalEstructuras.setHorizontalAlignment(SwingConstants.CENTER);
			lblProyectoFinalEstructuras.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblProyectoFinalEstructuras, "1, 18, 7, 1, center, default");
		}
	}

}
