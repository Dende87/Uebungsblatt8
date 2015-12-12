public class UE11Aufgabe17 {

	public static void main(String[] args) {
		BreakThrough spiel = new BreakThrough();
		spiel.spielen();
	}

}

class BTSpielzug {
	int vonReihe;
	int vonSpalte;
	int nachReihe;
	int nachSpalte;

	BTSpielzug(int vonR, int vonS, int nachR, int nachS) {
		this.vonReihe = vonR;
		this.vonSpalte = vonS;
		this.nachReihe = nachR;
		this.nachSpalte = nachS;
	}

	int getVonReihe() {
		return this.vonReihe;
	}

	int getVonSpalte() {
		return this.vonSpalte;
	}

	int getNachReihe() {
		return this.nachReihe;
	}

	int getNachSpalte() {
		return this.nachSpalte;
	}

}

class BTSpieler {
	boolean isA;

	BTSpieler(boolean isA) {
		this.isA = isA;
	}

	boolean isA() {
		return this.isA;
	}

	BTSpielzug getNaechsterSpielzug() {
		int vonReihe = IO.readInt("von Reihe: ");
		int vonSpalte = IO.readInt("von Spalte: ");
		int nachReihe = IO.readInt("nach Reihe: ");
		int nachSpalte = IO.readInt("nach Spalte: ");
		return new BTSpielzug(vonReihe, vonSpalte, nachReihe, nachSpalte);
	}
}

class BTSpielfeld {

	static final char FIGUR_A = '+'; // Figur Spieler FIGUR_A
	static final char FIGUR_B = 'o'; // Figur Spieler FIGUR_B
	static final char LEER = '.'; // Feld leer

	// Hinweis: Auf eine Klasse Spielfigur habe ich verzichtet. Stattdessen
	// werden Spielfiguren durch char-Werte repr‰sentiert. Es ist aber durchaus
	// legitim, eine geeignete Klasse Spielfigur zu implementieren!

	char[][] spielfeld = {
			{ BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A,
					BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A,
					BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A },
			{ BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A,
					BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A,
					BTSpielfeld.FIGUR_A, BTSpielfeld.FIGUR_A },
			{ BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER },
			{ BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER },
			{ BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER, BTSpielfeld.LEER, BTSpielfeld.LEER,
					BTSpielfeld.LEER },
			{ BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B,
					BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B,
					BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B },
			{ BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B,
					BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B,
					BTSpielfeld.FIGUR_B, BTSpielfeld.FIGUR_B } };

	BTSpielfeld() {
	}

	int getReihen() {
		return this.spielfeld.length;
	}

	int getSpalten() {
		return this.spielfeld[0].length;
	}

	char getFigur(int reihe, int spalte) {
		return this.spielfeld[reihe][spalte];
	}

	void spielzugAusfuehren(BTSpielzug zug, boolean aAmZug) {
		this.spielfeld[zug.getVonReihe()][zug.getVonSpalte()] = BTSpielfeld.LEER;
		if (aAmZug) {
			this.spielfeld[zug.getNachReihe()][zug.getNachSpalte()] = BTSpielfeld.FIGUR_A;
		} else {
			this.spielfeld[zug.getNachReihe()][zug.getNachSpalte()] = BTSpielfeld.FIGUR_B;
		}
	}

	void print() {
		// Spieler A
		System.out.println("\n  Spieler A");

		// Nummerierung der Spalten
		System.out.print("  ");
		for (int s = 0; s < this.spielfeld[0].length; s++) {
			System.out.print(s + " ");
		}
		System.out.println();

		// Feld
		for (int r = 0; r < this.spielfeld.length; r++) {
			System.out.print(r + " "); // Zeilennummer
			for (int s = 0; s < this.spielfeld[r].length; s++) {
				System.out.print(this.spielfeld[r][s] + " ");
			}
			System.out.println();
		}

		// Spieler B
		System.out.println("  Spieler B\n");
	}
}

class BTSchiedsrichter {

