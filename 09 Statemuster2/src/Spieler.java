import java.util.LinkedList;

public class Spieler extends Entity {
	private double tragkraft;
	private LinkedList<Gegenstand> gegenstaende;

	/**
	 * Initialisiert einen Spieler und setzt dessen Werte
	 * @param hp, Lebenspunkte
	 * @param staerke
	 */
	public Spieler(int hp, int staerke) {
		super(hp, staerke);

		this.tragkraft = 10.0;
		this.gegenstaende = new LinkedList<Gegenstand>();
	}

	/**
	 * Legt einen Gegenstand ab
	 * @param name, Name des Gegenstandes
	 * @return
	 */
	public Gegenstand legeGegenstandAb(String name) {
		for (Gegenstand g : gegenstaende) {
			if (g.getName().equals(name)) {
				gegenstaende.remove(g);
				return g;
			}
		}
		return null;
	}

	/**
	 * Nimmt einen Gegenstand auf
	 * @param g, Name des Gegenstandes
	 * @return
	 */
	public boolean gegenstandAufnehmen(Gegenstand g) {
		if (ermittleAktuellesGewicht() + g.getGewicht() <= this.tragkraft) {
			gegenstaende.add(g);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gibt eine LinkedList aller Gegenst�nde des Spielers zur�ck
	 * @return LinkedList
	 */
	public LinkedList<Gegenstand> getGegenstaende() {
		return gegenstaende;
	}

	/**
	 * Berechnet das aktuelle Gewicht 
	 * @return
	 */
	private double ermittleAktuellesGewicht() {
		double gewicht = 0;
		for (Gegenstand g : gegenstaende)
			gewicht = gewicht + g.getGewicht();
		return gewicht;
	}

	/**
	 * Gibt alle Informationen eines Spielers zur�ck
	 * @return
	 */
	public String zeigeStatus() {
		StringBuilder ergebnis = new StringBuilder("Tragkraft:" + this.tragkraft + "\nAufgenommene Gegenst�nde: ");
		if (gegenstaende.isEmpty()) {
			ergebnis.append("keine");
		} else {
			for (Gegenstand g : gegenstaende)
				ergebnis.append(g.toString() + "; ");
			ergebnis.append("\naktuelles Gewicht: " + ermittleAktuellesGewicht());
			ergebnis.toString();
		}

		// Zustand
		ergebnis.append("/nZustand: " + aktuellerZustand.toString());
		return ergebnis.toString();
	}

	/**
	 * Gibt die aktuelle Tragkraft zur�ck
	 * @return
	 */
	public double getTragkraft() {
		return tragkraft;
	}

	/**
	 * Setzt die aktuelle Tragkraft
	 * @param tragkraft
	 */
	public void setTragkraft(double tragkraft) {
		this.tragkraft = tragkraft;
	}
}
