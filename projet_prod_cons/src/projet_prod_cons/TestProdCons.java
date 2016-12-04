package projet_prod_cons;

import java.util.ArrayList;

import jus.poc.prodcons.*;

public class TestProdCons  extends Simulateur {
	private Observateur obs;
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

		
		public TestProdCons(Observateur observateur, String option) {
			super(observateur);
			this.obs=observateur;
			
			init(option);
			
			tampon = new ProdCons(nbBuffer);
			
			
			Acteur prod;
			p= new ArrayList<Producteur>();
			for (int i=0;i<nbProd;i++){
				try {
					prod=new Producteur(obs, tempsMoyenProduction, deviationTempsMoyenProduction, tampon);
					p.add((Producteur)prod); 
				} catch (ControlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
				}
			
				
			
			}
			
			c= new  ArrayList<Consommateur>();
			for (int i=0;i<=nbCons;i++){
				try {
					c.add(new Consommateur(obs, tempsMoyenConsommation, deviationTempsMoyenConsommation, tampon));
				} catch (ControlException e) {
					// TODO Auto-generated catch block
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
			//b=b && p.get(i).isAlive();
		}
		for (int i=0;i<nbCons;i++){
			System.out.println(c.get(i).toString());
			c.get(i).start();
		}
	}

	public static void main(String[] args) {
		new TestProdCons(new Observateur(),"option.xml").start();
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
