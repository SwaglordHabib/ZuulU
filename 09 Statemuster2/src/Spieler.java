import java.util.LinkedList;

public class Spieler extends Entity {
	private double tragkraft;
	private LinkedList<Gegenstand> gegenstaende;

	/**
	 * Erstllt ein Spieler-Objekt
	 * 
	 * @param hp
	 *            gibt die HP-Anzahl an (int)
	 * @param staerke
	 *            gibt die Stärke an (int)
	 */
	public Spieler(int hp, int staerke) {
		super(hp, staerke);

		this.tragkraft = 10.0;
		this.gegenstaende = new LinkedList<Gegenstand>();
	}

	public Gegenstand legeGegenstandAb(String name) {
		for (Gegenstand g : gegenstaende) {
			if (g.getName().equals(name)) {
				gegenstaende.remove(g);
				return g;
			}
		}
		return null;
	}

	public boolean gegenstandAufnehmen(Gegenstand g) {
		if (ermittleAktuellesGewicht() + g.getGewicht() <= this.tragkraft) {
			gegenstaende.add(g);
			return true;
		} else {
			return false;
		}
	}

	public LinkedList<Gegenstand> getGegenstaende() {
		return gegenstaende;
	}

	private double ermittleAktuellesGewicht() {
		double gewicht = 0;
		for (Gegenstand g : gegenstaende)
			gewicht = gewicht + g.getGewicht();
		return gewicht;
	}

	public String zeigeStatus() {
		StringBuilder ergebnis = new StringBuilder("Tragkraft:" + this.tragkraft + "\nAufgenommene Gegenstände: ");
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

	public double getTragkraft() {
		return tragkraft;
	}

	public void setTragkraft(double tragkraft) {
		this.tragkraft = tragkraft;
	}
}
