import java.util.LinkedList;

public class Spieler extends Entity {
	private double tragkraft;
	private LinkedList<Gegenstand> gegenstaende;

	public Spieler(int hp, int staerke) {
		super(hp, staerke);

		this.tragkraft = 10.0;
		this.gegenstaende = new LinkedList<Gegenstand>();
	}

	public boolean gegenstandAufnehmen(Gegenstand g) {
		if (ermittleAktuellesGewicht() + g.getGewicht() <= this.tragkraft) {
			gegenstaende.add(g);
			return true;
		} else {
			return false;
		}
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

	public Gegenstand legeGegenstandAb(String name) {
		for (Gegenstand g : gegenstaende) {
			if (g.getName().equals(name)) {
				gegenstaende.remove(g);
				return g;
			}
		}
		return null;
	}

	public double getTragkraft() {
		return tragkraft;
	}

	public void setTragkraft(double tragkraft) {
		this.tragkraft = tragkraft;
	}
}
