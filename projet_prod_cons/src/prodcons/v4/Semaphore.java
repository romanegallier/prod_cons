package prodcons.v4;



public class Semaphore {
//dkrhgm
	int compteur;
	
	public Semaphore (int compteur){
		this.compteur= compteur;
	}
	
	public synchronized  void P(){
		compteur --;
		if (compteur <0) {
			// no more free resources
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public synchronized void V(){
		compteur++;
		if (compteur <=0){
			//at least 1 waiting process
			notify();
			
		}
	}
	
}
