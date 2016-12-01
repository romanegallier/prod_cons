package prodcons.v2;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;
	private String contennu;
	private int date;
	
	public String toString (){
		
		return "Source"+source.toString()+"Numero"+String.valueOf(numero)+"Contennu"+contennu+"Date"+String.valueOf(date);
		//TODO 
	}
	public MessageX(Producteur p , int n, String contennu,int date ){
		this.source=p;
		this.numero=n;
		this.contennu=contennu;
		this.date=date;
	}
	
	
}
