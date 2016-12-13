package prodcons.v1 ;

import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;
	private int numero_bis;
	private String contenu;
	private Date date_production;
	private Date date_envoi;
	private Date date_retrait;
	private Date date_consommation;

	
	public MessageX(Producteur p , int n, String contenu,Date date ){
		this.source=p;
		this.numero=n;
		this.contenu=contenu;
		this.date_production=date;
		this.numero_bis=0;
	}
	
	public void set_num (int n){
		numero_bis=n;
	}
	
	public static String Format_HeureMinuteSeconde(Date date)
	{
		return "["+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"] : ";
	}
	

	
	//pour debug :
	public int get_numero()
	{
		return numero;
	}


	public void set_date_envoi(Date date)
	{
		date_envoi = date;
	}
	
	public Date get_date_envoi()
	{
		return date_envoi;
	}
	
	public void set_date_production(Date date)
	{
		date_production = date;
	}
	public Date get_date_production()
	{
		return date_production;
	}
	
	public void set_date_retrait(Date date)
	{
		date_retrait=date;
	}
	public Date get_date_retrait()
	{
		return date_retrait;
	}
	
	public void set_date_consommation(Date date)
	{
		date_consommation = date;
	}
	public Date get_date_consommation()
	{
		return date_consommation;
	}
	
	public String toString (){
		
		return "Produit à " + Format_HeureMinuteSeconde(date_production)+"Source : "+source.toString()+"\tNumero : "+String.valueOf(numero)+" Contenu : "+contenu  + "\tmessage n°" + String.valueOf(numero_bis) + "\n";
	}



}
