package prodcons.v1 ;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class Consommateur extends Acteur implements _Consommateur  {

	private int nbMessage;
	private Tampon tampon;
	private Aleatoire temp_traitement;
	private ArrayList <Message> message_lu;

	protected Consommateur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement, Tampon tampon) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		// TODO verifier le type
		this.tampon=tampon;
		this.nbMessage=0;
		this.temp_traitement=new Aleatoire(moyenneTempsDeTraitement,deviationTempsDeTraitement);
		this.message_lu= new ArrayList<Message>();
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
		MessageX m = new MessageX(null,0,null,null);
		int temp_attente;
		while (!(((ProdCons)tampon).cons_should_die())){
			try {
			
				m=(MessageX) tampon.get(this);
//				m.set_date_retrait(new Date());

				je_parle("je viens de get le message : "+m.toString());
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
				sleep(temp_attente);
			} catch (InterruptedException e) {
				System.out.println("J'ai pas reussi a attendre ...\n");
				e.printStackTrace();
			}
			
			m.set_date_consommation(new Date());
			
		}
		System.out.println("Je suis le "+this.toString()+" et je me meurt ... arghhhh\n");
	}
	public static int Cons (){
		return Acteur.typeConsommateur;
	}

	public String toString (){
		return "Consommateur "+this.identification();
	}
	
	public ArrayList<Message> get_message_lu() {
		return message_lu;
	}
	
	public void je_parle(String message)
	{
		System.out.println(MessageX.Format_HeureMinuteSeconde(new Date()) +this.toString()+"\t"+ message);
	}
}
