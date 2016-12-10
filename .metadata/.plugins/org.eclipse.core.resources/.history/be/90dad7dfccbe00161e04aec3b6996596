package projet_prod_cons;


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
	
	// constructeur 
	public ProdCons(int taille){
		this.taille=taille;
		this.tampon=new Message[taille];
		this.index_lecture=0;
		this.index_ecriture=0;
		this.enAttente=0;
		this.nb_prod_alive=0;
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

}
