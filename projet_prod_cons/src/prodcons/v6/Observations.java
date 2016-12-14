package prodcons.v6;

import java.util.ArrayList;
import java.util.Date;

import jus.poc.prodcons.*;

public class Observations {
	private ArrayList<Message> l;
	private int init_nb_prod;
	private int init_nb_conso;
	private int init_nb_buffers;
	private int nb_conso;
	private int nb_prod;

	private ArrayList<Producteur> p;
	private ArrayList<Consommateur> c;
	private ArrayList<Message> produit;
	private ArrayList<Message> depose;
	private ArrayList<Message> retrait;
	private ArrayList<Message> consomme;
	
	public  Observations () {
		l= new ArrayList<Message>();
		p= new ArrayList<Producteur>();
		c= new ArrayList<Consommateur>();
		produit= new ArrayList<Message>();
		depose= new ArrayList<Message>();
		retrait= new ArrayList<Message>();
		consomme= new ArrayList<Message>();
	}
	//a l'initialisation du systeme.
	public  void init(int nbProducteurs, int nbConsommateurs, int nbBuffers){
		this.init_nb_buffers=nbBuffers;
		this.init_nb_conso=nbConsommateurs;
		this.init_nb_prod=nbProducteurs;
	}
	//lorsqu'un nouveau producteur P est créé,
	public void newProducteur(Producteur P) throws ObservationExeption{
		if (P==null){ 
			throw new ObservationExeption();
		}
		this.p.add(P);
		
	}
	
	// lorsqu'un nouveau consommateur C est créé,
	public void newConsommateur(Consommateur C) throws ObservationExeption {
		if (C==null){ 
			throw new ObservationExeption();
		}
		this.c.add(C);
		
	}

	//lorsqu'un producteur P produit un nouveau message M avec un délai de production de T,
	public void productionMessage(Producteur P, Message M, int T) throws ObservationExeption{
		if (P==null) throw new ObservationExeption();
		// si le producteur n'as pas ete creer
		if (p.indexOf(P)==-1)throw new ObservationExeption();
		// si le message est nul
		if (M==null)throw new ObservationExeption();
		produit.add(M); 
	}

	//lorsqu'un message M est déposé dans le tampon par le producteur P,
	public void depotMessage(Producteur P, Message M) throws ObservationExeption{
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
	public void retraitMessage(Consommateur C, Message M) throws ObservationExeption{
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
		public void consommationMessage(Consommateur C, Message M, int T) throws ObservationExeption{
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
	
	public boolean bon_nombre_conso(){
		return this.c.size()==this.init_nb_conso;
	}
	
	public boolean bon_nombre_prod(){
		return this.p.size()==this.init_nb_prod;
	}
	
	//verifie que l'execution c'est bien passe 
	public boolean verification (){
		boolean b= true;
		//tous les message produit sont deposes
		if (produit.size()!=0)b=false;
		//tout les messages deposes sont lu		
		if (depose.size()!=0)b=false; 
		//tout les messages lu sont traites
		if (retrait.size()!=0)b=false;
		
		if (!bon_nombre_conso())b=false;
		if (!bon_nombre_prod())b=false;
		return b;
	}
		
		




 
 

}
