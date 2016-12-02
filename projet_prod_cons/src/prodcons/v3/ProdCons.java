package prodcons.v3;



import jus.poc.prodcons.*;
public class ProdCons implements Tampon {
	
	private int taille;// taille du buffer
	private Message tampon[];// buffer
	private int index_lecture;// indice de la zone du tampon ou il faut lire
	private int index_ecriture;// indice de la zone du tamtpon ou il faut ecrire
	private int enAttente;// nombre de message present dans le tampon
	private Semaphore notFull;
	private Semaphore notEmpty;
	private Semaphore mutex;
	//private Condition notEmpty, notFull;
	
	
	// constructeur 
	public ProdCons(int taille){
		this.taille=taille;
		this.tampon=new Message[taille];
		this.index_lecture=0;
		this.index_ecriture=0;
		this.enAttente=0;
		notFull= new Semaphore(taille);
		notEmpty= new Semaphore(0);
		mutex= new Semaphore(1);
	}

	@Override
	public int enAttente() {
		return this.enAttente;
	}

	@Override
	public synchronized Message get(_Consommateur arg0) throws Exception, InterruptedException {
		// if buffer is empty wait until it contains a messages
		notEmpty.P();
		mutex.P(); 
		Message m = tampon[index_lecture];
		index_lecture= (index_lecture+1)%taille;
		mutex.V();
		notFull.V();
		/*notFull.signal()*/ notifyAll();
		return m;
	}

	@Override
	public  void put(_Producteur arg0, Message arg1) throws Exception, InterruptedException {
		// if the buffer is full wait untill it become empty
		notFull.P();
		mutex.P();
		tampon[index_ecriture]= arg1;
		index_ecriture = (index_ecriture +1)%taille;
		mutex.V();
		notEmpty.V();
	}

	@Override
	public int taille() {
		return this.taille;
	}

	

}