	boolean ueberpruefeSpielEnde(BTSpielfeld feld, boolean aAmZug) {
		// Ziel erreicht
		for (int s = 0; s < feld.getSpalten(); s++) {
			if (feld.getFigur(0, s) == BTSpielfeld.FIGUR_B) {
				// Spieler B hat Grundlinie erreicht
				return true;
			}
			if (feld.getFigur(feld.getReihen() - 1, s) == BTSpielfeld.FIGUR_A) {
				// Spieler A hat Grundlinie erreicht
				return true;
			}
		}

		// keine eigenen Figuren mehr
		int anzahlFiguren = 0;
		for (int r = 0; r < feld.getReihen(); r++) {
			for (int s = 0; s < feld.getSpalten(); s++) {
				if (aAmZug && feld.getFigur(r, s) == BTSpielfeld.FIGUR_A
						|| !aAmZug && feld.getFigur(r, s) == BTSpielfeld.FIGUR_B) {
					anzahlFiguren++;
				}
			}
		}
		if (anzahlFiguren == 0) {
			return true;
		}

		return false;
	}

	boolean ueberpruefeSpielzug(BTSpielfeld feld, boolean aAmZug, BTSpielzug zug) {
		int vonReihe = zug.getVonReihe();
		int vonSpalte = zug.getVonSpalte();
		int nachReihe = zug.getNachReihe();
		int nachSpalte = zug.getNachSpalte();
		// Indizesueberpruefung
		if (vonReihe < 0 || vonReihe >= feld.getReihen() || nachReihe < 0
				|| nachReihe >= feld.getReihen() || vonSpalte < 0
				|| vonSpalte >= feld.getSpalten() || nachSpalte < 0
				|| nachSpalte >= feld.getSpalten()) {
			return false;
		}

		// Spalte
		if (nachSpalte < vonSpalte - 1 || nachSpalte > vonSpalte + 1) {
			return false;
		}

		if (aAmZug) {
			if (nachReihe != vonReihe + 1) { // Reihe
				return false;
			}
			if (feld.getFigur(vonReihe, vonSpalte) != BTSpielfeld.FIGUR_A) {
				// keine eigene Figur
				return false;
			}
			if (feld.getFigur(nachReihe, nachSpalte) == BTSpielfeld.FIGUR_A) {
				// eigene Figur
				return false;
			}
		} else {
			if (nachReihe != vonReihe - 1) { // reihe
				return false;
			}
			if (feld.getFigur(vonReihe, vonSpalte) != BTSpielfeld.FIGUR_B) {
				// keine eigene Figur
				return false;
			}
			if (feld.getFigur(nachReihe, nachSpalte) == BTSpielfeld.FIGUR_B) {
				// eigene Figur
				return false;
			}
		}
		return true;
	}

	void siegerBekanntgeben(boolean siegerA, String grund) {
		String spieler = siegerA ? "A" : "B";
		System.out.println("Sieger ist Spieler " + spieler + "; Grund: "
				+ grund);
	}
}

class BreakThrough {

	BTSpielfeld spielfeld;
	BTSchiedsrichter schiedsrichter;
	BTSpieler spielerA, spielerB;

	BreakThrough() {
		this.spielfeld = new BTSpielfeld();
		this.schiedsrichter = new BTSchiedsrichter();
		this.spielerA = new BTSpieler(true);
		this.spielerB = new BTSpieler(false);
	}

	void spielen() {
		this.spielfeld.print();
		BTSpieler aktSpieler = this.spielerA; // Spieler _A beginnt
		while (!this.schiedsrichter.ueberpruefeSpielEnde(this.spielfeld,
				aktSpieler.isA())) {
			System.out.println("Spieler " + (aktSpieler.isA() ? "A" : "B")
					+ " ist am Zug!");
			BTSpielzug zug = aktSpieler.getNaechsterSpielzug();
			if (!this.schiedsrichter.ueberpruefeSpielzug(this.spielfeld,
					aktSpieler.isA(), zug)) {
				this.schiedsrichter.siegerBekanntgeben(!aktSpieler.isA(),
						"Regelverstoﬂ");
				return; // Spielende
			} else {
				this.spielfeld.spielzugAusfuehren(zug, aktSpieler.isA());
				this.spielfeld.print();
				if (aktSpieler == this.spielerA) { // Spielerwechsel
					aktSpieler = this.spielerB;
				} else {
					aktSpieler = this.spielerA;
				}
			}
		}
		this.schiedsrichter.siegerBekanntgeben(!aktSpieler.isA(),
				"Ziel erreicht");
	}

}
