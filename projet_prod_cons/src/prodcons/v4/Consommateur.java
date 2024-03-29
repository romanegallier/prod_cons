package prodcons.v4 ;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;


public class Consommateur extends Acteur implements _Consommateur  {

	private int nbMessage;
	private Tampon tampon;
	private Aleatoire temp_traitement;
	private Observateur obs;
	private ArrayList <Message> message_lu;

	protected Consommateur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement, Tampon tampon) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.tampon=tampon;
		this.obs= observateur;
		obs.newConsommateur(this);
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
		MessageX m = new MessageX(null,0,null,null,0);
		int temp_attente;
		boolean b= true;
		while (b){
			try {
			
				m=(MessageX) tampon.get(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}catch (FinProgExeption e){
				b=false;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (b){
				try {
					obs.retraitMessage(this, m);
				} catch (ControlException e1) {
					e1.printStackTrace();
				}
				nbMessage++;
				temp_attente=this.temp_traitement.next();
				try {
					obs.consommationMessage(this, m, temp_attente);
					sleep(temp_attente);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ControlException e) {
					e.printStackTrace();
				}
				
				m.set_date_consommation(new Date());
			}
		}
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
