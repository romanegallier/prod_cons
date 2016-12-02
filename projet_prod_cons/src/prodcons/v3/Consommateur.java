package prodcons.v3 ;

import jus.poc.prodcons.*;

public class Consommateur extends Acteur implements _Consommateur  {

	private int nbMessage;
	private Tampon tampon;
	private Aleatoire temp_traitement;
	private Observateur obs;

	protected Consommateur(int type, Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement, Tampon tampon) throws ControlException {
		super(type, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		// TODO verifier le type
		this.tampon=tampon;
		this.obs= observateur;
		this.nbMessage=0;
		this.temp_traitement=new Aleatoire(moyenneTempsDeTraitement,deviationTempsDeTraitement);
		
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
		Message m= new MessageX(null, 0, "", 0);
		int temp_attente;
		while (true ){
			try {
			
				m=tampon.get(this);
				obs.retraitMessage(this, m);
				System.out.println("m:"+m.toString());
				nbMessage++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("petit probleme1\n");
				e.printStackTrace();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("petit probleme2\n");
				e.printStackTrace();
				
			}
		
			
			temp_attente=this.temp_traitement.next();
			try {
				obs.consommationMessage(this, m, temp_attente);
				sleep(temp_attente);
			} catch (InterruptedException | ControlException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			}
			
		}
	}
	public static int Cons (){
		return Acteur.typeConsommateur;
	}

	public String toString (){
		return "Consommateur "+this.identification()+"\n";
	}
}
