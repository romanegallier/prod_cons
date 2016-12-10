package projet_prod_cons;

import java.util.Date;

import jus.poc.prodcons.*;

public class Producteur extends Acteur implements _Producteur  {

	private int nbMessage;
	private Aleatoire temp_prod;
	private Tampon tampon;
	
	protected Producteur(Observateur observateur, int moyenneTempsDeTraitement,
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
//				je_parle("pre put le tampon a "+tampon.enAttente()+ " message(s) en attente et est de taille "+tampon.taille());
//				System.out.println("pre put : tampon : " + ((ProdCons) tampon).toString());
				tampon.put(this, m);
				m.set_date_envoi(new Date());
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
	
	public String toString (){
		return "Producteur "+this.identification();
	}
	
	public void je_parle(String message)
	{
		System.out.println(MessageX.Format_HeureMinuteSeconde(new Date()) +this.toString()+"\t"+ message);
	}
}
