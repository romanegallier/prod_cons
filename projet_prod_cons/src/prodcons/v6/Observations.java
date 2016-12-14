package prodcons.v6;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class Observations {
	private int init_nb_prod;
	private int init_nb_conso;
	private int init_nb_buffers;
	private boolean aff=true;

	private ArrayList<Producteur> p;
	private ArrayList<Consommateur> c;
	private ArrayList<Message> produit;
	private ArrayList<Message> depose;
	private ArrayList<Message> retrait;
	private ArrayList<Message> consomme;
	
	public  Observations () {
		p= new ArrayList<Producteur>();
		c= new ArrayList<Consommateur>();
		produit= new ArrayList<Message>();
		depose= new ArrayList<Message>();
		retrait= new ArrayList<Message>();
		consomme= new ArrayList<Message>();
	}
	//a l'initialisation du systeme.
	public synchronized void init(int nbProducteurs, int nbConsommateurs, int nbBuffers){
		this.init_nb_buffers=nbBuffers;
		this.init_nb_conso=nbConsommateurs;
		this.init_nb_prod=nbProducteurs;
	}
	//lorsqu'un nouveau producteur P est créé,
	public synchronized  void newProducteur(Producteur P) throws ObservationExeption{
		if (P==null){ 
			if (aff)System.out.println("On essaye de creer un nouveau producteur null");
			throw new ObservationExeption();
		}
		this.p.add(P);
		
	}
	
	// lorsqu'un nouveau consommateur C est créé,
	public  synchronized void newConsommateur(Consommateur C) throws ObservationExeption {
		if (C==null){ 
			if(aff) System.out.println("On essaye de creeer un nouveau consommateur null");
			throw new ObservationExeption();
		}
		this.c.add(C);
		
	}

	//lorsqu'un producteur P produit un nouveau message M avec un délai de production de T,
	public  synchronized void productionMessage(Producteur P, Message M, int T) throws ObservationExeption{
		if (P==null) throw new ObservationExeption();
		// si le producteur n'as pas ete creer
		if (p.indexOf(P)==-1)throw new ObservationExeption();
		// si le message est nul
		if (M==null)throw new ObservationExeption();
		produit.add(M); 
	}

	//lorsqu'un message M est déposé dans le tampon par le producteur P,
	public  synchronized void depotMessage(Producteur P, Message M) throws ObservationExeption{
		if (P==null) throw new ObservationExeption();
		//si le producteur n'a spas ete cree
		if (p.indexOf(P)==-1)throw new ObservationExeption();
		if (M==null)throw new ObservationExeption();
		int i = produit.indexOf(M);
		
		if (i==-1){ //le message n'a pas ete produit
			throw new ObservationExeption();
		}
		
		depose.add(M);
		if (depose.size()>this.init_nb_buffers)throw new ObservationExeption();
		produit.remove(i);
	}
	
	
	
	
	//lorsqu'un message M est retiré du tampon par le consommateur C.
	public  synchronized void retraitMessage(Consommateur C, Message M) throws ObservationExeption{
		if (C==null) throw new ObservationExeption();
		if (c.indexOf(C)==-1)throw new ObservationExeption();
		if (M==null)throw new ObservationExeption();
		int i = depose.indexOf(M);
		if (i==-1){ //le message n'a pas ete depose
			throw new ObservationExeption();
		}
		if (i!=0){// on essaie pas de retire le premier message du tampon
			throw new ObservationExeption();
		}
		depose.remove(i);
		retrait.add(M);
	}
	
	
	
	//lorsqu'un consommateur C consomme un message M avec un délai de T,
		public  synchronized void consommationMessage(Consommateur C, Message M, int T) throws ObservationExeption{
			if (C==null) throw new ObservationExeption();
			if (c.indexOf(C)==-1)throw new ObservationExeption();
			if (M==null)throw new ObservationExeption();
			int i = retrait.indexOf(M);
			if (i==-1){ //le message n'a pas ete depose
				throw new ObservationExeption();
			}
			
			retrait.remove(i);
			consomme.add(M);
		}
	
	public  synchronized boolean bon_nombre_conso(){
		return this.c.size()==this.init_nb_conso;
	}
	
	public  synchronized boolean bon_nombre_prod(){
		return this.p.size()==this.init_nb_prod;
	}
	
	//verifie que l'execution c'est bien passe 
	public  synchronized boolean verification (){
		boolean b= true;
		//tous les message produit sont deposes
		if (produit.size()!=0){
			b=false;
			if (aff)System.out.println("Des messages sont produit et non deposes");
		}
		//tout les messages deposes sont lu		
		if (depose.size()!=0){
			b=false;
			if (aff)System.out.println("des messages sont deposes et non lu");
		}
		//tout les messages lu sont traites
		if (retrait.size()!=0){
			b=false;
			if (aff)System.out.println("Des messages sont lu mais non traites");
		}
		
		if (!bon_nombre_conso()){
			b=false;
			System.out.println("nb_conso="+this.init_nb_conso+": longeur de la liste:"+ c.size());
			if (aff)System.out.println("Les conso demandes n'ont pas ete tous initialises");
		}
		if (!bon_nombre_prod()){
			b=false;
			if (aff)System.out.println("Le bon nombre de producteur n'as pas ete instanciée");
		}
		return b;
	}
		
	public  synchronized void set_aff(boolean b){
		this.aff=b;
	}
		




 
 

}
