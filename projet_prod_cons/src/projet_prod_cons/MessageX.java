package projet_prod_cons;

import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;
	private String contennu;
	private Date date;
	
	public String toString (){
		
		return "["+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"] : "+"Source : "+source.toString()+"\tNumero : "+String.valueOf(numero)+"\n\t Contenu : "+contennu+ "\n";
		//TODO 
	}
	public MessageX(Producteur p , int n, String contennu,Date date ){
		this.source=p;
		this.numero=n;
		this.contennu=contennu;
		this.date=date;
	}
	
	
}
