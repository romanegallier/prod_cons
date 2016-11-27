package projet_prod_cons;

import java.util.concurrent.locks.Condition;

import jus.poc.prodcons.*;
public class ProdCons implements Tampon {
	
	private int taille;
	private Message tampon[];
	private int index_lecture;
	private int index_ecriture;
	private int enAttente;
	private Condition notEmpty, notFull;
	
	public ProdCons(int taille){
		this.taille=taille;
		this.tampon=new Message[taille];
		this.index_lecture=0;
		this.index_ecriture=0;
		this.enAttente=0;
	}

	@Override
	public int enAttente() {
		return this.enAttente;
	}

	@Override
	public synchronized Message get(_Consommateur arg0) throws Exception, InterruptedException {
		if (enAttente==0){
			notEmpty.wait();
		}
		index_lecture= index_lecture+1%taille;
		enAttente --;
		notFull.signal();
		return null;
	}

	@Override
	public synchronized void put(_Producteur arg0, Message arg1) throws Exception, InterruptedException {
		if (enAttente==taille){
			notFull.wait();
		}
		tampon[index_ecriture]= arg1;
		index_ecriture= index_ecriture+1%taille;
		enAttente++;
		notEmpty.signal();
	}

	@Override
	public int taille() {
		return this.taille;
	}

}
