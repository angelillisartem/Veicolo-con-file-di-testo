public class Cliente {
    private String nome;
    private String cognome;
    private double budget;              //prepagata per noleggio
    private String veicoloNoleggiato;   //targa del veicolo noleggiato (dop il noleggio e prima del pagamento)

    public Cliente(String nome, String cognome, double budget) {        //costruttore
        this.nome = nome;
        this.cognome = cognome;
        this.budget = budget;
        this.veicoloNoleggiato=null;
    }

    //getter e setter

    public String getNome() {
        return nome;
    }


    public String getCognome() {
        return cognome;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getVeicoloNoleggiato() {
        return veicoloNoleggiato;
    }

    public void setVeicoloNoleggiato(String veicoloNoleggiato) {
        this.veicoloNoleggiato = veicoloNoleggiato;
    }
}
