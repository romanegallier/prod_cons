package prodcons.v4;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class MessageX implements Message{
	private Producteur  source;
	private int numero;		// Permet de tester
	private int numero_bis = 0; // Permet de tester
	private String contenu;
	private Date date_production;
	private Date date_envoi;
	private ArrayList<Date> l_date_retrait = new ArrayList<Date>();
	private ArrayList<Date> l_date_consommation = new ArrayList<Date>();
	private int nbExemplaires;	// Nombre d'exemplaires de ce message produits
	private int nbConsommateurs = 0; //enregistre le nombre de consommateur essayant de retirer ce message
	private boolean producteur_endormi = true; //Permet de savoir si le producteur a dépassé synchrone.P() ou non (true si il n'a pas encore dépassé synchrone.P()
	
	public MessageX(Producteur p , int n, String contenu,Date date , int nbExemplaires){
		this.source=p;
		this.numero=n;
		this.contenu=contenu;
		this.date_production=date;
		this.nbExemplaires = nbExemplaires;
	}
	
	
	public static String Format_HeureMinuteSeconde(Date date)
	{
		return "["+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"] : ";
	}
	

	
	public int get_num2()
	{
		return numero_bis;
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
	 * retourne nbExemplaire - nbConsommateurs
	 * @return nbExemplaires-nbConsommateurs
	 */
	public int nbConso_attendus()
	{
		return nbExemplaires - nbConsommateurs;
	}
	
	
	/**
	 * décrémente le nombre de consommateurs essayant de retirer le message puis indique si il reste un consommateur à réveiller ou non
	 * @return true si il reste des consommateur à reveiller. Faux sinon
	 */
	public synchronized boolean reveil_necessaire()
	{
		nbConsommateurs--;
		return nbConsommateurs >=0;
	}
	
	public void incrNbConsommateurs()
	{
		nbConsommateurs++;
	}
	
	public void decrNbConsommateurs()
	{
		nbConsommateurs--;
	}
	
	public int get_nbConsommateurs()
	{
		return nbConsommateurs;
	}
	/**
	 * effet de bord : incrémente le nombre de consommateurs prêts à retirer ce message
	 * indique si il est possible de retirer le message (donc si autant de consommateurs que d'exemplaires sont réunis)
	 * @return true si autant de consommateurs que d'exemplaires sont réunis. False sinon
	 */
	public synchronized boolean retrait_possible()

	{

		nbConsommateurs++;

		return nbConsommateurs == nbExemplaires;
	}
	
	
	public boolean getProducteur_endormi()
	{
		return producteur_endormi;
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
	
	public synchronized void set_date_retrait(Date date)
	{
		l_date_retrait.add(date);
	}
	public ArrayList<Date> get_date_retrait()
	{
		return l_date_retrait;
	}
	
	public synchronized void set_date_consommation(Date date)
	{
		l_date_consommation.add(date);
	}
	public ArrayList<Date> get_date_consommation()
	{
		return l_date_consommation;
	}
	
	public String toString (){
		
		return "Produit à " + Format_HeureMinuteSeconde(date_production)+"Source : "+source.toString()+"\tNumero : "+String.valueOf(numero)+" Contenu : "+contenu  + "\tmessage n°" + String.valueOf(numero_bis) + " et nbExemplaires = "+nbExemplaires+"\n";
	}

	public void set_nbExemplaires(int i) {
		nbExemplaires=i;
		
	}



}
