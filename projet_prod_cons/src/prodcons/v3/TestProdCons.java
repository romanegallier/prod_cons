package prodcons.v3;

import java.util.ArrayList;

import jus.poc.prodcons.*;

public class TestProdCons  extends Simulateur {
	private Observateur obs;
	private int taille_tampon=3;
	int nb_conso=2;
	int nb_prod=2;
	Tampon tampon;
	ArrayList <Producteur> p;
	ArrayList <Consommateur> c;
	
	public TestProdCons(Observateur observateur) {
		super(observateur);
		this.obs=observateur;
		try {
			obs.init(nb_prod, nb_conso, taille_tampon);
		} catch (ControlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//TODO recuperer l nombre de producteur 
		
		//TODO recuperer nombre conso
		
		
		//TODO recuperer taille du tampon 
		
		
		
		tampon = new ProdCons(taille_tampon);
		
		
		
		Producteur prod;
		p= new ArrayList<Producteur>();
		int moyenneTempsDeTraitement= 10;//TODO a changer
		int deviationTempsDeTraitement =5;//TODO a changer
		for (int i=0;i<=nb_prod;i++){
		
			try {
				prod=new Producteur(Producteur.Prod(), obs, moyenneTempsDeTraitement, deviationTempsDeTraitement, tampon);
				p.add(prod);
				obs.newProducteur( prod);
			} catch (ControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}
			
			
		
		}
		
		c= new  ArrayList<Consommateur>();// posse possiblement probleme comme initialisation
		moyenneTempsDeTraitement= 10;//TODO a changer
		deviationTempsDeTraitement =5;//TODO a changer
		for (int i=0;i<=nb_prod;i++){
			try {
				c.add(new Consommateur(Consommateur.Cons(), obs, moyenneTempsDeTraitement, deviationTempsDeTraitement, tampon));
				obs.newConsommateur(c.get(i));
			} catch (ControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}

	

	@Override
	protected void run() throws Exception {

		
		for (int i=0;i<nb_prod;i++){
			if (p.get(i)==null) System.out.println("petit probleme\n");
			else System.out.println(p.get(i).toString());
			p.get(i).start();
			//b=b && p.get(i).isAlive();
		}
		for (int i=0;i<nb_conso;i++){
			System.out.println(c.get(i).toString());
			c.get(i).start();
		}
	}

	public static void main(String[] args) {
		new TestProdCons(new Observateur()).start();
	}
	
	
	



}