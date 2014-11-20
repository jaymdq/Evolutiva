package ui;

import com.alee.extended.statusbar.WebStatusLabel;

public class Cronometro implements Runnable{

	public boolean cronometroActivo = false;
	private WebStatusLabel tiempo;
	private Long ms;
	
	public Cronometro (WebStatusLabel label){
		this.tiempo = label;
		ms = new Long(0);
	}
	
	@Override
	public void run() {
		Integer horas = 0,minutos = 0 , segundos = 0, milesimas = 0;
		//min es minutos, seg es segundos y mil es milesimas de segundo
		String h="",min="", seg="", mil="";
		tiempo.setText( "00:00:00:000" );
		try
		{
			//Mientras cronometroActivo sea verdadero entonces seguira
			//aumentando el tiempo
			while( cronometroActivo )
			{
				Thread.sleep( 4 );
				//Incrementamos 4 milesimas de segundo
				milesimas += 4;
				ms += 4;
				//Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
				//y las milesimas de segundo de nuevo a 0
				if( milesimas == 1000 )
				{
					milesimas = 0;
					segundos += 1;
					//Si los segundos llegan a 60 entonces aumenta 1 los minutos
					//y los segundos vuelven a 0
					if( segundos == 60 )
					{
						segundos = 0;
						minutos++;
						
						if (minutos == 60){
							horas++;
							minutos = 0;
						}
					}
				}
				//Esto solamente es estetica para que siempre este en formato
				//00:00:00:000
				h = horas.toString();
				if( minutos < 10 ) min = "0" + minutos;
				else min = minutos.toString();
				if( segundos < 10 ) seg = "0" + segundos;
				else seg = segundos.toString();
				if( milesimas < 10 ) mil = "00" + milesimas;
				else if( milesimas < 100 ) mil = "0" + milesimas;
				else mil = milesimas.toString();
				//Colocamos en la etiqueta la informacion
				tiempo.setText(h + ":" + min + ":" + seg + ":" + mil ); 
			}
		}catch(Exception e){}
		//Cuando se reincie se coloca nuevamente en 00:00:000
	}

	public void empezar(){
		cronometroActivo = true;
		Thread t = new Thread(this);
		ms = new Long(0);
		t.start();
	}
	
	public void parar(){
		cronometroActivo = false;
	}

	public void reiniciar() {
		tiempo.setText("00:00:00:000");
		ms = new Long(0);
		
	}
	
	public String toString(){
		return tiempo.getText();
	}

	public Long getMs(){
		return this.ms;
	}

}
