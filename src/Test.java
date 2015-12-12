public class Test {

	public static void main(String[] args) {
		//TANListe wird für 100 einträge reserviert
		
		int tanSize = IO.readInt("Bitte groesse der TAN Liste angeben: ");
		TANListe tanliste = new TANListe(tanSize);
		//TANListe wird erstellt mit 100 verschiedenen Zahlen
		tanliste.create();
		
		//Ausgabe der Tanliste mit 15 Nummern nebeneinander
		for(int i = 0;i< tanliste.tanliste.length;i++){
			System.out.print(tanliste.tanliste[i].number + " " + tanliste.tanliste[i].isUsed);
			IO.print(" ");
			if((i+1) % 15 == 0){
				IO.println();
			}
		}
		
		IO.println();
		
		boolean valid = tanliste.useNumber(IO.readInt("Bitte gueltige TAN eingeben: "));
		if (valid) {
			IO.println("Valide!");
		}else{
			IO.println("InValide!");
		}
		
		for(int i = 0;i< tanliste.tanliste.length;i++){
			System.out.print(tanliste.tanliste[i].number + " " + tanliste.tanliste[i].isUsed);
			IO.print(" ");
			if((i+1) % 15 == 0){
				IO.println();
			}
		}
		
		boolean valid1 = tanliste.useNumber(IO.readInt("Bitte gueltige TAN eingeben: "));
		if (valid1) {
			IO.println("Valide!");
		}else{
			IO.println("InValide!");
		}
	}
}

class TANListe {

	Dictionary tanliste[];

	static boolean used = true;
	static boolean notUsed = false;

	TANListe(int size) {
		tanliste = new Dictionary[size];
	}

	//Erstellt eine TANListe mit 100 einträgen
	Dictionary[] create() {
		for (int i = 0; i < tanliste.length; i++) {
			int randomZahl = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
			tanliste[i] = new Dictionary(randomZahl, notUsed);
		}

		return getValidTanList(tanliste);
	}

	//Funktion zum Verwenden von TANNummern
	boolean useNumber(int number) {

		for (int i = 0; i < tanliste.length; i++) {
			if (tanliste[i].containsNumber(number)) {
				if (tanliste[i].isUsed == false) {
					tanliste[i].isUsed = true;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	//Überprüft die TANListe auf Gültige werte und ersetzt unggültige
	private Dictionary[] getValidTanList(Dictionary[] tanliste) {

		boolean check = false;
		for (int i = 0; i < tanliste.length; i++) {
			int zahl = tanliste[i].number;
			for (int j = 0; j < tanliste.length; j++) {
				if (tanliste[j].containsNumber(zahl)) {
					if (check == false) {
						check = true;
					} else {
						int randomZahl = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
						tanliste[i].number = randomZahl;
						getValidTanList(tanliste);
					}
				}
			}
			check = false;
		}

		return tanliste;
	}

}

// Dictionary Klasse für die Datenstruktur vom Array mit TANNummerneinträgen und Verwendung
class Dictionary {

	int number;
	boolean isUsed;

	Dictionary(int number, boolean used) {
		this.number = number;
		this.isUsed = used;

	}

	boolean containsNumber(int number) {
		if (this.number == number) {
			return true;
		}
		return false;
	}
}
