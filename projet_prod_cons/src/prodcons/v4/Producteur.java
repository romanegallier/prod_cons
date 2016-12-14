package prodcons.v4;

import java.util.Date;

import jus.poc.prodcons.*;

public class Producteur extends Acteur implements _Producteur  {

	private int nbMessage;
	private Aleatoire temp_prod;
	private Tampon tampon;
	private Observateur obs;
	private Aleatoire nbExemplaires;
	
	protected Producteur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement,Tampon tampon, int nbMoyenProduction,
			int deviationNbProduction, int nbMoyenExemplaires, int deviationNbExemplaires) throws ControlException {
		
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nbMessage= new Aleatoire(nbMoyenProduction, deviationNbProduction).next();
		temp_prod= new Aleatoire(moyenneTempsDeTraitement,deviationTempsDeTraitement);
		this.tampon=tampon;
		nbExemplaires = new Aleatoire(nbMoyenExemplaires,deviationNbExemplaires);
		obs=observateur;
		obs.newProducteur( this);

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
			MessageX m = new MessageX(this, i, "patate", new Date(),nbExemplaires.next());
			
			int tempAttente= temp_prod.next();
			
			
			try {
				obs.productionMessage(this, m, tempAttente);
				sleep(tempAttente);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ControlException e) {
				e.printStackTrace();
			}
			
			try {
				obs.depotMessage(this, m);
				tampon.put(this, m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			};
			
			
			
		}
		((ProdCons) tampon).fin_prod();
		
	}
	
	public static int Prod (){
		return Acteur.typeProducteur;
	}
		
	
	public String toString (){
		return "Producteur "+this.identification();
	}
	
	public void je_parle(String message)
	{
		System.out.println(MessageX.Format_HeureMinuteSeconde(new Date()) +this.toString()+"\t"+ message);
	}
}
