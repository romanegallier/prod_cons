package prodcons.v2;

import java.util.Date;

import jus.poc.prodcons.*;

public class Producteur extends Acteur implements _Producteur  {

	private int nbMessage;
	private int nbMessagesProduits;
	private Aleatoire temp_prod;
	private Tampon tampon;
	
	protected Producteur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement,Tampon tampon, int nbMoyenProduction,
			int deviationNbProduction) throws ControlException {
		
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nbMessage= new Aleatoire(nbMoyenProduction, deviationNbProduction).next();
		nbMessagesProduits = 0;
		temp_prod= new Aleatoire(moyenneTempsDeTraitement,deviationTempsDeTraitement);
		this.tampon=tampon;
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
			MessageX m = new MessageX(this, i, "patate", new Date());
			
			int tempAttente= temp_prod.next();
			
			try {
				sleep(tempAttente);
			} catch (InterruptedException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			}
			
			try {
				tampon.put(this, m);
				nbMessagesProduits++;
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
	
	/**
	 * A utiliser pour les tests de fin d'execution. permet de savoir si un producteur a produit tous ses messages.
	 * @return booleen assurant que chaque message créé a été déposé.
	 */
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
