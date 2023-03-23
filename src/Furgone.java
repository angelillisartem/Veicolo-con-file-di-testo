public class Furgone extends Veicolo{
    private int litBenzina;
    private int portata; // int che puo' variare da 1 a 3 (tonnellate)

    public Furgone(String targa, double kmPercorsi, int litBenzina, int portata) {
        super(targa,  kmPercorsi);
        this.litBenzina = litBenzina;
        this.portata=portata;
    }

    @Override
    public int paga(Cliente c,double km, int ore, int lt)  {    //calcolo del prezzo
        int prezzo=0;
        prezzo= (int) (12* km + 20*ore+ 8*lt+ 2*this.portata);
        if(c.getBudget()< prezzo){  //credito insufficiente
            System.out.println("Non hai abbastanza budget per pagare. Ricarica la tua prepagata");
            prezzo=-1;
        }
        else {  //credito sufficiente
            System.out.println("Budget attuale: "+ c.getBudget());
            double newBudget= c.getBudget()- prezzo; //aggiorna il Budget del cliente sottraendo il prezzo del noleggio
            c.setBudget(newBudget) ;    //aggiornamento budget
        }

        return prezzo;
    }

    public int getLitBenzina() {
        return litBenzina;
    }

    public void setLitBenzina(int litBenzina) {
        this.litBenzina = litBenzina;
    }
}
