package prodcons.v6;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class Observations {
	ArrayList<Message> l;
	int init_nb_prod;
	int init_nb_conso;
	int init_nb_buffers;
	int nb_conso;
	int nb_prod;
	ArrayList<Producteur> p;
	ArrayList<Consommateur> c;
	ArrayList<Message> m;
	
	public  Observations () {
		l= new ArrayList<Message>();
		p= new ArrayList<Producteur>();
		c= new ArrayList<Consommateur>();
		m= new ArrayList<Message>();
	}
	//a l'initialisation du systeme.
	public  void init(int nbProducteurs, int nbConsommateurs, int nbBuffers){
		this.init_nb_buffers=nbBuffers;
		this.init_nb_conso=nbConsommateurs;
		this.init_nb_prod=nbProducteurs;
	}
	//lorsqu'un nouveau producteur P est créé,
	public void newProducteur(Producteur P){
		this.p.add(P);
		//TODO verifier que le prod est valide
	}
	// lorsqu'un nouveau consommateur C est créé,
	public void newConsommateur(Consommateur C) {
		this.c.add(C);
		//TODO verifier que le conso est valide
	}

	//lorsqu'un producteur P produit un nouveau message M avec un délai de production de T,
	public void productionMessage(Producteur P, Message M, int T){
		m.add(new MessageX(P, 0, "", new Date()));
		//recuperer le delai de prod 
		//dire que le prod a recuperer un message;
	}

	//lorsqu'un consommateur C consomme un message M avec un délai de T,
	public void consommationMessage(Consommateur C, Message M, int T){
		
	}

	//lorsqu'un message M est déposé dans le tampon par le producteur P,
	public void depotMessage(Producteur P, Message M){
		
	}
	
	//lorsqu'un message M est retiré du tampon par le consommateur C.
	public void retraitMessage(Consommateur C, Message M){
		
	}
	
	public boolean bon_nombre_conso(){
		return this.c.size()==this.init_nb_conso;
	}
	
	public boolean bon_nombre_prod(){
		return this.p.size()==this.init_nb_prod;
	}
	
	//TODO
	//tout les messages creer sont deposes 
	//tout les messages lu sont traites
	//tout les messages deposes sont lu
	//respect des loi temporel
	//les messages sont lu dans le meme ordre qu'ils deposer
	//Les messages sont creer avant d'etre deposer
	//Les messages sont deposer avant d'etre lu
	//les messages sont lu avant d'etre traiter
	
}
