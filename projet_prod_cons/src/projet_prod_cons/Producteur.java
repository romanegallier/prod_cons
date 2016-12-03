package projet_prod_cons;

import java.util.Date;

import jus.poc.prodcons.*;

public class Producteur extends Acteur implements _Producteur  {

	private int nbMessage;
	private Aleatoire temp_prod;
	private Tampon tampon;
	
	protected Producteur(int type, Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement,Tampon tampon) throws ControlException {
		
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nbMessage=Aleatoire.valeur(3,2);// TODO a changer
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
			System.out.println(this.toString()+nbMessage +":"+i);
			MessageX m = new MessageX(this, i, "patate", new Date());
			try {
				tampon.put(this, m); //TODO à changer, random
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			int tempAttente= temp_prod.next();
			try {
				sleep(tempAttente);
			} catch (InterruptedException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			}
			
		}
		((ProdCons) tampon).fin_prod();
		
	}
	
	public static int Prod (){
		return Acteur.typeProducteur;
	}
	
	public String toString (){
		return "Producteur "+this.identification()+"\n";
	}
}
