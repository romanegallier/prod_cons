package projet_prod_cons;

import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;
	private int numero_bis;
	private String contenu;
	private Date date_production;
	private int numero_tris;
	
	public String toString (){
		
		return "Produit à " + Format_HeureMinuteSeconde(date_production)+"Source : "+source.toString()+"\tNumero : "+String.valueOf(numero)+" Contenu : "+contenu  + "\tmessage n°" + String.valueOf(numero_bis) + "\n";

	

		//TODO 
	}
	public MessageX(Producteur p , int n, String contenu,Date date ){
		this.source=p;
		this.numero=n;
		this.contenu=contenu;
		this.date_production=date;
		this.numero_bis=0;
		this.numero_tris=0;
	}
	
	public void set_num (int n){
		numero_bis=n;
	}
	
	public static String Format_HeureMinuteSeconde(Date date)
	{
		return "["+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"] : ";
	}
	

	public void set_num2 (int n){
		numero_tris=n;
	}
	
	//pour debug :
	public int get_numero()
	{
		return numero;
	}
}
