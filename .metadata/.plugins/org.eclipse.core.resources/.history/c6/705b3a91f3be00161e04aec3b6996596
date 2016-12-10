package prodcons.v4;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;		//TODO à enlever, (c'était pour des tests)
	private int numero_bis;	//TODO à enlever, (c'était pour des tests)
	private String contenu;
	private Date date_production;
	private Date date_envoi;
	private ArrayList<Date> l_date_retrait = new ArrayList<Date>();
	private ArrayList<Date> l_date_consommation = new ArrayList<Date>();
	private int numero_tris;	//TODO à enlever, (c'était pour des tests)
	private int nbExemplaires;	// Nombre d'exemplaires de ce message produits
	private int nbConsommateurs = 0; //enregistre le nombre de consommateur essayant de retirer ce message
	
	
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

	//TODO peut être à supprimer
	public int get_nbExemplaires()
	{
		return nbExemplaires;
	}
	
	/**
	 * décrémente le nombre de consommateurs essayant de retirer le message puis indique si il reste un consommateur à réveiller ou non
	 * @return true si il reste des consommateur à reveiller. Faux sinon
	 */
	public boolean reveil_necessaire()
	{
		nbConsommateurs--;
		return nbConsommateurs > 0;
	}
	/**
	 * effet de bord : incrémente le nombre de consommateurs prêts à retirer ce message
	 * indique si il est possible de retirer le message (donc si autant de consommateurs que d'exemplaires sont réunis)
	 * @return true si autant de consommateurs que d'exemplaires sont réunis. False sinon
	 */
	public boolean retrait_possible()
	{

		nbConsommateurs++;
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
		l_date_retrait.add(date);
	}
	public ArrayList<Date> get_date_retrait()
	{
		return l_date_retrait;
	}
	
	public void set_date_consommation(Date date)
	{
		l_date_consommation.add(date);
	}
	public ArrayList<Date> get_date_consommation()
	{
		return l_date_consommation;
	}
	
	public String toString (){
		
		return "Produit à " + Format_HeureMinuteSeconde(date_production)+"Source : "+source.toString()+"\tNumero : "+String.valueOf(numero)+" Contenu : "+contenu  + "\tmessage n°" + String.valueOf(numero_bis) + "\n";
	}



}
