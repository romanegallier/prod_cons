package prodcons.v6;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class TestProdCons  extends Simulateur {
	private Observateur obs;
	private Observations observations;
	Tampon tampon;
	ArrayList <Producteur> p;
	ArrayList <Consommateur> c;
	
	//******************** OPTIONS ************
	
		protected static int nbProd;
		protected static int nbCons;
		protected static int nbBuffer;
		protected static int tempsMoyenProduction;
		protected static int deviationTempsMoyenProduction;
		protected static int tempsMoyenConsommation;
		protected static int deviationTempsMoyenConsommation;
		protected static int nombreMoyenDeProduction;
		protected static int deviationNombreMoyenDeProduction;
		protected static int nombreMoyenNbExemplaire;
		protected static int deviationNombreMoyenNbExemplaire;

		
		public TestProdCons(Observateur observateur, String option, Observations observations) {
			super(observateur);
			this.obs=observateur;
			
			this.observations=observations;
			
			init(option);
			try {
				obs.init(nbProd, nbCons, nbBuffer);
				observations.init(nbProd, nbCons, nbBuffer);
			} catch (ControlException e1) {
				e1.printStackTrace();
			}
			tampon = new ProdCons(nbBuffer);
			
			
			Acteur prod;
			p= new ArrayList<Producteur>();
			for (int i=0;i<nbProd;i++){
				try {
					prod=new Producteur(obs, tempsMoyenProduction, deviationTempsMoyenProduction, tampon, nombreMoyenDeProduction, deviationNombreMoyenDeProduction,observations);
					p.add((Producteur)prod); 
				} catch (ControlException e) {
					e.printStackTrace();
			
				}
			}
			
			c= new  ArrayList<Consommateur>();
			for (int i=0;i<nbCons;i++){
				try {
					c.add(new Consommateur(obs, tempsMoyenConsommation, deviationTempsMoyenConsommation, tampon,observations));
				} catch (ControlException e) {
					e.printStackTrace();
				} 
			}
			
		}


	@Override
	protected void run() throws Exception {

		for (int i=0;i<nbProd;i++){
			if (p.get(i)==null) System.out.println("petit probleme\n");
			else System.out.println(p.get(i).toString());
			p.get(i).start();
		}
		for (int i=0;i<nbCons;i++){
			System.out.println(c.get(i).toString());
			c.get(i).start();
		}
	}

	public static void main(String[] args) {
		Observations observations = new Observations();
		TestProdCons tpd = new TestProdCons(new Observateur(),"option.xml",observations);
		
		
		tpd.start();
		//On s'arrête jusqu'à la fin de chaque consommateur (qui se produit après la fin de chaque producteur... au pire on peut aussi attendre la fin de chaque producteur... ce qu'on va faire)

		for(Producteur producteur : tpd.p)
		{
			try {
				producteur.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(Consommateur consommateur : tpd.c)
		{
			try {
				consommateur.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		boolean tests = true;
		//A présent, chaque producteur et consommateur a terminé de s'exécuter. On peut effectuer des tests
		
		if(tests)
		{
			((ProdCons) tpd.tampon).tests_temporels(new Date());
			 
			if (observations.verification()) System.out.println("Tout c'est bien passe ");
			else System.out.println("Il y a eu des erreurs");
		}
	}
	
	
	

	/**
	* Retreave the parameters of the application.
	* @param file the final name of the file containing the options.
	*/
	protected static void init(String file) {
	// retreave the parameters of the application

		final class Properties extends java.util.Properties {
			private static final long serialVersionUID = 1L;
			public int get(String key){return Integer.parseInt(getProperty(key));}
			public Properties(String file) {
				try{
					loadFromXML(ClassLoader.getSystemResourceAsStream(file));
				}catch(Exception e){e.printStackTrace();}
			}
		}
		Properties option = new Properties("prodcons/options/"+file);
		nbProd = option.get("nbProd");
		System.out.println("nb_prod =" + nbProd);
		nbCons = option.get("nbCons");
		nbBuffer = option.get("nbBuffer");
		tempsMoyenProduction = option.get("tempsMoyenProduction");
		deviationTempsMoyenProduction = option.get("deviationTempsMoyenProduction");
		tempsMoyenConsommation = option.get("tempsMoyenConsommation");
		deviationTempsMoyenConsommation = option.get("deviationTempsMoyenConsommation");
		nombreMoyenDeProduction = option.get("nombreMoyenDeProduction");
		deviationNombreMoyenDeProduction = option.get("deviationNombreMoyenDeProduction");
		nombreMoyenNbExemplaire = option.get("nombreMoyenNbExemplaire");
		deviationNombreMoyenNbExemplaire = option.get("deviationNombreMoyenNbExemplaire");
		}




}
