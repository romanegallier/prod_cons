package prodcons.v4;

import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;		//TODO à enlever, (c'était pour des tests)
	private int numero_bis;	//TODO à enlever, (c'était pour des tests)
	private String contenu;
	private Date date_production;
	private Date date_envoi;
	private Date date_retrait;
	private Date date_consommation;
	private int numero_tris;	//TODO à enlever, (c'était pour des tests)
	private int nbExemplaires;	// Nombre d'exemplaires de ce message produits
	private int nbConsommateurs; //enregistre le nombre de consommateur essayant de retirer ce message
	
	
	public MessageX(Producteur p , int n, String contenu,Date date , int nbExemplaires){
		this.source=p;
		this.numero=n;
		this.contenu=contenu;
		this.date_production=date;
		this.numero_bis=0;
		this.numero_tris=0;
		this.nbExemplaires = nbExemplaires;
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

	/**
	 * indique si il est possible de retirer le message (donc si autant de consommateurs que d'exemplaires sont réunis)
	 * @return true si autant de consommateurs que d'exemplaires sont réunis. False sinon
	 */
	public boolean retrait_possible()
	{
		//TODO retirer le syso car il ne sert qu'à tester le bon fonctionnement de la consommation synchrone
		System.out.println("Il y a "+nbConsommateurs+" qui veulent retirer ce message ("+nbExemplaires+" exemplaires)");

		return nbConsommateurs == nbExemplaires;
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
