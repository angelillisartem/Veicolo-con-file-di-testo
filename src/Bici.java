import java.util.Scanner;

public class Bici extends Veicolo{
    private int stato;  //stato della bici alla restituzione (0 ottimo -1 peggiorato 2 molto peggiorato)

    private Scanner in= new Scanner(System.in);

    public Bici(String targa,  double kmPercorsi) {
        super(targa, kmPercorsi);
        this.stato=0;
    }

    @Override
    public int paga(Cliente c,double km, int ore, int lt) {
        int prezzo=0;
        prezzo= (int) (2* km + 5 * ore + 10 * this.stato); //stato: multa assegnata se la bici torna in uno stato peggiore
        if(c.getBudget()< prezzo) { //credito insufficiente
            System.out.println("Non hai abbastanza budget per pagare. Ricarica la tua prepagata");
            prezzo=-1;}
        else {  //credito sufficiente
            System.out.println("Budget attuale: "+ c.getBudget());
            double newBudget= c.getBudget()- prezzo; //aggiorna il Budget del cliente sottraendo il prezzo del noleggio
            c.setBudget(newBudget) ;    //aggiornamento budget
        }
        return prezzo;
    }

    public int getStato() {
        return stato;
    }
    public void setStato(int stato) {
        this.stato = stato;
    }
}
