public class Auto extends Veicolo {
    private int litBenzina;

    public Auto(String targa, double kmPercorsi, int litBenzina) {
        super(targa, kmPercorsi);
        this.litBenzina = litBenzina;
    }

    @Override
    public int paga(Cliente c,double km, int ore, int lt) {
        int prezzo=0;
        prezzo= (int) (8* km + 10*ore +  5*lt);
        System.out.println("Costo noleggio: "+ prezzo);
        if(c.getBudget()< prezzo){  //credito insufficiente
            System.out.println("Non hai abbastanza budget per pagare. Ricarica la tua prepagata");
            prezzo= -1;
        } else{     //credito sufficiente
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
