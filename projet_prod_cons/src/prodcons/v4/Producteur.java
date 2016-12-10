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
		
		for (int i=0; i <nbMessage; i++){
			System.out.println(this.toString()+nbMessage +":"+i);
			// On produit le message
			MessageX m = new MessageX(this, i, "patate", new Date(), nbExemplaires.next());
			
//TODO A CHANGER DE PARTOUT
			// On attend pour simuler le temp de prod
			int tempAttente= temp_prod.next();
			//On informe l'observateur
			try {
				obs.productionMessage(this, m, tempAttente);
				sleep(tempAttente);
			} catch (InterruptedException | ControlException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			}

			
			try {
				obs.depotMessage(this, m);
				tampon.put(this, m);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
						
		}
		
	}
	
	public static int Prod (){
		return Acteur.typeProducteur;
	}
	
	public String toString (){
		return "Producteur "+this.identification()+"\n";
	}
}
