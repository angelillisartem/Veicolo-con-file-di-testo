import java.util.*;
import java.io.*;

public class Noleggio {
    public static ArrayList <Veicolo> mioNoleggio;      // variabile per parco veicoli
    public static ArrayList <Cliente> listaClienti;     // variabile per parco clienti
    public static Scanner in;

    public static void main(String[] args){
        in = new Scanner(System.in);
        int scelta = 0;                                   //  scelta menu
        mioNoleggio = new ArrayList<>();                  // istanzio parco veicoli
        listaClienti = new ArrayList<>();                 // istanzio parco clienti

        do {
            System.out.print("\nBuongiorno, cosa vuoi fare?");
            System.out.print("\n1) Inserisci Veicolo");
            System.out.print("\n2) Inserisci Cliente");
            System.out.print("\n3) Noleggia Veicolo");
            System.out.print("\n4) Registra Pagamento");
            System.out.print("\n5) Ricarica Prepagata");
            System.out.print("\n6) Cerca cliente e verifica noleggio auto");
            System.out.print("\n7) Esci");
            System.out.print("");
            System.out.print("\nScelta: ");

            scelta = in.nextInt();
            in.nextLine();
            String targa, nome, cognome;
            double budget;

            switch (scelta){
                case 1: //aggiunge un veicolo
                    System.out.print("\nChe veicolo vuoi inserire?");
                    System.out.print("\n1) Bici");
                    System.out.print("\n2) Auto");
                    System.out.print("\n3) Furgone");
                    System.out.print("");
                    System.out.print("\nScelta: ");
                    int tipoVeicolo = in.nextInt();
                    in.nextLine();

                    //dati comuni
                    System.out.print("Inserisci targa: ");
                    targa = in.nextLine();
                    System.out.println("Inserisci i km del veicolo: ");
                    double kmVeicolo = in.nextDouble();
                    in.nextLine();

                    int litVeicolo = 0;
                    int portata = 0;

                    //auto o furgone (oppure scelta sbagliata)
                    if (tipoVeicolo > 1 && tipoVeicolo < 4){
                        System.out.print("Inserisci i lit di benzina del veicolo: ");
                        litVeicolo = in.nextInt();
                        in.nextLine();
                        if (tipoVeicolo == 2) //aggiungo auto
                            mioNoleggio.add(new Auto(targa,kmVeicolo,litVeicolo)); //istanzia nuovo auto e lo aggiunge alla lista dei veicoli
                        else if (tipoVeicolo == 3)  {  //aggiungo furgone
                            System.out.println("Inserisci la portata del furgone (da 1 a 3 tonnellate): "); //chiedo la portata del furgone
                            portata=in.nextInt();
                            in.nextLine();
                            mioNoleggio.add(new Furgone(targa,kmVeicolo,litVeicolo, portata));  //istanzia nuovo furgone e lo aggiunge alla lista dei veicoli
                        }
                    } else if (tipoVeicolo == 1) {    //aggiungo bici
                        mioNoleggio.add(new Bici(targa,kmVeicolo));                     //istanzia nuovo bici e lo aggiunge alla lista dei veicoli
                    } else
                        System.out.print("Scelta sbagliata");

                    Veicolo auto = new Auto(targa, kmVeicolo, litVeicolo);
                    Veicolo furgone = new Furgone(targa, kmVeicolo, litVeicolo, portata);
                    Veicolo bici = new Bici(targa, kmVeicolo);

                    // Crea un nuovo file di testo e scrivi il nome e il cognome al suo interno
                    try {
                        FileWriter fileWriter = new FileWriter("src/DatiVeicolo.txt", true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        printWriter.println(auto.getTarga() + " " + auto.getKmPercorsi() + " " + auto.getLitBenzina());
                        printWriter.close();
                        printWriter.println(furgone.getTarga() + " " + furgone.getKmPercorsi() + " " + furgone.getLitBenzina());
                        printWriter.close();
                        printWriter.println(bici.getTarga() + " " + bici.getKmPercorsi());
                        printWriter.close();
                        System.out.print("\nI tuoi dati sono stati salvati nel file DatiVeicolo.txt.");
                    } catch (IOException e) {
                        System.out.print("\nSi è verificato un errore durante la scrittura del file.");
                        e.printStackTrace();
                    }
                    break;

                case 2: //aggiungi cliente
                    System.out.print("Inserisci nome: ");
                    String nomeCliente = in.nextLine();
                    System.out.print("Inserisci cognome: ");
                    String cognomeCliente = in.nextLine();
                    System.out.print("Inserisci budget: ");
                    budget = in.nextDouble();

                    listaClienti.add(new Cliente(nomeCliente,cognomeCliente, budget)); //istanzia nuovo cliente e lo aggiunge alla lista dei clienti

                    Cliente cliente1 = new Cliente(nomeCliente, cognomeCliente, budget);

                    // Crea un nuovo file di testo e scrivi: nome, cognome e il budget del cliente
                    try {
                        FileWriter fileWriter = new FileWriter("src/DatiCliente.txt", true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        printWriter.println(cliente1.getNome() + " " + cliente1.getCognome() + " " + cliente1.getBudget());
                        printWriter.close();
                        System.out.print("\nI tuoi dati sono stati salvati nel file DatiCliente.txt.");
                    } catch (IOException e) {
                        System.out.print("\nSi è verificato un errore durante la scrittura del file.");
                        e.printStackTrace();
                    }
                    break;

                case 3: //noleggia un veicolo a un cliente
                    // leggi il percorso del file e la targa del veicolo dall'input dell'utente
                    Scanner scannerNoleggio = new Scanner(System.in);
                    System.out.print("\nInserisci il percorso del file: ");
                    String filePathNoleggio = scannerNoleggio.nextLine();
                    System.out.print("\nInserisci la targa del veicolo: ");
                    String cercaTarga = scannerNoleggio.nextLine();

                    // cerca il veicolo nel file
                    boolean trovatoTarga = false;
                    try {
                        File file = new File(filePathNoleggio);
                        Scanner fileScanner = new Scanner(file);
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(" ");
                            String fileTarga = parts[0]; // la targa è il primo elemento della riga
                            if (fileTarga.equalsIgnoreCase(cercaTarga)) {
                                trovatoTarga = true;
                                boolean noleggiato = Boolean.parseBoolean(parts[2]); // il flag di noleggio è il terzo elemento della riga
                                if (noleggiato) {
                                    System.out.println("Il veicolo con targa " + cercaTarga + " è già stato noleggiato.");
                                } else {
                                    System.out.println("Il veicolo con targa " + cercaTarga + " può essere noleggiato.");
                                }
                                break;
                            }
                        }
                        fileScanner.close();
                        if (!trovatoTarga) {
                            System.out.println("Il veicolo con targa " + cercaTarga + " non è stato trovato nel file.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Il file non esiste o non può essere letto.");
                    }
                    break;

                case 4: //Riconsegna auto, acquisizione dati del noleggio(km, ore, stato, benzina) , calcolo pagamento ed budget cliente
                    System.out.print("\nInserisci targa del veicolo restituito: ");
                    targa = in.nextLine();
                    int i = 0, posCli =- 1;
                    int ltConsumati = 0; // lt di benzina
                    Veicolo vNoleggiato = null;
                    boolean trovato = false;
                    for (Cliente cl : listaClienti) { //cerca cliente a cui il veicolo e' stato noleggiato
                        if((cl.getVeicoloNoleggiato())!=null && (cl.getVeicoloNoleggiato()).equals(targa) ){ //cliente a cui ho noleggiato il veicolo
                            vNoleggiato = mioNoleggio.get(cercaVeicolo(targa)); //estraggo il veicolo noleggiato
                            posCli = listaClienti.indexOf(cl); // e la posizione del cliente nella lista
                        }
                    }
                    if(posCli < 0)
                        System.out.print("\nVeicolo non noleggiato");
                    else { //veicolo noleggiato
                        System.out.print("\nQuanti km ha ora il veicolo?");
                        double newKm = in.nextDouble(); //km contakm dopo la restituzione del veicolo
                        in.nextLine();
                        double kmPercorsi = newKm - vNoleggiato.getKmPercorsi(); //calcolo differenza km
                        vNoleggiato.setKmPercorsi(newKm); // aggiorna km totale veicolo nel contakm

                        System.out.print("Quante ore è durato il noleggio?");
                        int tempoNol = in.nextInt(); //tempo del noleggio in ore
                        in.nextLine();

                        if (vNoleggiato instanceof Bici ){ //se il veicolo è una bici
                            System.out.println("Stato della bici:0 ottimo , 1 peggiorato,  2 molto peggiorato");
                            ((Bici) vNoleggiato).setStato(in.nextInt()); //scrivo lo stato della bici alla riconsegna
                            in.nextLine();
                        } else if(vNoleggiato instanceof Auto || vNoleggiato instanceof Furgone) {    //se è un veicolo a benzina
                            System.out.println("Quanti litri di benzina ha ora il veicolo?");
                            int newLt= in.nextInt();                                 //litri benzina dopo la restituzione del veicolo
                            in.nextLine();
                            // calcolo il consumo di benzina
                            if (vNoleggiato instanceof Auto) {
                                ltConsumati=  ((Auto)vNoleggiato).getLitBenzina()- newLt; //calcolo differenza benzina per auto
                                ((Auto) vNoleggiato).setLitBenzina(newLt);       // aggiorna lit benzina auto
                            } else{
                                ltConsumati=  ((Furgone)vNoleggiato).getLitBenzina()- newLt; //calcolo differenza benzina per furgone
                                ((Furgone) vNoleggiato).setLitBenzina(newLt);   // aggiorna lit benzina furgone
                            }
                        }

                        int pagato=vNoleggiato.paga(listaClienti.get(posCli),kmPercorsi,tempoNol,ltConsumati); //richiama il metodo paga su vNoleggiato
                        if(pagato>0){                                                       //se il budget era sufficiente e si è proceduto al pagamento
                            System.out.println("Scontrino: km percorsi "+ kmPercorsi +" per "+ tempoNol +" ore. Prezzo: "+ pagato +" Euro");    //scontrino
                            vNoleggiato.setLibero(true);                                    //il veicolo torna libero
                            listaClienti.get(posCli).setVeicoloNoleggiato("");              //il cliente non ha più alcun veicolo noleggiato
                        }
                    }
                    break;

                case 5: //aumenta il budget del cliente richiesto dall'utente della cifra richiesta
                    System.out.print("\nInserisci nome cliente: ");
                    nome = in.nextLine();
                    System.out.print("\nInserisci cognome cliente: ");
                    cognome = in.nextLine();
                    System.out.print("\nInserisci cifra da ricaricare: ");
                    budget = in.nextDouble();

                    if(listaClienti != null){
                        double oldbudget = 0;                 //variabile per budget prima della ricarica
                        int pos=cercaCliente(nome,cognome);
                        if (pos >= 0){
                            oldbudget = (listaClienti.get(pos)).getBudget();  //estraggo il budget attuale (prima della ricarica)
                            System.out.println("\nVechhio Budget " + oldbudget);
                            budget=budget + oldbudget;                       //incremento il budget con la cfra da ricaricare
                            (listaClienti.get(pos)).setBudget(budget);      //assegno nuovo budget all'attributo del cliente
                            System.out.println("nuovo Budget " + listaClienti.get(pos).getBudget());        //scrivo a video
                        } else
                            System.out.print("\nCliente non esistente");
                    }else
                        System.out.print("\nNon ci sono ancora clienti in questo momento");
                    break;

                case 6: //Cerca cliente e verifica noleggio auto
                    // leggi il percorso del file e il cognome del cliente dall'input dell'utente
                    Scanner scannerCliente = new Scanner(System.in);
                    System.out.print("\nInserisci il percorso del file: ");
                    String filePathCliente = scannerCliente.nextLine();
                    System.out.print("\nInserisci il cognome del cliente: ");
                    String cognomeDelCliente = scannerCliente.nextLine();

                    // cerca il cliente nel file
                    boolean trovata = false;
                    try {
                        File file = new File(filePathCliente);
                        Scanner fileScanner = new Scanner(file);
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] parts = line.split(" ");
                            String fileCognome = parts[1]; // il cognome è il secondo elemento della riga
                            if (fileCognome.equalsIgnoreCase(cognomeDelCliente)) {
                                trovata = true;
                                boolean haNoleggio = Boolean.parseBoolean(parts[2]); // il flag di noleggio è il terzo elemento della riga
                                if (haNoleggio) {
                                    System.out.println("Il cliente " + cognomeDelCliente + " ha già noleggiato un'auto.");
                                } else {
                                    System.out.println("Il cliente " + cognomeDelCliente + " può noleggiare un'auto.");
                                }
                                break;
                            }
                        }
                        fileScanner.close();
                        if (!trovata) {
                            System.out.println("Il cliente " + cognomeDelCliente + " non è stato trovato nel file.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Il file non esiste o non può essere letto.");
                    }
                    break;
                default:
                    scelta = 7; //esci
            }
        } while (scelta < 7) ;

    }
    public static int cercaCliente (String nome, String cognome){
        int pos=-1;     //restituisce la pos del cliente nella lista o -1 se non presente
        for (Cliente cl: listaClienti) {
            if(cl.getCognome().equals(cognome) && cl.getCognome().equals(cognome))
                pos=listaClienti.indexOf(cl);
        }
        return pos;
    }
    public static int cercaVeicolo (String targa){
        int pos=-1; //restituisce la pos del veicolo nella lista o -1 se non presente
        for (Veicolo v: mioNoleggio) {
            if(v.getTarga().equals(targa) )
                pos=mioNoleggio.indexOf(v);
        }
        return pos;
    }
}
