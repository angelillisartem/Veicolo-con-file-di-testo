public abstract class Veicolo {
    private String targa;
    private double kmPercorsi;  // come segnato sul contakm
    private boolean libero; //veicolo libero o noleggiato
    private int litBenzina;


    public Veicolo(String targa,  double kmPercorsi) {
        this.targa = targa;
        this.kmPercorsi = kmPercorsi;   //contakm alla creazione
        this.libero = true;             //quando inserisco un veicolo nel parco macchine non è ancora noleggiato
        this.litBenzina = litBenzina;
    }

    public abstract int paga (Cliente c, double km, int ore, int lt);       //metodo astratto per il pagamento (da sovrascrivere)

    public void noleggia(Cliente c){        //metodo concreto per il noleggio ad un cliente
        if(this.isLibero()) {
            this.setLibero(false);  //cambia stato da true a false
            c.setVeicoloNoleggiato(this.getTarga()); //assegna il valore (String) della targa all'attributo veicoloNoleggiato del cliente
        }
        else
            System.out.println("Veicolo già occupato");
    }


    // getter e setter

    public String getTarga() {
        return targa;
    }

    public double getKmPercorsi() {
        return kmPercorsi;
    }

    public boolean isLibero() {
        return libero;
    }

    public int getLitBenzina() {
        return litBenzina;
    }

    public void setKmPercorsi(double kmPercorsi) {
        this.kmPercorsi = kmPercorsi;
    }

    public void setLibero(boolean libero) {
        this.libero = libero;
    }
}
