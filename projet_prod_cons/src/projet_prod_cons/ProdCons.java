package projet_prod_cons;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jus.poc.prodcons.*;
public class ProdCons implements Tampon {
	
	private int taille;// taille du buffer
	private Message tampon[];// buffer
	private int index_lecture;// indice de la zone du tampon ou il faut lire
	private int index_ecriture;// indice de la zone du tamtpon ou il faut ecrire
	private int enAttente;// nombre de message present dans le tampon
	//private Condition notEmpty, notFull;
	private int nb_prod_alive;
	private int  num=0;
	private int  num2=0;
	
	private List<Message> l_messages;
	
	// constructeur 
	public ProdCons(int taille){
		this.taille=taille;
		this.tampon=new Message[taille];
		this.index_lecture=0;
		this.index_ecriture=0;
		this.enAttente=0;
		this.nb_prod_alive=0;
		l_messages = new LinkedList<Message>();
	}
	

	public synchronized void nv_prod (){
		nb_prod_alive ++;
	}
	
	public synchronized void fin_prod(){
		nb_prod_alive --;
		if (cons_should_die()) notifyAll();
	}
	public synchronized boolean  cons_should_die (){
		return (nb_prod_alive==0) && (enAttente==0);
	}
	@Override
	public int enAttente() {
		return this.enAttente;
	}

	@Override
	public synchronized Message get(_Consommateur arg0) throws Exception, InterruptedException {
		while (enAttente==0){
			/*notEmpty.*/wait();
		}
		Message m = tampon[index_lecture];
		num2++;
		((MessageX)m).set_num2(num2);
		index_lecture= (index_lecture+1)%taille;
		enAttente --;
		((MessageX) m).set_date_retrait(new Date());
		System.out.println("Je get le message " + num2);
		/*notFull.signal()*/ notifyAll();
		return m;
	}

	@Override
	public synchronized void put(_Producteur arg0, Message arg1) throws Exception, InterruptedException {
		while (enAttente==taille){
			/*notFull.*/wait();
		}
		num++;
		((MessageX)arg1).set_num(num);
		tampon[index_ecriture]= arg1;
		index_ecriture= (index_ecriture+1)%taille;
		enAttente++;
		l_messages.add(arg1);
		((MessageX) arg1).set_date_envoi(new Date());
		System.out.println("Je put le message "+num);
		/*notEmpty.signal()*/ notifyAll();
	}

	@Override
	public int taille() {
		return this.taille;
	}
	
	@Override
	public String toString()
	{
		String res = "[";
		char occupe = 'X';
		char libre = ' ';
		if (index_lecture > index_ecriture)
		{
			occupe = ' ';
			libre = 'X';
		}
		
		for(int i = 0; i < index_lecture ; i++)
		{
			res += libre + ",";
		}
		for(int i = index_lecture; i < index_ecriture ; i++)
		{
			res += occupe +",";
		}
		for (int i = index_ecriture ; i < taille ; i++)
		{
			res += libre + ",";
		}
		return res += "]";
	}
	
	
	
	//tests à lancer à la fin du processus
	

	public void tests_temporels(Date date_arret)
	{
		//Booleens vérifiant les différents tests.
		boolean test_fifo_valide = true;
		boolean test_messages_consommes = true;
		boolean test_depot_avant_retrait = true;
		
		
		//Dates de dépôt et retrait
		Date dated = null; 
		Date dater = null;  
		Date datec = null;
		
		System.out.println("Test : On affiche les temps de dépôt et retrait de chaque message :");
		for(Message m : l_messages)
		{
	
			//on vérifie si le test sur l'ordre de retrait des messages est valide en regardant si le message est retiré après le précédent.
			if(m != l_messages.get(0) && dater.after(((MessageX) m).get_date_retrait()))
			{
					test_fifo_valide = false;
					System.out.println("*");
			}

			//Si les messages ne sont pas déposés selon le même ordre que la liste, ce n'est pas normal
			if(m != l_messages.get(0) && !dated.before(((MessageX) m).get_date_envoi()))
			{
				System.out.println("La liste de messages n'est pas construite comme prévu !");
			}

				
			dated = ((MessageX) m).get_date_envoi();			
			dater = ((MessageX) m).get_date_retrait();
			datec = ((MessageX) m).get_date_consommation();
			
			
			//On vérifie que chaque message a été déposé avant d'être retiré
			if(dated.after(dater))
			{
				test_depot_avant_retrait = false;
				System.out.println("***");
			}
			
			
			//On vérifie que chaque message est consommé avant la fin du programme
			if(datec.after(date_arret))
			{
				test_messages_consommes = false;
				System.out.println("**  Date arret = " + MessageX.Format_HeureMinuteSeconde(date_arret));
			}
			
			
			String s_dated = MessageX.Format_HeureMinuteSeconde(dated);
			String s_dater = MessageX.Format_HeureMinuteSeconde(dater);

			System.out.println("  dépôt : " + s_dated+ "    retrait : " + s_dater);
		}
		
		System.out.println("fifo : Chaque message a-t-il été retiré dans l'ordre où il a été déposé ?");
		if(test_fifo_valide) 
		{
			System.out.println("Le test a bien été validé.\n\n");
		}
		else System.out.println("Le test n'a pas été validé.\n\n");
		
		System.out.println("Exécution complète : Chaque message produit a-t-il été consommé avant la fin du programme ?");
		if(test_messages_consommes)
		{
			System.out.println("Le test a bien été validé.\n\n");
		}
		else
		{
			System.out.println("Le test n'a pas été validé.\n\n");
		}
		
		System.out.println("Dépot avant retrait : Chaque message produit a-t-il été retiré par un consommateur ?");
		if(test_depot_avant_retrait)
		{
			System.out.println("Le test a bien été validé.\n\n");
		}
		else
		{
			System.out.println("Le test n'a pas été validé.\n\n");
		}
	}
	
	

}
