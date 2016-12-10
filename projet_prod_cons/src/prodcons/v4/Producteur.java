package prodcons.v4;

import java.util.Date;

import jus.poc.prodcons.*;

public class Producteur extends Acteur implements _Producteur  {

	private int nbMessage;
	private int nbMessagesProduits;
	private Aleatoire temp_prod;
	private Tampon tampon;
	private Observateur obs;
	private Aleatoire nbExemplaires;
	
	protected Producteur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement,Tampon tampon, int nbMoyenProduction,
			int deviationNbProduction, int nbMoyenExemplaires, int deviationNbExemplaires) throws ControlException {
		
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nbMessage= new Aleatoire(nbMoyenProduction, deviationNbProduction).next();
		nbMessagesProduits = 0;
		temp_prod= new Aleatoire(moyenneTempsDeTraitement,deviationTempsDeTraitement);
		this.tampon=tampon;
		nbExemplaires = new Aleatoire(nbMoyenExemplaires,deviationNbExemplaires);
		obs = observateur;
	}

	@Override
	public int deviationTempsDeTraitement() {
		return super.deviationTempsDeTraitement;
	}

	@Override
	public int identification() {
		return super.identification();
	}

	@Override
	public int moyenneTempsDeTraitement() {
		return super.moyenneTempsDeTraitement;
	}

	@Override
	public int nombreDeMessages() {
		
		return nbMessage;
	}

	@Override
	public void run() {
		((ProdCons) tampon).nv_prod();
		for (int i=0; i <nbMessage; i++){
			je_parle("je produis le message "+(i+1) +" sur "+nbMessage +"\n");
			MessageX m = new MessageX(this, i, "patate", new Date(),nbExemplaires.next());
			
			int tempAttente= temp_prod.next();
			
			
			
			try {
				obs.productionMessage(this, m, tempAttente);
				sleep(tempAttente);
			} catch (InterruptedException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			} catch (ControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
//				je_parle("pre put le tampon a "+tampon.enAttente()+ " message(s) en attente et est de taille "+tampon.taille());
//				System.out.println("pre put : tampon : " + ((ProdCons) tampon).toString());
				obs.depotMessage(this, m);
				tampon.put(this, m);
				nbMessagesProduits++;
//				m.set_date_envoi(new Date());  ça ne convient pas ici car on est sorti de la section critique
//				je_parle("j'ai put le message numero : " + (m.get_numero()+1));
//				System.out.println("post put : tampon : " + ((ProdCons) tampon).toString());
//				je_parle("post put le tampon a "+tampon.enAttente()+ "messages en attente et est de taille "+tampon.taille() +"\n");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
			
		}
		((ProdCons) tampon).fin_prod();
		
	}
	
	public static int Prod (){
		return Acteur.typeProducteur;
	}
	
	//A utiliser pour les tests de fin d'execution. permet de savoir si un producteur a produit tous ses messages.
	public boolean messages_tous_deposes()
	{
		return nbMessage==nbMessagesProduits;
	}
	
	
	public String toString (){
		return "Producteur "+this.identification();
	}
	
	public void je_parle(String message)
	{
		System.out.println(MessageX.Format_HeureMinuteSeconde(new Date()) +this.toString()+"\t"+ message);
	}
}
