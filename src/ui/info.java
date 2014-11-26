package ui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.util.Set;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class info {

	public static String nombrePC()
	{
		return System.getenv("COMPUTERNAME");
	}

	public static String Usuario()
	{
		return System.getProperty("user.name");
	}

	public static String Procesador()
	{
		return System.getenv("PROCESSOR_IDENTIFIER");
	}

	public static String SO()
	{
		return System.getProperty("os.name");
	}

	public static String arqSO()
	{
		return System.getProperty("os.arch");
	}

	public static String JDK()
	{
		return System.getProperty("java.version");  
	}


	public static String configPc() {
		String salida = "";
		salida += "Propiedades ("+ nombrePC() +") : \n";
		salida += "  +  Usuario = [" + Usuario() +"].\n";
		salida += "  +  Sistema Operativo = [" + SO() +"].\n";
		salida += "  +  Arquitectura del Sistema Operativo = [" + arqSO() +"].\n";

		CpuInfo[] infos = null;
		Sigar sigar = new Sigar();
		try {
			infos = sigar.getCpuInfoList();
		} catch (SigarException e) {}
		CpuInfo info = infos[0];
		salida += "  +  Procesador = [" + Procesador() +"].\n";
		salida += "  	+  Fabricante = [" + info.getVendor() +"].\n";
		salida += "  	+  Modelo = [" + info.getModel() +"].\n";
		salida += "  	+  Mhz = [" + info.getMhz() +"].\n\n";

		return salida;
	}      

	public static String getHoraFecha(){
		//Instanciamos el objeto Calendar
		//en fecha obtenemos la fecha y hora del sistema
		Calendar fecha = new GregorianCalendar();
		//Obtenemos el valor del año, mes, día,
		//hora, minuto y segundo del sistema
		//usando el método get y el parámetro correspondiente
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		int segundo = fecha.get(Calendar.SECOND);
		return "" + dia + "/" + (mes+1) + "/" + anio + " a las "+hora+":"+minuto+":"+segundo +".";
	}

	public static String getHoraFechaArchivo() {
		//Instanciamos el objeto Calendar
		//en fecha obtenemos la fecha y hora del sistema
		Calendar fecha = new GregorianCalendar();
		//Obtenemos el valor del año, mes, día,
		//hora, minuto y segundo del sistema
		//usando el método get y el parámetro correspondiente
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		int segundo = fecha.get(Calendar.SECOND);
		return "Soluci�n_" + dia + "_" + (mes+1) + "_" +anio + "__"+hora+"hs_"+minuto+"min_"+segundo +"segs";
	}

}
